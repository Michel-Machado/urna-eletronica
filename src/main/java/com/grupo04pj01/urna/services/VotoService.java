package com.grupo04pj01.urna.services;

import com.grupo04pj01.urna.DTO.BuscaCandidatoDTO;
import com.grupo04pj01.urna.DTO.CandidatoVotosRecebidosDTO;
import com.grupo04pj01.urna.DTO.VotoDTO;
import com.grupo04pj01.urna.models.EleitorModel;
import com.grupo04pj01.urna.models.VotosModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VotoService {

    void votar(BuscaCandidatoDTO votoDTO);

    List<CandidatoVotosRecebidosDTO> contarVotosById();

    void deletarTodosVotos();


}
