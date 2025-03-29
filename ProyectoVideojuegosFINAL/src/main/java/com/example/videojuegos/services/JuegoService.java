package com.example.videojuegos.services;

import com.example.videojuegos.model.Juego;
import com.example.videojuegos.repository.JuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JuegoService {

    @Autowired
    private JuegoRepository juegoRepository;

    public List<Juego> obtenerTodos() {
        return juegoRepository.findAll();
    }

    public Juego obtenerPorId(Long id) {
        return juegoRepository.findById(id).orElse(null);
    }

    public Juego crear(Juego juego) {
        return juegoRepository.save(juego);
    }

    public Juego actualizar(Juego juego) {
        return juegoRepository.save(juego);
    }

    public void eliminar(Long id) {
        juegoRepository.deleteById(id);
    }
}
