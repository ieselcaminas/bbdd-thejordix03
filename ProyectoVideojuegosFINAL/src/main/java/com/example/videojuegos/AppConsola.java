package com.example.videojuegos;

import com.example.videojuegos.model.Desarrollador;
import com.example.videojuegos.model.Juego;
import com.example.videojuegos.model.Plataforma;
import com.example.videojuegos.repository.DesarrolladorRepository;
import com.example.videojuegos.repository.JuegoRepository;
import com.example.videojuegos.repository.PlataformaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AppConsola {

    @Autowired
    private PlataformaRepository plataformaRepository;

    @Autowired
    private DesarrolladorRepository desarrolladorRepository;

    @Autowired
    private JuegoRepository juegoRepository;

    public void run() {
        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 0) {
            mostrarMenuPrincipal();
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    gestionarPlataformas(sc);
                    break;
                case 2:
                    gestionarDesarrolladores(sc);
                    break;
                case 3:
                    gestionarJuegos(sc);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private void mostrarMenuPrincipal() {
        System.out.println("=== MENÚ PRINCIPAL ===");
        System.out.println("1. Gestión de plataformas");
        System.out.println("2. Gestión de desarrolladores");
        System.out.println("3. Gestión de juegos");
        System.out.println("0. Salir");
        System.out.print("Selecciona una opción: ");
    }

    public void gestionarJuegos(Scanner sc) {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- MENÚ DE GESTIÓN DE JUEGOS ---");
            System.out.println("1. Crear juego");
            System.out.println("2. Listar juegos");
            System.out.println("3. Eliminar juego");
            System.out.println("0. Volver al menú principal");
            System.out.print("Selecciona una opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    crearJuego(sc);
                    break;
                case 2:
                    listarJuegos();
                    break;
                case 3:
                    eliminarJuego(sc);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
    public void crearJuego(Scanner sc) {
        System.out.print("Introduce el nombre del juego: ");
        String nombreJuego = sc.nextLine();

        System.out.print("Introduce el ID de la plataforma: ");
        Long plataformaId = Long.parseLong(sc.nextLine());
        Plataforma plataforma = plataformaRepository.findById(plataformaId).orElse(null);

        if (plataforma == null) {
            System.out.println("Plataforma no encontrada.");
            return;
        }

        System.out.print("Introduce el ID del desarrollador: ");
        Long desarrolladorId = Long.parseLong(sc.nextLine());
        Desarrollador desarrollador = desarrolladorRepository.findById(desarrolladorId).orElse(null);

        if (desarrollador == null) {
            System.out.println("Desarrollador no encontrado.");
            return;
        }

        Juego juego = new Juego();
        juego.setNombre(nombreJuego);
        juego.setPlataforma(plataforma);
        juego.setDesarrollador(desarrollador);

        juegoRepository.save(juego);
        System.out.println("Juego creado con éxito.");
    }


    public void listarJuegos() {
        System.out.println("Juegos disponibles:");
        for (Juego juego : juegoRepository.findAll()) {
            if (juego.getPlataforma() != null && juego.getDesarrollador() != null) {
                System.out.println("ID: " + juego.getId() + ", Nombre: " + juego.getNombre() +
                        ", Plataforma: " + juego.getPlataforma().getNombre() +
                        ", Desarrollador: " + juego.getDesarrollador().getNombre());
            } else {
                System.out.println("Juego con datos incompletos.");
            }
        }
    }


    public void eliminarJuego(Scanner sc) {
        System.out.print("Introduce el ID del juego a eliminar: ");
        Long juegoId = Long.parseLong(sc.nextLine());
        juegoRepository.deleteById(juegoId);
        System.out.println("Juego eliminado con éxito.");
    }

    public void gestionarPlataformas(Scanner sc) {
        // Implementa el manejo de plataformas si es necesario
    }

    public void gestionarDesarrolladores(Scanner sc) {
        // Implementa el manejo de desarrolladores si es necesario
    }
}
