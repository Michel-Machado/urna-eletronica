package com.grupo04pj01.urna.configuration;

import com.grupo04pj01.urna.models.CandidatoModel;
import com.grupo04pj01.urna.models.UrnaModel;
import com.grupo04pj01.urna.repositories.CandidatoRepository;
import com.grupo04pj01.urna.repositories.UrnaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInit implements CommandLineRunner {

    private final UrnaRepository urnaRepository;
    private final CandidatoRepository candidatoRepository;

    @Override
    public void run(String... args) throws Exception {
        if (urnaRepository.count() == 0) {
            UrnaModel urna = new UrnaModel(1L,false);
            urnaRepository.save(urna);
        }else {
            urnaRepository.findById(1L).get().setIsUrnaLiberada(false);
        }

        if (!candidatoRepository.findByChapa("0").isPresent()){
            CandidatoModel votosNulos= new CandidatoModel();
            votosNulos.setChapa("0");
            votosNulos.setNome("Brancos & Nulos");
            candidatoRepository.save(votosNulos);
        }
    }
}
