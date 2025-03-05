package com.grupo04pj01.urna.services.impl;

import com.grupo04pj01.urna.DTO.LoginRequest;
import com.grupo04pj01.urna.DTO.LoginResponse;
import com.grupo04pj01.urna.models.UserModel;
import com.grupo04pj01.urna.repositories.UserRepository;
import com.grupo04pj01.urna.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@EnableMethodSecurity
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtEncoder jwtEncoder;

    public LoginResponse login(LoginRequest loginRequest){
        Optional<UserModel> user = userRepository.findByUserName(loginRequest.username());


        if (user.isEmpty() || !user.get().isLoginCorrect(loginRequest,bCryptPasswordEncoder)){
            throw new BadCredentialsException("Usuário ou senha inválidos");
        }

        var now = Instant.now();
        var expiresIn = 300L;
        var scope = user.get().getRole();

        var claims = JwtClaimsSet.builder()

                .issuer("urna")
                .subject(user.get().getUserId().toString())
                .claim("scope", scope)
                .expiresAt(now.plusSeconds(expiresIn))
                .issuedAt(now)
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new LoginResponse(jwtValue, expiresIn);
    }

}
