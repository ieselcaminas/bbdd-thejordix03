package com.example.videojuegos.services;

import com.example.videojuegos.model.Desarrollador;

import java.util.List;
import java.util.Optional;

public interface DesarrolladorService {

    void crear(Desarrollador desarrollador);

    List<Desarrollador> obtenerTodos();

    void eliminar(Long id);

    Optional<Desarrollador> obtenerPorId(Long id);
}
