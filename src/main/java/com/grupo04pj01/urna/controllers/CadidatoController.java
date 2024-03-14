package com.grupo04pj01.urna.controllers;

import com.grupo04pj01.urna.models.CandidatoModel;
import com.grupo04pj01.urna.models.EleitorModel;
import com.grupo04pj01.urna.services.CandidatoService;
import com.grupo04pj01.urna.services.EleitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/candidato")
public class CadidatoController {
    private final CandidatoService candidatoService;

    @PostMapping
    public ResponseEntity<CandidatoModel> cadastraEleitor(@RequestBody CandidatoModel candidatoModel){
      CandidatoModel model=candidatoService.criarCandidato(candidatoModel);
      return  ResponseEntity.status(HttpStatus.CREATED).body(model);
    }

    @GetMapping
    public ResponseEntity<List<CandidatoModel>> buscaTodosCandidatos(){
       List<CandidatoModel> eleitorModels= candidatoService.buscarCandidato();
       return ResponseEntity.status(HttpStatus.OK).body(eleitorModels);
    }

    @GetMapping("/{id}")
    public CandidatoModel buscaCandidatoById(@PathVariable Long id){
        CandidatoModel candidatoModel= candidatoService.buscaCandidatoById(id);
        return candidatoModel;
    }

}
