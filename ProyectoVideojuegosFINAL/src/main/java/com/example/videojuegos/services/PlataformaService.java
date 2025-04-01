package com.example.videojuegos.services;

import com.example.videojuegos.model.Plataforma;

import java.util.List;
import java.util.Optional;

public interface PlataformaService {

    void crear(Plataforma plataforma);

    List<Plataforma> obtenerTodos();

    void eliminar(Long id);

    Optional<Plataforma> obtenerPorId(Long id);
}
