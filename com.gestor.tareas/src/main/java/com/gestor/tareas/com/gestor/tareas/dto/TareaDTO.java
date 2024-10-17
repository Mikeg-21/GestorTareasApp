package com.gestor.tareas.com.gestor.tareas.dto;



import com.gestor.tareas.com.gestor.tareas.model.Usuario;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class TareaDTO {


    private Long idTareas;


    private String titulo;


    private String descripcion;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaLimite = new Date();

    private boolean completada;

    private Long usuarioId;

    public TareaDTO() {
    }

    public TareaDTO(Long idTareas, String titulo, String descripcion, Date fechaLimite, Long usuarioId, boolean completada) {
        this.idTareas = idTareas;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaLimite = fechaLimite;
        this.usuarioId = usuarioId;
        this.completada = completada;
    }

}
