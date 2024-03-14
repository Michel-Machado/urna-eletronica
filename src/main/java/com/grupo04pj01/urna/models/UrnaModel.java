package com.grupo04pj01.urna.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Entity
@Table(name = "urna_tb")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UrnaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isUrnaLiberada =false;
}
