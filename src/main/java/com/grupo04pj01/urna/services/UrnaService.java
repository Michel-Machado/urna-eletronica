package com.grupo04pj01.urna.services;

import com.grupo04pj01.urna.models.VotosModel;
import org.springframework.stereotype.Service;

@Service
public interface UrnaService {


    Boolean verificaUrna();

    void alterarStatusUrna(Boolean isUrnaLiberada);
}
