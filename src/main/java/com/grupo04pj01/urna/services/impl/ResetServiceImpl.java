package com.grupo04pj01.urna.services.impl;

import com.grupo04pj01.urna.DTO.ApuracaoDTO;
import com.grupo04pj01.urna.DTO.CandidatoVotosRecebidosDTO;
import com.grupo04pj01.urna.models.ApuracaoModel;
import com.grupo04pj01.urna.models.UserModel;
import com.grupo04pj01.urna.repositories.ApuracaoRepository;
import com.grupo04pj01.urna.repositories.UserRepository;
import com.grupo04pj01.urna.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResetServiceImpl implements ResetService {

    private final EleitorService eleitorService;
    private final VotoService votoService;
    private final EleitorPresenteService eleitorPresenteService;
    private final CandidatoService candidatoService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    @Override
    public void reset() {
        eleitorService.deleteAll();
        votoService.deletarTodosVotos();
        eleitorPresenteService.limpaListaPresenca();
        candidatoService.deleteAllCandidatos();
        var users =userRepository.findAll();
        for (UserModel user: users) {
            user.setPassword(passwordEncoder.encode("0000"));
            userRepository.save(user);
        }
    }
}
