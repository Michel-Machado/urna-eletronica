package com.grupo04pj01.urna.services.impl;

import com.grupo04pj01.urna.DTO.EleitoresCadastradosDTO;
import com.grupo04pj01.urna.DTO.EleitoresCadastrarDTO;
import com.grupo04pj01.urna.DTO.EleitoresResponseDTO;
import com.grupo04pj01.urna.DTO.ResponseCadastroEleitor;
import com.grupo04pj01.urna.exceptions.BusinessException;
import com.grupo04pj01.urna.exceptions.NotFoundException;
import com.grupo04pj01.urna.models.EleitorModel;
import com.grupo04pj01.urna.models.EleitorPresenteModel;
import com.grupo04pj01.urna.repositories.EleitorPresenteRepository;
import com.grupo04pj01.urna.repositories.EleitorRepository;
import com.grupo04pj01.urna.services.EleitorService;
import io.swagger.v3.core.converter.ModelConverter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.Writer;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EleitorServiceImpl implements EleitorService {

    private final EleitorRepository eleitorRepository;
    private final EleitorPresenteRepository eleitorPresenteRepository;
    private final UrnaServiceImpl urnaService;
    private final ModelMapper mapper;
    private List<EleitoresCadastradosDTO> eleitoresNaoCadastrados = new ArrayList<>();
    private List<EleitoresCadastradosDTO> eleitoresCadastrados = new ArrayList<>();


    @Override
    public ResponseCadastroEleitor criaEleitor(EleitoresCadastrarDTO listaNovosEleitores) {
        eleitoresCadastrados.clear();
        var numero =  gerarNovoNumero(listaNovosEleitores.getClasse());
        var classe = listaNovosEleitores.getClasse().toUpperCase();

        for (String novoEleitor: listaNovosEleitores.getNomes()) {
            String ra = gerarRa(classe, numero);

            EleitorModel eleitorCadastrar = EleitorModel.builder()
                    .ra(ra)
                    .numero(numero)
                    .classe(classe)
                    .nome(novoEleitor.toUpperCase())
                    .build();

            validaNovoEleitor(eleitorCadastrar);

            eleitorRepository.save(eleitorCadastrar);
            eleitoresCadastrados.add(new EleitoresCadastradosDTO(eleitorCadastrar.ra, eleitorCadastrar.nome));
            numero = numero + 1;
        }
        ResponseCadastroEleitor response = new ResponseCadastroEleitor(eleitoresCadastrados);

        return response;
    }

    private void validaNovoEleitor(EleitorModel eleitorCadastrar) {
        var classe = eleitorCadastrar.getClasse().toUpperCase();
        var nome = eleitorCadastrar.getNome();
        Optional<EleitorModel> validacao = eleitorRepository.findByNomeAndClasse( nome,classe);
        if(validacao.isPresent()){
            throw new BusinessException("nome já cadastrado");
        }
    }

    @Override
    public List<EleitoresResponseDTO> buscarEleitores(String nome, String ra) {
       List<EleitorModel> eleitorModelList= eleitorRepository.search(nome,ra);
       List<EleitoresResponseDTO> eleitoresResponse = eleitorModelList.stream().map(eleitor ->mapper.map(eleitor, EleitoresResponseDTO.class)).collect(Collectors.toList());
        return eleitoresResponse;
    }

    @Override
    public void liberarEleitor(String ra) {
        if (urnaService.verificaUrna())
            return;
       Optional<EleitorModel> eleitorModel= eleitorRepository.findEleitorModelByRaIgnoreCase(ra);
       EleitorModel eleitorCadastrado= validarEleitor(eleitorModel);

       Optional<EleitorPresenteModel> eleitorPresenteModel= eleitorPresenteRepository.findByEleitorModel(eleitorCadastrado);
       validarPresenca(eleitorPresenteModel);

       eleitorPresenteRepository.save(new EleitorPresenteModel(eleitorCadastrado));
       urnaService.alterarStatusUrna(true);

    }

    public EleitorModel validarEleitor(Optional<EleitorModel> eleitorModel){
        if (eleitorModel.isEmpty()) throw new NotFoundException("Eleitor não cadastrado");

        return eleitorModel.get();
    }

    public void validarPresenca(Optional<EleitorPresenteModel> eleitorModel){
        if (eleitorModel.isPresent()) throw new BusinessException("Eleitor já votou");

    }

    @Override
    public void deleteByRa(String ra) {
       EleitorModel model= validaEleitor(eleitorRepository.findEleitorModelByRa(ra));
       eleitorRepository.delete(model);
    }

    @Override
    public EleitorModel update(EleitorModel eleitorEditado) {
        EleitorModel eleitorVerificado = validaUpdadeEleitor(eleitorEditado);

        eleitorVerificado.setNome(eleitorEditado.getNome());
        eleitorVerificado.setRa(eleitorEditado.getRa());

        eleitorRepository.save(eleitorVerificado);

        return eleitorVerificado;
    }

    @Override
    public EleitoresResponseDTO buscaEleitorByRa(String ra){
        Optional<EleitorModel> eleitor= eleitorRepository.findEleitorModelByRaIgnoreCase(ra);
        EleitorModel model = validaEleitor(eleitor);
        EleitoresResponseDTO eleitorResponse = mapper.map(model, EleitoresResponseDTO.class);

        return eleitorResponse;
    }

    @Override
    public void deleteAll() {
        eleitorRepository.deleteAll();
    }

    public void exportarCsv(Writer writer){
        List<EleitorModel> eleitores = eleitorRepository.findAll();


    }

    private EleitorModel validaEleitor(Optional<EleitorModel> eleitorModelOptional){
        if (eleitorModelOptional.isEmpty()) throw new NotFoundException("Eleitor não encontrado");

        return eleitorModelOptional.get();
    }

    private Integer gerarNovoNumero(String classe){
        Integer maiorNumero =  eleitorRepository.buscaMaiorNumero(classe.toUpperCase());
        if (maiorNumero == null) maiorNumero = 0;

        return maiorNumero + 1;

    }

    private String gerarRa( String classe ,Integer numero){

        String ra = classe + String.format("%02d", numero);
        return ra;
    }


    private EleitorModel validaUpdadeEleitor(EleitorModel eleitorModel){
        Optional<EleitorModel> eleitorEncontrado = eleitorRepository.findById(eleitorModel.getId());
        if (eleitorEncontrado.isEmpty()) throw new NotFoundException("Eleitor não encontrado");

        Optional<EleitorModel> raEmUso = eleitorRepository.findEleitorModelByRa(eleitorModel.ra);
        if (raEmUso.isPresent() && (!Objects.equals(raEmUso.get().getId(), eleitorModel.getId()))) throw new BusinessException("RA em uso");

        return eleitorEncontrado.get();
    }

}
