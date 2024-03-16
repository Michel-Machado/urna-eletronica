package com.grupo04pj01.urna.controllers;


import com.grupo04pj01.urna.models.EleitorPresenteModel;
import com.grupo04pj01.urna.services.EleitorPresenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/presenca")
public class EleitorPresenteController {

    private final EleitorPresenteService eleitorPresenteService;

    @GetMapping()
    public ResponseEntity<List<EleitorPresenteModel>> listarEleitoresPresentes(){


        return ResponseEntity.status(HttpStatus.OK).body(eleitorPresenteService.buscarEleitoresPresentes());
    }

    @DeleteMapping()
    public ResponseEntity<Void> limparListaEleitoresPresentes(){
        eleitorPresenteService.limpaListaPresenca();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
