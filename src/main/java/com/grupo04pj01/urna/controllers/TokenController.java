package com.grupo04pj01.urna.controllers;

import com.grupo04pj01.urna.DTO.LoginRequest;
import com.grupo04pj01.urna.DTO.LoginResponse;
import com.grupo04pj01.urna.services.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/auth")
public class TokenController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
            var response = userService.login(loginRequest);
        return ResponseEntity.ok(response);
    }
}
