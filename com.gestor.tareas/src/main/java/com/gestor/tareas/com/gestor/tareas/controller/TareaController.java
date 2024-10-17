package com.gestor.tareas.com.gestor.tareas.controller;

import com.gestor.tareas.com.gestor.tareas.dto.TareaDTO;
import com.gestor.tareas.com.gestor.tareas.service.ITareaService;
import com.gestor.tareas.com.gestor.tareas.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/tareas")
public class TareaController {

    @Autowired
    private ITareaService tareaService;

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping
    public String listarTareas(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Long usuarioId = usuarioService.findByEmail(username).getId();
        List<TareaDTO> tareas = tareaService.listarTareasPorUsuario(usuarioId);
        model.addAttribute("tareas", tareas);
        return "lista-tareas";
    }

    @GetMapping("/nueva")
    public String mostrarFormularioNuevaTarea(Model model) {
        model.addAttribute("tarea", new TareaDTO());
        return "formulario-tarea";
    }

    @PostMapping("/nueva")
    public String guardarTarea(@Valid @ModelAttribute("tarea") TareaDTO tareaDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "formulario-tarea";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Long usuarioId = usuarioService.findByEmail(username).getId();
        tareaService.saveTarea(tareaDTO, usuarioId);
        return "redirect:/tareas";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarTarea(@PathVariable Long id, Model model) {
        TareaDTO tarea = tareaService.obtenerTareaPorId(id);
        model.addAttribute("tarea", tarea);
        return "formulario-tarea";
    }

    @PostMapping("/editar/{id}")
    public String actualizarTarea(@PathVariable Long id, @Valid @ModelAttribute("tarea") TareaDTO tareaDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "formulario-tarea";
        }
        tareaService.updateTarea(id, tareaDTO);
        return "redirect:/tareas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarTarea(@PathVariable Long id) {
        tareaService.deleteTarea(id);
        return "redirect:/tareas";
    }
}