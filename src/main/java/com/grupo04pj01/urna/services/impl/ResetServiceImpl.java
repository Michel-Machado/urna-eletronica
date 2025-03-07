package com.grupo04pj01.urna.services.impl;

import com.grupo04pj01.urna.DTO.SenhaRequest;
import com.grupo04pj01.urna.models.UserModel;
import com.grupo04pj01.urna.repositories.UserRepository;
import com.grupo04pj01.urna.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResetServiceImpl implements ResetService {


    private final EleitorService eleitorService;
    private final VotoService votoService;
    private final EleitorPresenteService eleitorPresenteService;
    private final CandidatoService candidatoService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${app.key}")
    private String SENHA_ADM ;

    @Override
    public void reset(SenhaRequest senha) {
        if (!senha.senha().matches(SENHA_ADM)){
            throw new BadCredentialsException("Senha de reset incorreta, contate o desenvolvedor do sistema");
        }
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
