package com.grupo04pj01.urna.controllers;

import com.grupo04pj01.urna.DTO.BuscaCandidatoDTO;
import com.grupo04pj01.urna.DTO.CandidatoVotosRecebidosDTO;
import com.grupo04pj01.urna.DTO.VotoDTO;
import com.grupo04pj01.urna.models.CandidatoModel;
import com.grupo04pj01.urna.models.VotosModel;
import com.grupo04pj01.urna.services.ApuracaoService;
import com.grupo04pj01.urna.services.CandidatoService;
import com.grupo04pj01.urna.services.VotoService;
import com.grupo04pj01.urna.services.impl.ApuracaoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/voto")
public class VotoController {
    private final VotoService votoService;
    private final ApuracaoService apuracaoService;

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_ELEITOR')")
    public ResponseEntity<Void> votar(@RequestBody BuscaCandidatoDTO chapa){
     votoService.votar(chapa);
     return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public List<CandidatoVotosRecebidosDTO> buscaVotosPorCandidato(){
       List<CandidatoVotosRecebidosDTO> lista= votoService.contarVotosById();
        return lista;
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Void> zerarUrna(){
        votoService.deletarTodosVotos();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
