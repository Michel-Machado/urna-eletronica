package com.grupo04pj01.urna.controllers;

import com.grupo04pj01.urna.services.UrnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/liberacao_urna")
public class UrnaController {

    @Autowired
    UrnaService urnaService;

    @GetMapping
    public Boolean isUrnaLiberada(){

        return urnaService.verificaUrna();
    }

    @PostMapping
    public ResponseEntity<Void> alterarLiberacaoUrna(@RequestParam Boolean isUrnaLiberada){
        urnaService.alterarStatusUrna(isUrnaLiberada);

      return ResponseEntity.ok().build();
    }
}
