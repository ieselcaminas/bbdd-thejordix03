package com.example.videojuegos.services;

import com.example.videojuegos.model.Juego;
import com.example.videojuegos.repository.JuegoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JuegoService {

    private final JuegoRepository juegoRepository;

    public JuegoService(JuegoRepository juegoRepository) {
        this.juegoRepository = juegoRepository;
    }

    // Método para crear un juego
    public void crear(Juego juego) {
        juegoRepository.save(juego);
    }

    // Método para obtener todos los juegos
    public List<Juego> obtenerTodos() {
        return juegoRepository.findAll();
    }

    // Método para eliminar un juego por ID
    public void eliminar(Long id) {
        juegoRepository.deleteById(id);
    }

    // Método para obtener un juego por ID
    public Optional<Juego> obtenerPorId(Long id) {
        return juegoRepository.findById(id);
    }
}
