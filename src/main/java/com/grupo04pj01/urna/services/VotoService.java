package com.grupo04pj01.urna.services;

import com.grupo04pj01.urna.models.EleitorModel;
import com.grupo04pj01.urna.models.VotosModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VotoService {

    void votar(VotosModel votosModel);

    Integer contarVotosById(Long candidatoId);

    void deletarTodosVotos();


}
