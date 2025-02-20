package com.grupo04pj01.urna.services.impl;

import com.grupo04pj01.urna.DTO.ApuracaoDTO;
import com.grupo04pj01.urna.DTO.CandidatoVotosRecebidosDTO;
import com.grupo04pj01.urna.models.ApuracaoModel;
import com.grupo04pj01.urna.models.UrnaModel;
import com.grupo04pj01.urna.repositories.ApuracaoRepository;
import com.grupo04pj01.urna.repositories.UrnaRepository;
import com.grupo04pj01.urna.repositories.VotoRepository;
import com.grupo04pj01.urna.services.ApuracaoService;
import com.grupo04pj01.urna.services.VotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApuracaoServiceImpl implements ApuracaoService {

    private final ApuracaoRepository apuracaoRepository;
    private final VotoService votoService;


    @Override
    public ApuracaoDTO verificaApuracao() {
        try {
            Long pk = 1L;
            Optional<ApuracaoModel> apuracao = apuracaoRepository.findById(pk);
            boolean apuracaoLiberada = validar(apuracao).getIsApuracaoLiberada();
            if (apuracaoLiberada){
                int totalGeral = 0;
                List<CandidatoVotosRecebidosDTO> votos = votoService.contarVotosById();
                for (CandidatoVotosRecebidosDTO votoPorCandidato: votos ) {
                  totalGeral = totalGeral + votoPorCandidato.getTotalVotos();
                }
                ApuracaoDTO apuracaoResponse = new ApuracaoDTO(totalGeral, votos);
                return apuracaoResponse;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public void alterarStatusApuracao(Boolean isApuracaoLiberada) {
        Long pk = 1L;
        Optional<ApuracaoModel> apuracao = apuracaoRepository.findById(pk);
       ApuracaoModel model= validar(apuracao);
       model.setIsApuracaoLiberada(isApuracaoLiberada);
       apuracaoRepository.save(model);
    }

    private ApuracaoModel validar(Optional<ApuracaoModel> objeto) {
        if (objeto.isPresent()) {
            return objeto.get();
        } else throw new RuntimeException("status nao iniciado");

    }
}
