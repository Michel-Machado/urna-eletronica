package com.grupo04pj01.urna.configuration;

import com.grupo04pj01.urna.models.*;
import com.grupo04pj01.urna.models.enums.Role;
import com.grupo04pj01.urna.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInit implements CommandLineRunner {

    private final UrnaRepository urnaRepository;
    private final CandidatoRepository candidatoRepository;
    private final ApuracaoRepository apuracaoRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {


        if (urnaRepository.findAll().isEmpty()) {
            UrnaModel urna = new UrnaModel(1L,false);
            urnaRepository.save(urna);
        }else {
            urnaRepository.findById(1L).get().setIsUrnaLiberada(false);
        }

        if (apuracaoRepository.findAll().isEmpty()){
            ApuracaoModel apuracao = new ApuracaoModel(1L,false);
            apuracaoRepository.save(apuracao);
        } else {
            apuracaoRepository.findById(1L).get().setIsApuracaoLiberada(false);
        }

        if ( userRepository.findAll().isEmpty()){
            UserModel adm = new UserModel(1L, "admin", passwordEncoder.encode("0000"), Role.ADMIN);
            UserModel mes = new UserModel(2L, "mesario",passwordEncoder.encode("0000"), Role.MESARIO);
            UserModel ele = new UserModel(3L, "eleitor",passwordEncoder.encode("0000"), Role.ELEITOR);
            userRepository.save(adm);
            userRepository.save(mes);
            userRepository.save(ele);
        }

        if (!candidatoRepository.findByChapa("0").isPresent()){
            CandidatoModel votosNulos= new CandidatoModel();
            votosNulos.setChapa("0");
            votosNulos.setNome("Brancos & Nulos");
            candidatoRepository.save(votosNulos);
        }
    }
}
