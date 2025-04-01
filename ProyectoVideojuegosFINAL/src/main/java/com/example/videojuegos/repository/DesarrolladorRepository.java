package com.example.videojuegos.repository;

import com.example.videojuegos.model.Desarrollador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesarrolladorRepository extends JpaRepository<Desarrollador, Long> {
}
