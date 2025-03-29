package com.example.videojuegos.controller;

import com.example.videojuegos.model.Juego;
import com.example.videojuegos.services.JuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/juegos")
public class JuegoController {

    @Autowired
    private JuegoService juegoService;

    @GetMapping
    public List<Juego> obtenerTodos() {
        return juegoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Juego obtenerPorId(@PathVariable Long id) {
        return juegoService.obtenerPorId(id);
    }

    @PostMapping
    public Juego crear(@RequestBody Juego juego) {
        return juegoService.crear(juego);
    }

    @PutMapping("/{id}")
    public Juego actualizar(@PathVariable Long id, @RequestBody Juego juego) {
        juego.setId(id);
        return juegoService.actualizar(juego);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        juegoService.eliminar(id);
    }
}
