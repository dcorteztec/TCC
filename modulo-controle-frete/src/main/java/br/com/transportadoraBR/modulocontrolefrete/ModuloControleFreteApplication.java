package br.com.transportadoraBR.modulocontrolefrete;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableEurekaClient
@EnableJpaAuditing
@SpringBootApplication
public class ModuloControleFreteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModuloControleFreteApplication.class, args);
	}
}
