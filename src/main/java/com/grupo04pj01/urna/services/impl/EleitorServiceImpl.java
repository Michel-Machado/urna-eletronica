package com.grupo04pj01.urna.services.impl;

import com.grupo04pj01.urna.models.EleitorModel;
import com.grupo04pj01.urna.models.EleitorPresenteModel;
import com.grupo04pj01.urna.models.LoginModel;
import com.grupo04pj01.urna.repositories.EleitorPresenteRepository;
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
    private final EleitorPresenteRepository eleitorPresenteRepository;



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

    @Override
    public void liberarEleitor(String ra) {
       Optional<EleitorModel> eleitorModel= eleitorRepository.findEleitorModelByRa(ra);
       EleitorModel eleitorCadastrado= validarEleitor(eleitorModel);

       Optional<EleitorPresenteModel> eleitorPresenteModel= eleitorPresenteRepository.findByEleitorModel(eleitorCadastrado);
       validarPresenca(eleitorPresenteModel);

       eleitorPresenteRepository.save(new EleitorPresenteModel(eleitorCadastrado));

    }

    public EleitorModel validarEleitor(Optional<EleitorModel> eleitorModel){
        if (eleitorModel.isEmpty()) throw new RuntimeException("Eleitor não cadastrado");

        return eleitorModel.get();
    }    public void validarPresenca(Optional<EleitorPresenteModel> eleitorModel){
        if (eleitorModel.isPresent()) throw new RuntimeException("Eleitor já votou");

    }

}
