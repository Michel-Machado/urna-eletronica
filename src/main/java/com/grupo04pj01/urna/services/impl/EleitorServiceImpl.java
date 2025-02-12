package com.grupo04pj01.urna.services.impl;

import com.grupo04pj01.urna.DTO.EleitoresCadastradosDTO;
import com.grupo04pj01.urna.DTO.ResponseCadastroEleitor;
import com.grupo04pj01.urna.exceptions.BusinessException;
import com.grupo04pj01.urna.exceptions.NotFoundException;
import com.grupo04pj01.urna.models.EleitorModel;
import com.grupo04pj01.urna.models.EleitorPresenteModel;
import com.grupo04pj01.urna.repositories.EleitorPresenteRepository;
import com.grupo04pj01.urna.repositories.EleitorRepository;
import com.grupo04pj01.urna.services.EleitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EleitorServiceImpl implements EleitorService {

    private final EleitorRepository eleitorRepository;
    private final EleitorPresenteRepository eleitorPresenteRepository;
    private final UrnaServiceImpl urnaService;
    private List<EleitoresCadastradosDTO> eleitoresNaoCadastrados = new ArrayList<>();
    private List<EleitoresCadastradosDTO> eleitoresCadastrados = new ArrayList<>();


    @Override
    public ResponseCadastroEleitor criaEleitor(List<EleitorModel> eleitorModel) {
        eleitoresNaoCadastrados.clear();
        eleitoresCadastrados.clear();


        for (EleitorModel eleitor: eleitorModel) {
            verificarRaDisponivel(eleitor);
        }

        ResponseCadastroEleitor response = new ResponseCadastroEleitor(eleitoresCadastrados,eleitoresNaoCadastrados);


//        eleitoresNaoCadastrados.clear();
//        eleitoresCadastrados.clear();

        return response;

    }

    @Override
    public List<EleitorModel> buscarEleitores(String nome, String ra) {
       List<EleitorModel> eleitorModelList= eleitorRepository.search(nome,ra);
        return eleitorModelList;
    }

    @Override
    public void liberarEleitor(String ra) {
        if (urnaService.verificaUrna())
            return;
       Optional<EleitorModel> eleitorModel= eleitorRepository.findEleitorModelByRa(ra);
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
    public EleitorModel buscaEleitorByRa(String ra){
        Optional<EleitorModel> eleitor= eleitorRepository.findEleitorModelByRa(ra);
        EleitorModel model = validaEleitor(eleitor);

        return model;
    }

    @Override
    public void deleteAll() {
        eleitorRepository.deleteAll();
    }

    private EleitorModel validaEleitor(Optional<EleitorModel> eleitorModelOptional){
        if (eleitorModelOptional.isEmpty()) throw new NotFoundException("Eleitor não encontrado");

        return eleitorModelOptional.get();
    }

    private void verificarRaDisponivel(EleitorModel eleitorModel){

        Optional<EleitorModel> optionalEleitor = eleitorRepository.findEleitorModelByRa(eleitorModel.getRa());

        if (optionalEleitor.isPresent() ){
            EleitoresCadastradosDTO eleitorNCadastrado = new EleitoresCadastradosDTO(eleitorModel.getRa(), eleitorModel.getNome());
            eleitoresNaoCadastrados.add(eleitorNCadastrado);
        }else {
            EleitoresCadastradosDTO eleitorCadastrado = new EleitoresCadastradosDTO(eleitorModel.getRa(), eleitorModel.getNome());
            eleitoresCadastrados.add(eleitorCadastrado);
            eleitorRepository.save(eleitorModel);
        }
    }

    private EleitorModel validaUpdadeEleitor(EleitorModel eleitorModel){
        Optional<EleitorModel> eleitorEncontrado = eleitorRepository.findById(eleitorModel.getId());
        if (eleitorEncontrado.isEmpty()) throw new NotFoundException("Eleitor não encontrado");

        Optional<EleitorModel> raEmUso = eleitorRepository.findEleitorModelByRa(eleitorModel.ra);
        if (raEmUso.isPresent() && (!Objects.equals(raEmUso.get().getId(), eleitorModel.getId()))) throw new BusinessException("RA em uso");

        return eleitorEncontrado.get();
    }

}
