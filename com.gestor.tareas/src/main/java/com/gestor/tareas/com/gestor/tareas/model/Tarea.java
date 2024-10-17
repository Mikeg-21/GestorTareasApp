package com.gestor.tareas.com.gestor.tareas.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tarea")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTareas;

    @Column
    private String titulo;

    @Column
    private String descripcion;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaCreacion = new Date();

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaLimite = new Date();

    private boolean completada;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;




    public Tarea(String titulo, String descripcion, Date fechaLimite) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaLimite = fechaLimite;
    }


}
