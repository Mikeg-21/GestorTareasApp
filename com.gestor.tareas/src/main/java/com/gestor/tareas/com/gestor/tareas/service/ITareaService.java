package com.gestor.tareas.com.gestor.tareas.service;

import com.gestor.tareas.com.gestor.tareas.dto.TareaDTO;
import com.gestor.tareas.com.gestor.tareas.model.Tarea;

import java.util.List;

public interface ITareaService {



    List<TareaDTO> listarTareasPorUsuario(Long usuarioId);
    TareaDTO saveTarea(TareaDTO tareaDTO, Long usuarioId);
    TareaDTO updateTarea(Long id, TareaDTO tareaDTO);
    void deleteTarea(Long id);

    TareaDTO obtenerTareaPorId(Long id);
}
