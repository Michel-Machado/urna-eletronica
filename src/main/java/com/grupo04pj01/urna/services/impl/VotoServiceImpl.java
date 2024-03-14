package com.grupo04pj01.urna.services.impl;

import com.grupo04pj01.urna.DTO.CandidatoVotosRecebidosDTO;
import com.grupo04pj01.urna.DTO.VotoDTO;
import com.grupo04pj01.urna.models.CandidatoModel;
import com.grupo04pj01.urna.models.VotosModel;
import com.grupo04pj01.urna.repositories.CandidatoRepository;
import com.grupo04pj01.urna.repositories.VotoRepository;
import com.grupo04pj01.urna.services.CandidatoService;
import com.grupo04pj01.urna.services.VotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VotoServiceImpl implements VotoService {

    private final VotoRepository votoRepository;
    private final UrnaServiceImpl urnaService;
    private final CandidatoServiceImpl candidatoService;


    @Override
    public void votar(VotoDTO chapa) {
       if (!urnaService.verificaUrna()) throw new RuntimeException("Urna Bloqueada");

       CandidatoModel candidato = candidatoService.buscaCandidatoByChapa(chapa.getChapa());

       votoRepository.save(new VotosModel(candidato));
        urnaService.alterarStatusUrna(false);

    }

    @Override
    public List<CandidatoVotosRecebidosDTO> contarVotosById() {
      List<CandidatoModel> candidatosEncontrados=  candidatoService.buscarCandidato();
      CandidatoVotosRecebidosDTO votosRecebidosDTO = new CandidatoVotosRecebidosDTO();

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
