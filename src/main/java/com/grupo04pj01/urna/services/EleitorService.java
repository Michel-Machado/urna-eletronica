package com.grupo04pj01.urna.services;

import com.grupo04pj01.urna.models.EleitorModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EleitorService {

    EleitorModel criaEleitor(EleitorModel eleitorModel);

    List<EleitorModel> buscarEleitores();

    void liberarEleitor(String ra);

    EleitorModel buscaEleitorByRa(String ra);

    void deleteAll();

    void deleteByRa(String ra);
}
