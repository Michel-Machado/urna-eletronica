package com.grupo04pj01.urna.controllers;

import com.grupo04pj01.urna.DTO.ResponseCadastroEleitor;
import com.grupo04pj01.urna.models.EleitorModel;
import com.grupo04pj01.urna.services.EleitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/eleitor")
public class EleitorController {
    private final EleitorService eleitorService;

    @PostMapping
    public ResponseEntity<ResponseCadastroEleitor> cadastraEleitor(@RequestBody List<EleitorModel> eleitorModel){
        ResponseCadastroEleitor response = eleitorService.criaEleitor(eleitorModel);
        return  ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @GetMapping
    public ResponseEntity<List<EleitorModel>> buscaTodosEleitores(@RequestParam(required = false) String nome,
                                                                  @RequestParam(required = false) String ra){

       List<EleitorModel> eleitorModels= eleitorService.buscarEleitores(nome,ra);
       return ResponseEntity.status(HttpStatus.OK).body(eleitorModels);
    }

    @PostMapping("verificar/{ra}")
    public ResponseEntity<Void> liberaEleitor(@PathVariable String ra){
        eleitorService.liberarEleitor(ra);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{ra}")
    public ResponseEntity<EleitorModel> buscaEleitorByRa(@PathVariable String ra){
      EleitorModel eleitor=  eleitorService.buscaEleitorByRa(ra);
        return ResponseEntity.status(HttpStatus.OK).body(eleitor);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletaTodoseleitores(){
        eleitorService.deleteAll();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{ra}")
    public ResponseEntity<Void> deletaEleitorById(@PathVariable String ra){
        eleitorService.deleteByRa(ra);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    public ResponseEntity<EleitorModel> atualizaEleitor(@RequestBody EleitorModel eleitorModel){
        EleitorModel eleitorAlterado = eleitorService.update(eleitorModel);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(eleitorAlterado);
    }
}
