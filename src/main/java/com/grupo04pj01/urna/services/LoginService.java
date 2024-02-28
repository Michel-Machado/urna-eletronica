package com.grupo04pj01.urna.services;

import com.grupo04pj01.urna.models.LoginModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    ResponseEntity findLogin(LoginModel loginModel);

    LoginModel criaLogin(LoginModel loginModel);
}