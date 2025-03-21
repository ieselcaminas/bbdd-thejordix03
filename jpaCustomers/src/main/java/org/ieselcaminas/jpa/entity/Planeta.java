package org.ieselcaminas.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "planetas")
public class Planeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "galaxia_id", nullable = false)
    private Galaxia galaxia;

    public Planeta() {}

    public Planeta(String nombre, Galaxia galaxia) {
        this.nombre = nombre;
        this.galaxia = galaxia;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Galaxia getGalaxia() { return galaxia; }
}
