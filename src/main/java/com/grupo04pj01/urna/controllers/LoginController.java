package com.grupo04pj01.urna.controllers;

import com.grupo04pj01.urna.models.LoginModel;
import com.grupo04pj01.urna.services.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {
    private final LoginService loginService;

    @PostMapping
    public LoginModel criaUsuario(@RequestBody LoginModel loginModel){
      return loginService.criaLogin(loginModel);

    }

    @GetMapping
    public ResponseEntity<LoginModel> buscaLogin(@RequestBody LoginModel loginModel){

        return loginService.findLogin(loginModel);
    }


}
