package com.grupo04pj01.urna.configuration;

import com.grupo04pj01.urna.models.UrnaModel;
import com.grupo04pj01.urna.repositories.UrnaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInit implements CommandLineRunner {

    private final UrnaRepository urnaRepository;
    @Override
    public void run(String... args) throws Exception {
        if (urnaRepository.count() == 0) {
            UrnaModel urna = new UrnaModel(1L,false);
            urnaRepository.save(urna);

        }
    }
}
