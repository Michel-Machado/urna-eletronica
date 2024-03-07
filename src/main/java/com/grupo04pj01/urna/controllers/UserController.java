package com.grupo04pj01.urna.controllers;

import com.grupo04pj01.urna.DTO.LoginResponseDTO;
import com.grupo04pj01.urna.DTO.RegisterDTO;
import com.grupo04pj01.urna.DTO.UserDTO;
import com.grupo04pj01.urna.models.UserModel;
import com.grupo04pj01.urna.repositories.UserRepository;
import com.grupo04pj01.urna.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/auth")
public class UserController {


    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    //método logar- recebe usuario e senha, e depois autentica.
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserDTO userDTO){

        //recebe login/senha e transforma em token
        var usernamePassword= new UsernamePasswordAuthenticationToken(userDTO.login(),userDTO.password());

        // recebe token e autentica
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((UserModel) auth.getPrincipal());

      return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    private ResponseEntity register(@RequestBody RegisterDTO registerDTO){
        if (userRepository.findByUsuario(registerDTO.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        UserModel newUser = new UserModel(registerDTO.login(),encryptedPassword, registerDTO.role());
        userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
