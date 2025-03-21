package org.ieselcaminas.jpa;

import org.ieselcaminas.jpa.controller.PlanetaGalaxiaController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PlanetasApplication implements CommandLineRunner {

	private final PlanetaGalaxiaController controller;

	public PlanetasApplication(PlanetaGalaxiaController controller) {
		this.controller = controller;
	}

	public static void main(String[] args) {
		SpringApplication.run(PlanetasApplication.class, args);


	}

	@Override
	public void run(String... args) {
		controller.ejecutarMenu();
	}
}
