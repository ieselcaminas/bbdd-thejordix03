package com.example.videojuegos.controller;

import com.example.videojuegos.model.Juego;
import com.example.videojuegos.services.JuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/juegos")
public class JuegoController {

    private final JuegoService juegoService;

    @Autowired
    public JuegoController(JuegoService juegoService) {
        this.juegoService = juegoService;
    }

    // Método para obtener un juego por ID
    @GetMapping("/{id}")
    public Juego obtenerJuegoPorId(@PathVariable Long id) {
        Optional<Juego> juego = juegoService.obtenerPorId(id);
        if (juego.isPresent()) {
            return juego.get();
        } else {
            throw new RuntimeException("Juego no encontrado con ID: " + id);
        }
    }

    // Otros métodos para gestionar juegos pueden ir aquí...
}
