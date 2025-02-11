package com.grupo04pj01.urna.services.impl;

import com.grupo04pj01.urna.DTO.BuscaCandidatoDTO;
import com.grupo04pj01.urna.exceptions.BusinessException;
import com.grupo04pj01.urna.exceptions.NotFoundException;
import com.grupo04pj01.urna.models.CandidatoModel;
import com.grupo04pj01.urna.models.EleitorModel;
import com.grupo04pj01.urna.repositories.CandidatoRepository;
import com.grupo04pj01.urna.services.CandidatoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CandidatoServiceImpl implements CandidatoService {

    private final CandidatoRepository candidatoRepository;


    @Override
    public CandidatoModel criarCandidato(CandidatoModel candidatoModel) {
        verificarChapaDisponivel(candidatoModel);
        return candidatoRepository.save(candidatoModel);
    }

    @Override
    public List<CandidatoModel> buscarCandidato() {
        return candidatoRepository.findAll();
    }

    @Override
    public CandidatoModel buscaCandidatoByChapa(BuscaCandidatoDTO candidatoDTO) {
      Optional<CandidatoModel> candidatoModel =  candidatoRepository.findByChapa(candidatoDTO.getChapa());
      CandidatoModel model= validarOptional(candidatoModel);
        return model;
    }

    @Override
    public void deletarCandidatoByChapa(String chapa) {
       CandidatoModel model = validarOptional(candidatoRepository.findByChapa(chapa));
        candidatoRepository.delete(model);
    }

    @Override
    public void deleteAllCandidatos() {
        candidatoRepository.deleteAll();
        CandidatoModel brancos= new CandidatoModel();
        brancos.setNome("Brancos & Nulos");
        brancos.setChapa("0");
        candidatoRepository.save(brancos);
    }

    @Override
    public CandidatoModel update(CandidatoModel candidatoEditado) {
        CandidatoModel candidatoVerificado = validaUpdadeCandidato(candidatoEditado);

        candidatoVerificado.setNome(candidatoEditado.getNome());
        candidatoVerificado.setChapa(candidatoEditado.getChapa());
        candidatoVerificado.setFoto(candidatoEditado.getFoto());

        candidatoRepository.save(candidatoVerificado);

        return candidatoVerificado;
    }

    private CandidatoModel validarOptional(Optional<CandidatoModel> optional){
        if (optional.isEmpty()) throw new NotFoundException("Candidato Não Encontrado");
        return optional.get();

    }

    private void verificarChapaDisponivel(CandidatoModel candidatoModel){
        String chapa= candidatoModel.getChapa();
        Optional<CandidatoModel> candidatoOptional = candidatoRepository.findByChapa(chapa);
        if (!candidatoOptional.isEmpty()) throw new NotFoundException("Chapa já cadastrada");
    }

    private CandidatoModel validaUpdadeCandidato(CandidatoModel candidatoModel){
        Optional<CandidatoModel> candidatoEncontrado = candidatoRepository.findById(candidatoModel.getId());
        if (candidatoEncontrado.isEmpty()) throw new NotFoundException("Candidato não encontrado");

        Optional<CandidatoModel> chapaEmUso = candidatoRepository.findByChapa(candidatoModel.chapa);
        if (chapaEmUso.isPresent() && (!Objects.equals(chapaEmUso.get().getId(), candidatoModel.getId()))) throw new BusinessException("Número de Chapa em uso");

        return candidatoEncontrado.get();
    }




}
