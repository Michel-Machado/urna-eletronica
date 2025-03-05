package com.grupo04pj01.urna.models;

import com.grupo04pj01.urna.DTO.LoginRequest;
import com.grupo04pj01.urna.models.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users_tb")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userName;

    private String password;

    private Role role;


    public boolean isLoginCorrect(LoginRequest loginRequest, PasswordEncoder passwordEncoder) {
        var isCorrect = passwordEncoder.matches(loginRequest.password(), this.password);
        return isCorrect;
    }
}
