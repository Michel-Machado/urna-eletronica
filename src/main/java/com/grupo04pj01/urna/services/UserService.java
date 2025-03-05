package com.grupo04pj01.urna.services;

import com.grupo04pj01.urna.DTO.ApuracaoDTO;
import com.grupo04pj01.urna.DTO.LoginRequest;
import com.grupo04pj01.urna.DTO.LoginResponse;
import com.grupo04pj01.urna.models.UserModel;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    LoginResponse login(LoginRequest loginRequest);
}
