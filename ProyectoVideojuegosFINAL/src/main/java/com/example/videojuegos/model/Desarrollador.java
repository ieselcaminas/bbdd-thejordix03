package com.example.videojuegos.model;

import javax.persistence.*;

@Entity
public class Desarrollador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;

    // Getters y Setters
}
