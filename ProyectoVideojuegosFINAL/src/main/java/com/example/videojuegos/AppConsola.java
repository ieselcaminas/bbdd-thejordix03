package com.example.videojuegos;

import com.example.videojuegos.model.Juego;
import com.example.videojuegos.services.JuegoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class AppConsola implements CommandLineRunner {

    private final JuegoService juegoService;

    public AppConsola(JuegoService juegoService) {
        this.juegoService = juegoService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n=== MENÚ VIDEOJUEGOS ===");
            System.out.println("1. Crear juego");
            System.out.println("2. Listar juegos");
            System.out.println("3. Buscar juego por ID");
            System.out.println("4. Actualizar juego");
            System.out.println("5. Eliminar juego");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // consumir salto de línea

            switch (opcion) {
                case 1:
                    crearJuego(scanner);
                    break;
                case 2:
                    listarJuegos();
                    break;
                case 3:
                    buscarJuego(scanner);
                    break;
                case 4:
                    actualizarJuego(scanner);
                    break;
                case 5:
                    eliminarJuego(scanner);
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }

        scanner.close();
        System.out.println("Aplicación finalizada.");
    }

    private void crearJuego(Scanner scanner) {
        System.out.print("Nombre del juego: ");
        String nombre = scanner.nextLine();
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();

        Juego juego = new Juego();
        juego.setNombre(nombre);
        juego.setDescripcion(descripcion);

        juegoService.crear(juego);
        System.out.println("Juego creado correctamente.");
    }

    private void listarJuegos() {
        List<Juego> juegos = juegoService.obtenerTodos();
        if (juegos.isEmpty()) {
            System.out.println("No hay juegos registrados.");
        } else {
            for (Juego juego : juegos) {
                System.out.println("ID: " + juego.getId() + " | Nombre: " + juego.getNombre() + " | Descripción: " + juego.getDescripcion());
            }
        }
    }

    private void buscarJuego(Scanner scanner) {
        System.out.print("ID del juego: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Juego juego = juegoService.obtenerPorId(id);
        if (juego != null) {
            System.out.println("Juego encontrado: " + juego.getNombre() + " - " + juego.getDescripcion());
        } else {
            System.out.println("Juego no encontrado.");
        }
    }

    private void actualizarJuego(Scanner scanner) {
        System.out.print("ID del juego a actualizar: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Juego juegoExistente = juegoService.obtenerPorId(id);
        if (juegoExistente == null) {
            System.out.println("Juego no encontrado.");
            return;
        }

        System.out.print("Nuevo nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Nueva descripción: ");
        String descripcion = scanner.nextLine();

        juegoExistente.setNombre(nombre);
        juegoExistente.setDescripcion(descripcion);

        juegoService.actualizar(juegoExistente);
        System.out.println("Juego actualizado correctamente.");
    }

    private void eliminarJuego(Scanner scanner) {
        System.out.print("ID del juego a eliminar: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        juegoService.eliminar(id);
        System.out.println("Juego eliminado correctamente.");
    }
}
