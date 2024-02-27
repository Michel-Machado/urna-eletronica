package com.grupo04pj01.urna.services.impl;

import com.grupo04pj01.urna.models.LoginModel;
import com.grupo04pj01.urna.repositories.LoginRepository;
import com.grupo04pj01.urna.services.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final LoginRepository loginRepository;

    @Override
    public LoginModel criaLogin(LoginModel loginModel) {
        return loginRepository.save(loginModel);
    }

    @Override
    public ResponseEntity findLogin(LoginModel loginModel) {

        Optional<LoginModel> model = loginRepository.findLoginModelByUsuario(loginModel.getUsuario());
        LoginModel userEncontrado = validarUser(model);

        if(loginModel.getSenha().equals(userEncontrado.getSenha())){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    LoginModel validarUser(Optional<LoginModel> model){
        if (model.isEmpty()) throw new RuntimeException("User nao encontrado!");
        return model.get();
    }


}
