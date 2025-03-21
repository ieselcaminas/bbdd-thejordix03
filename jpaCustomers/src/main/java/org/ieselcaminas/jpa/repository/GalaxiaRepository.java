package org.ieselcaminas.jpa.repository;

import org.ieselcaminas.jpa.entity.Galaxia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GalaxiaRepository extends JpaRepository<Galaxia, Long> {

    Optional<Galaxia> findByNombre(String nombre);

    // 👇 Este método carga también los planetas (evita LazyInitializationException)
    @Query("SELECT g FROM Galaxia g LEFT JOIN FETCH g.planetas WHERE g.nombre = :nombre")
    Optional<Galaxia> findByNombreConPlanetas(@Param("nombre") String nombre);
}
