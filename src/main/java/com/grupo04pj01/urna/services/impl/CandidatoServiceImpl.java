package com.grupo04pj01.urna.services.impl;

import com.grupo04pj01.urna.DTO.BuscaCandidatoDTO;
import com.grupo04pj01.urna.exceptions.NotFoundException;
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
    }

    CandidatoModel validarOptional(Optional<CandidatoModel> optional){
        if (optional.isEmpty()) throw new NotFoundException("Candidato Não Encontrado");
        return optional.get();

    }
}
