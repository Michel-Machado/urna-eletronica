package com.grupo04pj01.urna;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.grupo04pj01.urna.repositories")
public class AppConfig {
    // Configurações adicionais podem ser adicionadas aqui, se necessário
}
