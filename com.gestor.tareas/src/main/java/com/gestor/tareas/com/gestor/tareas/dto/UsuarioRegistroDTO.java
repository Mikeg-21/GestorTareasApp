package com.gestor.tareas.com.gestor.tareas.dto;

import com.gestor.tareas.com.gestor.tareas.model.Tarea;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UsuarioRegistroDTO {


    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String password;


}
