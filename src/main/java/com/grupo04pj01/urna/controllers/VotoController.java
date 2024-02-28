package com.grupo04pj01.urna.controllers;

import com.grupo04pj01.urna.models.CandidatoModel;
import com.grupo04pj01.urna.models.VotosModel;
import com.grupo04pj01.urna.services.CandidatoService;
import com.grupo04pj01.urna.services.VotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/voto")
public class VotoController {
    private final VotoService votoService;

    @PostMapping
    public ResponseEntity<Void> votar(@RequestBody VotosModel votosModel){
     votoService.votar(votosModel);
     return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public Integer buscaVotosPorCandidato(@PathVariable Long id){
       Integer totalVotos= votoService.contarVotosById(id);

        return totalVotos;
    }

    @DeleteMapping
    public ResponseEntity<Void> zerarUrna(){
        votoService.deletarTodosVotos();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
