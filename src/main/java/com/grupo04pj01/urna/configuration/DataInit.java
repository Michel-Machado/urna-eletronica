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


        if (urnaRepository.count() == 0) {
            UrnaModel urna = new UrnaModel(1L,false);
            urnaRepository.save(urna);
        }else {
            urnaRepository.findById(1L).get().setIsUrnaLiberada(false);
        }

        if (apuracaoRepository.count() == 0){
            ApuracaoModel apuracao = new ApuracaoModel(1L,false);
            apuracaoRepository.save(apuracao);
        } else {
            apuracaoRepository.findById(1L).get().setIsApuracaoLiberada(false);
        }

        if ( userRepository.count() == 0){
            UserModel user = new UserModel(1L, "admin", passwordEncoder.encode("123"), Role.ADMIN);
            UserModel user2 = new UserModel(2L, "mesario",passwordEncoder.encode("1234"), Role.MESARIO);
            userRepository.save(user);
            userRepository.save(user2);
        }

        if (!candidatoRepository.findByChapa("0").isPresent()){
            CandidatoModel votosNulos= new CandidatoModel();
            votosNulos.setChapa("0");
            votosNulos.setNome("Brancos & Nulos");
            candidatoRepository.save(votosNulos);
        }
    }
}
