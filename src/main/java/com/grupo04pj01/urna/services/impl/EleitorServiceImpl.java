package com.grupo04pj01.urna.services.impl;

import com.grupo04pj01.urna.models.EleitorModel;
import com.grupo04pj01.urna.models.LoginModel;
import com.grupo04pj01.urna.repositories.EleitorRepository;
import com.grupo04pj01.urna.repositories.LoginRepository;
import com.grupo04pj01.urna.services.EleitorService;
import com.grupo04pj01.urna.services.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EleitorServiceImpl implements EleitorService {

    private final EleitorRepository eleitorRepository;


    @Override
    public EleitorModel criaEleitor(EleitorModel eleitorModel) {
        EleitorModel eleitor= eleitorRepository.save(eleitorModel);
        return eleitor;
    }

    @Override
    public List<EleitorModel> buscarEleitores() {
       List<EleitorModel> eleitorModelList= eleitorRepository.findAll();
        return eleitorModelList;
    }
}
