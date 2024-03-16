package com.grupo04pj01.urna.services.impl;

import com.grupo04pj01.urna.DTO.BuscaCandidatoDTO;
import com.grupo04pj01.urna.DTO.CandidatoVotosRecebidosDTO;
import com.grupo04pj01.urna.models.CandidatoModel;
import com.grupo04pj01.urna.models.VotosModel;
import com.grupo04pj01.urna.repositories.VotoRepository;
import com.grupo04pj01.urna.services.VotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VotoServiceImpl implements VotoService {

    private final VotoRepository votoRepository;
    private final UrnaServiceImpl urnaService;
    private final CandidatoServiceImpl candidatoService;


    @Override
    public void votar(BuscaCandidatoDTO votoDTO) {
       if (!urnaService.verificaUrna()) throw new RuntimeException("Urna Bloqueada");

       CandidatoModel candidato = candidatoService.buscaCandidatoByChapa(votoDTO);

       votoRepository.save(new VotosModel(candidato));
        urnaService.alterarStatusUrna(false);

    }

    @Override
    public List<CandidatoVotosRecebidosDTO> contarVotosById() {
      List<CandidatoModel> candidatosEncontrados=  candidatoService.buscarCandidato();
      List<CandidatoVotosRecebidosDTO> listDTO= new ArrayList<>();

        for (CandidatoModel candidato: candidatosEncontrados) {
           Integer votos= votoRepository.contarVotosByCandidatoId(candidato.getId());
           String nome = candidato.getNome();

           listDTO.add(new CandidatoVotosRecebidosDTO(nome,votos));
        }

        return listDTO;
    }

    @Override
    public void deletarTodosVotos() {
        votoRepository.deleteAll();
    }

}
