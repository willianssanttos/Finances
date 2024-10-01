package br.com.sistema.controle.financas.pessoais;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ApplicationFinances {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationFinances.class, args);
    }
}