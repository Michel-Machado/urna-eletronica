package com.grupo04pj01.urna.services.impl;

import com.grupo04pj01.urna.models.CandidatoModel;
import com.grupo04pj01.urna.models.EleitorModel;
import com.grupo04pj01.urna.repositories.CandidatoRepository;
import com.grupo04pj01.urna.repositories.EleitorRepository;
import com.grupo04pj01.urna.services.CandidatoService;
import com.grupo04pj01.urna.services.EleitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CandidatoServiceImpl implements CandidatoService {

    private final CandidatoRepository candidatoRepository;


    @Override
    public CandidatoModel criarCandidato(CandidatoModel candidatoModel) {
        return candidatoRepository.save(candidatoModel);
    }

    @Override
    public List<CandidatoModel> buscarCandidato() {
        return candidatoRepository.findAll();
    }

    @Override
    public CandidatoModel buscaCandidatoById(Long candidatoId) {
      Optional<CandidatoModel> candidatoModel =  candidatoRepository.findById(candidatoId);
      CandidatoModel model= validarOptional(candidatoModel);
        return model;
    }

    @Override
    public void deletarCandidatoById(Long candidatoId) {
        Optional<CandidatoModel> candidatoModel= candidatoRepository.findById(candidatoId);
        CandidatoModel model= validarOptional(candidatoModel);
        candidatoRepository.delete(model);
    }

    CandidatoModel validarOptional(Optional<CandidatoModel> optional){
        if (optional.isEmpty()) throw new RuntimeException("Candidato Não Encontrado");
        return optional.get();

    }
}
