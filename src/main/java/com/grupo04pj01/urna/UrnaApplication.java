package com.grupo04pj01.urna;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.grupo04pj01.urna"})
//@OpenAPIDefinition(
//		info = @Info(
//				title = "Urna Eletrônica",
//				version = "1.0",
//				description = "Projeto de software para sistema de votação",
//				contact = @Contact(name = " grupo04 pj DRT 3 ")
//		),
//		servers = {
//				@Server(url = "http://localhost:80", description = "Servidor Local"),
//				@Server(url = "https://mesariobarao.squareweb.app/", description = "Servidor de Desenvolvimento")
//		}
//)
public class UrnaApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrnaApplication.class, args);
	}

}
