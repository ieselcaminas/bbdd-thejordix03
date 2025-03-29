package com.example.videojuegos.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Plataforma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    
    @ManyToMany(mappedBy = "plataformas")
    private List<Juego> juegos;

    // Getters y Setters
}
