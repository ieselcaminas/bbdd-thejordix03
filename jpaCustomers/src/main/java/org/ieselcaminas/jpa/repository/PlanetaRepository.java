package org.ieselcaminas.jpa.repository;

import org.ieselcaminas.jpa.entity.Planeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanetaRepository extends JpaRepository<Planeta, Long> {
    List<Planeta> findByNombreContainingIgnoreCase(String nombre);
}
