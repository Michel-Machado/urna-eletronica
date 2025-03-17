package com.grupo04pj01.urna.services;

import com.grupo04pj01.urna.DTO.ApuracaoDTO;
import com.grupo04pj01.urna.DTO.BuscaCandidatoDTO;
import com.grupo04pj01.urna.DTO.CandidatoVotosRecebidosDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ApuracaoService {
    ApuracaoDTO verificaApuracao();

    void alterarStatusApuracao(Boolean isApuracaoLiberada);

    boolean getStatusApuracao();


}
