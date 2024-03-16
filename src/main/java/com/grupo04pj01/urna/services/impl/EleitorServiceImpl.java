package com.grupo04pj01.urna.services.impl;

import com.grupo04pj01.urna.exceptions.BusinessException;
import com.grupo04pj01.urna.exceptions.NotFoundException;
import com.grupo04pj01.urna.models.EleitorModel;
import com.grupo04pj01.urna.models.EleitorPresenteModel;
import com.grupo04pj01.urna.repositories.EleitorPresenteRepository;
import com.grupo04pj01.urna.repositories.EleitorRepository;
import com.grupo04pj01.urna.repositories.UrnaRepository;
import com.grupo04pj01.urna.services.EleitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EleitorServiceImpl implements EleitorService {

    private final EleitorRepository eleitorRepository;
    private final EleitorPresenteRepository eleitorPresenteRepository;
    private final UrnaServiceImpl urnaService;



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
       urnaService.alterarStatusUrna(true);

    }

    public EleitorModel validarEleitor(Optional<EleitorModel> eleitorModel){
        if (eleitorModel.isEmpty()) throw new NotFoundException("Eleitor não cadastrado");

        return eleitorModel.get();
    }    public void validarPresenca(Optional<EleitorPresenteModel> eleitorModel){
        if (eleitorModel.isPresent()) throw new BusinessException("Eleitor já votou");

    }

    @Override
    public void deleteByRa(String ra) {
       EleitorModel model= validaEleitor(eleitorRepository.findEleitorModelByRa(ra));
       eleitorRepository.delete(model);
    }

    @Override
    public EleitorModel buscaEleitorByRa(String ra) {
        Optional<EleitorModel> eleitor= eleitorRepository.findEleitorModelByRa(ra);
        EleitorModel model = validaEleitor(eleitor);

        return model;
    }

    @Override
    public void deleteAll() {
        eleitorRepository.deleteAll();
    }

    private EleitorModel validaEleitor(Optional<EleitorModel> eleitorModelOptional){
        if (eleitorModelOptional.isEmpty()) throw new RuntimeException("Eleitor não encontrado");

        return eleitorModelOptional.get();
    }
}
