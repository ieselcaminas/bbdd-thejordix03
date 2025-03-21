package org.ieselcaminas.jpa.controller;

import org.ieselcaminas.jpa.entity.Galaxia;
import org.ieselcaminas.jpa.entity.Planeta;
import org.ieselcaminas.jpa.repository.GalaxiaRepository;
import org.ieselcaminas.jpa.repository.PlanetaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class PlanetaGalaxiaController {

    private final GalaxiaRepository galaxiaRepository;
    private final PlanetaRepository planetaRepository;
    private final Scanner scanner;

    public PlanetaGalaxiaController(GalaxiaRepository galaxiaRepository, PlanetaRepository planetaRepository) {
        this.galaxiaRepository = galaxiaRepository;
        this.planetaRepository = planetaRepository;
        this.scanner = new Scanner(System.in);
    }

    public void ejecutarMenu() {
        while (true) {
            System.out.println("\n📌 Menú:");
            System.out.println("1. Agregar galaxia");
            System.out.println("2. Agregar planeta");
            System.out.println("3. Listar planetas por nombre");
            System.out.println("4. Eliminar galaxia (si está vacía)");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> agregarGalaxia();
                case 2 -> agregarPlaneta();
                case 3 -> listarPlanetas();
                case 4 -> eliminarGalaxia();
                case 5 -> { return; }
                default -> System.out.println("❌ Opción inválida");
            }
        }
    }

    private void agregarGalaxia() {
        System.out.print("🌌 Ingresa el nombre de la galaxia: ");
        String nombre = scanner.nextLine();
        galaxiaRepository.save(new Galaxia(nombre));
        System.out.println("✅ Galaxia agregada.");
    }

    private void agregarPlaneta() {
        System.out.print("🌍 Ingresa el nombre del planeta: ");
        String nombrePlaneta = scanner.nextLine();
        System.out.print("🌌 Ingresa el nombre de la galaxia: ");
        String nombreGalaxia = scanner.nextLine();

        Optional<Galaxia> galaxia = galaxiaRepository.findByNombre(nombreGalaxia);
        if (galaxia.isPresent()) {
            planetaRepository.save(new Planeta(nombrePlaneta, galaxia.get()));
            System.out.println("✅ Planeta agregado.");
        } else {
            System.out.println("❌ La galaxia no existe.");
        }
    }

    private void listarPlanetas() {
        System.out.print("🔎 Ingresa parte del nombre del planeta: ");
        String nombre = scanner.nextLine();
        List<Planeta> planetas = planetaRepository.findByNombreContainingIgnoreCase(nombre);
        if (planetas.isEmpty()) {
            System.out.println("❌ No se encontraron planetas.");
        } else {
            planetas.forEach(p -> System.out.println("🌍 " + p.getNombre() + " (Galaxia: " + p.getGalaxia().getNombre() + ")"));
        }
    }

    private void eliminarGalaxia() {
        System.out.print("🗑 Ingresa el nombre de la galaxia a eliminar: ");
        String nombre = scanner.nextLine();

        Optional<Galaxia> galaxia = galaxiaRepository.findByNombreConPlanetas(nombre);

        if (galaxia.isPresent()) {
            if (galaxia.get().getPlanetas().isEmpty()) {
                galaxiaRepository.delete(galaxia.get());
                System.out.println("✅ Galaxia eliminada.");
            } else {
                System.out.println("❌ No se puede eliminar (tiene planetas).");
            }
        } else {
            System.out.println("❌ La galaxia no existe.");
        }
    }

}
