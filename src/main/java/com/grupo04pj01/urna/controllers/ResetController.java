package com.grupo04pj01.urna.controllers;

import com.grupo04pj01.urna.DTO.ApuracaoDTO;
import com.grupo04pj01.urna.DTO.SenhaRequest;
import com.grupo04pj01.urna.services.ApuracaoService;
import com.grupo04pj01.urna.services.ResetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/reset")
public class ResetController {
    private final ResetService resetService;


    @DeleteMapping
    public ResponseEntity<Void> resetar(@RequestBody SenhaRequest senha){
        resetService.reset(senha);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
