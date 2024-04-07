package com.grupo04pj01.urna.services.impl;

import com.grupo04pj01.urna.models.UrnaModel;
import com.grupo04pj01.urna.models.VotosModel;
import com.grupo04pj01.urna.repositories.UrnaRepository;
import com.grupo04pj01.urna.repositories.VotoRepository;
import com.grupo04pj01.urna.services.UrnaService;
import com.grupo04pj01.urna.services.VotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UrnaServiceImpl implements UrnaService {

    private final UrnaRepository urnaRepository;


    @Override
    public Boolean verificaUrna() {
        Long pk = 1L;
        Optional<UrnaModel> urna = urnaRepository.findById(pk);

        return validar(urna).getIsUrnaLiberada();
    }

    @Override
    public void alterarStatusUrna(Boolean isUrnaLiberada) {
        Long pk = 1L;
        Optional<UrnaModel> urna = urnaRepository.findById(pk);
       UrnaModel model= validar(urna);
       model.setIsUrnaLiberada(isUrnaLiberada);
       urnaRepository.save(model);
    }

    private UrnaModel validar(Optional<UrnaModel> objeto) {
        if (objeto.isPresent()) {
            return objeto.get();
        } else throw new RuntimeException("status nao iniciado");

    }
}
