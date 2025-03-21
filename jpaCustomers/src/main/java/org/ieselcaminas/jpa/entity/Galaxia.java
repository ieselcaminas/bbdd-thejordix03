package org.ieselcaminas.jpa.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "galaxias")
public class Galaxia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @OneToMany(mappedBy = "galaxia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Planeta> planetas;

    public Galaxia() {}

    public Galaxia(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public List<Planeta> getPlanetas() { return planetas; }
}
