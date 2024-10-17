package com.gestor.tareas.com.gestor.tareas.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    public Rol(String nombre) {
        this.nombre = nombre;
    }


    public Rol() {}
}
