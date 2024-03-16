package com.grupo04pj01.urna.services.impl;

import com.grupo04pj01.urna.models.EleitorPresenteModel;
import com.grupo04pj01.urna.repositories.EleitorPresenteRepository;
import com.grupo04pj01.urna.services.EleitorPresenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EleitorPresenteServiceImpl implements EleitorPresenteService{

    private final EleitorPresenteRepository eleitorPresenteRepository;

    @Override
    public List<EleitorPresenteModel> buscarEleitoresPresentes() {

        return eleitorPresenteRepository.findAll();
    }

    @Override
    public void cadastraEleitorPresente(EleitorPresenteModel eleitorPresenteModel) {
         eleitorPresenteRepository.save(eleitorPresenteModel);
    }

    @Override
    public void limpaListaPresenca() {
        eleitorPresenteRepository.deleteAll();
    }
}
