package com.example.videojuegos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProyectoVideojuegosFinalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProyectoVideojuegosFinalApplication.class, args);
    }

    // Configurar el CommandLineRunner para ejecutar el menú
    @Bean
    public CommandLineRunner run(AppConsola appConsola) {
        return args -> {
            appConsola.run();  // Aquí se inicia el menú de consola
        };
    }
}
