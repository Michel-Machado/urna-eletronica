package com.grupo04pj01.urna.controllers;

import com.grupo04pj01.urna.DTO.ApuracaoDTO;
import com.grupo04pj01.urna.DTO.BuscaCandidatoDTO;
import com.grupo04pj01.urna.DTO.CandidatoVotosRecebidosDTO;
import com.grupo04pj01.urna.services.ApuracaoService;
import com.grupo04pj01.urna.services.VotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/apuracao")
public class ApuracaoController {
    private final ApuracaoService apuracaoService;



    @GetMapping()
    public ApuracaoDTO buscaApuração(){
        ApuracaoDTO lista= apuracaoService.verificaApuracao();
        return lista;
    }

    @GetMapping("/status")
    public ResponseEntity<Boolean> buscaStatusApuracao(){
        Boolean status = apuracaoService.getStatus();
        return ResponseEntity.ok().body(status);
    }

    @PostMapping
    public ResponseEntity<Void> alterarLiberacaoUrna(@RequestParam Boolean isApuracaoLiberada){
        apuracaoService.alterarStatusApuracao(isApuracaoLiberada);

        return ResponseEntity.ok().build();
    }


}
