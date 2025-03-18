package com.grupo04pj01.urna.controllers;

import com.grupo04pj01.urna.DTO.EleitoresCadastrarDTO;
import com.grupo04pj01.urna.DTO.EleitoresResponseDTO;
import com.grupo04pj01.urna.DTO.ResponseCadastroEleitor;
import com.grupo04pj01.urna.models.EleitorModel;
import com.grupo04pj01.urna.services.EleitorService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/eleitor")
public class EleitorController {
    private final EleitorService eleitorService;

    @PostMapping
    public ResponseEntity<ResponseCadastroEleitor> cadastraEleitor(@RequestBody EleitoresCadastrarDTO eleitoresCadastrarDTO){
        ResponseCadastroEleitor response = eleitorService.criaEleitor(eleitoresCadastrarDTO);

        return  ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @GetMapping
    public ResponseEntity<List<EleitoresResponseDTO>> buscaTodosEleitores(@RequestParam(required = false) String nome,
                                                                          @RequestParam(required = false) String ra){

       List<EleitoresResponseDTO> eleitorModels= eleitorService.buscarEleitores(nome,ra);
       return ResponseEntity.status(HttpStatus.OK).body(eleitorModels);
    }

    @PostMapping("verificar/{ra}")
    public ResponseEntity<Void> liberaEleitor(@PathVariable String ra){
        eleitorService.liberarEleitor(ra);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{ra}")
    public ResponseEntity<EleitoresResponseDTO> buscaEleitorByRa(@PathVariable String ra){
        EleitoresResponseDTO eleitor=  eleitorService.buscaEleitorByRa(ra);
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

    @GetMapping("/export")
    public ResponseEntity<Void> exportarCadastroEleitor(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=eleitores.csv");
        eleitorService.exportarCsv(response.getWriter());

        return ResponseEntity.status(HttpStatus.OK).build();


    }
}
