package com.gestor.tareas.com.gestor.tareas.repository;

import com.gestor.tareas.com.gestor.tareas.model.Tarea;
import com.gestor.tareas.com.gestor.tareas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITareaRepository extends JpaRepository<Tarea, Long> {

    List<Tarea>findByUsuarioId(Long usuarioId);
}
