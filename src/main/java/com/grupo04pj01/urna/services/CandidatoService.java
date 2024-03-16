package com.grupo04pj01.urna.services;

import com.grupo04pj01.urna.DTO.BuscaCandidatoDTO;
import com.grupo04pj01.urna.DTO.VotoDTO;
import com.grupo04pj01.urna.models.CandidatoModel;
import com.grupo04pj01.urna.models.EleitorModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CandidatoService {

    CandidatoModel criarCandidato(CandidatoModel candidatoModel);

    List<CandidatoModel> buscarCandidato();

    CandidatoModel buscaCandidatoByChapa(BuscaCandidatoDTO candidatoDTO);
    void deletarCandidatoByChapa(String chapa);

    void deleteAllCandidatos();
}
