package com.grupo04pj01.urna.services;

import com.grupo04pj01.urna.models.EleitorModel;
import com.grupo04pj01.urna.models.EleitorPresenteModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EleitorPresenteService {


    List<EleitorPresenteModel> buscarEleitoresPresentes();

    void cadastraEleitorPresente(EleitorPresenteModel eleitorPresenteModel);
}
