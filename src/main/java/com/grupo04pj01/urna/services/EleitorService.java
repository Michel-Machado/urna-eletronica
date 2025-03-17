package com.grupo04pj01.urna.services;

import com.grupo04pj01.urna.DTO.EleitoresCadastrarDTO;
import com.grupo04pj01.urna.DTO.EleitoresResponseDTO;
import com.grupo04pj01.urna.DTO.ResponseCadastroEleitor;
import com.grupo04pj01.urna.models.EleitorModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EleitorService {

   ResponseCadastroEleitor criaEleitor(EleitoresCadastrarDTO eleitoresCadastrarDTO );

    List<EleitoresResponseDTO> buscarEleitores(String nome, String ra);

    void liberarEleitor(String ra);

 EleitoresResponseDTO buscaEleitorByRa(String ra);

    void deleteAll();

    void deleteByRa(String ra);

    EleitorModel update(EleitorModel eleitorModel);
}
