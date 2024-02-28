package com.grupo04pj01.urna.services.impl;

import com.grupo04pj01.urna.models.CandidatoModel;
import com.grupo04pj01.urna.models.VotosModel;
import com.grupo04pj01.urna.repositories.CandidatoRepository;
import com.grupo04pj01.urna.repositories.VotoRepository;
import com.grupo04pj01.urna.services.CandidatoService;
import com.grupo04pj01.urna.services.VotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VotoServiceImpl implements VotoService {

    private final VotoRepository votoRepository;


    @Override
    public void votar(VotosModel votosModel) {
        votoRepository.save(votosModel);
    }

    @Override
    public Integer contarVotosById(Long candidatoId) {
       Integer totalVotos= votoRepository.contarVotosByCandidatoId(candidatoId);
        return totalVotos;
    }

    @Override
    public void deletarTodosVotos() {
        votoRepository.deleteAll();
    }
}
