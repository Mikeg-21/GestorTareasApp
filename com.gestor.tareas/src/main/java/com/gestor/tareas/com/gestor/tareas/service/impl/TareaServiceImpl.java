package com.gestor.tareas.com.gestor.tareas.service.impl;

import com.gestor.tareas.com.gestor.tareas.dto.TareaDTO;
import com.gestor.tareas.com.gestor.tareas.model.Tarea;
import com.gestor.tareas.com.gestor.tareas.model.Usuario;
import com.gestor.tareas.com.gestor.tareas.repository.ITareaRepository;
import com.gestor.tareas.com.gestor.tareas.repository.IUsuarioRepository;
import com.gestor.tareas.com.gestor.tareas.service.ITareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TareaServiceImpl implements ITareaService {

    @Autowired
    private ITareaRepository tareaRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;


    @Override
    public List<TareaDTO> listarTareasPorUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        List<Tarea> tareas = tareaRepository.findByUsuarioId(usuario.getId());
        return tareas.stream().map(this::convertirADto).collect(Collectors.toList());
    }

    @Override
    public TareaDTO saveTarea(TareaDTO tareaDTO, Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Tarea tarea = new Tarea();
        tarea.setTitulo(tareaDTO.getTitulo());
        tarea.setDescripcion(tareaDTO.getDescripcion());
        tarea.setFechaLimite(tareaDTO.getFechaLimite());
        tarea.setCompletada(tareaDTO.isCompletada());
        tarea.setUsuario(usuario);
        tareaRepository.save(tarea);
        return convertirADto(tarea);
    }

    @Override
    public TareaDTO updateTarea(Long id, TareaDTO tareaDTO) {
        Tarea tarea = tareaRepository.findById(id).orElseThrow(() -> new RuntimeException("Tarea no encontrada"));
        tarea.setTitulo(tareaDTO.getTitulo());
        tarea.setDescripcion(tareaDTO.getDescripcion());
        tarea.setFechaLimite(tareaDTO.getFechaLimite());
        tarea.setCompletada(tareaDTO.isCompletada());
        tareaRepository.save(tarea);
        return convertirADto(tarea);
    }

    @Override
    public void deleteTarea(Long id) {
        tareaRepository.deleteById(id);
    }

    @Override
    public TareaDTO obtenerTareaPorId(Long id) {
        Tarea tarea = tareaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));
        return convertirADto(tarea);
    }


    private TareaDTO convertirADto(Tarea tarea) {
        TareaDTO tareaDTO = new TareaDTO();
        tareaDTO.setIdTareas(tarea.getIdTareas());
        tareaDTO.setTitulo(tarea.getTitulo());
        tareaDTO.setDescripcion(tarea.getDescripcion());
        tareaDTO.setFechaLimite(tarea.getFechaLimite());
        tareaDTO.setCompletada(tarea.isCompletada());
        return tareaDTO;
    }
}
