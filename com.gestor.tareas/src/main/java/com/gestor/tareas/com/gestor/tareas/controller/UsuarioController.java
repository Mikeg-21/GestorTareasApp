package com.gestor.tareas.com.gestor.tareas.controller;

import com.gestor.tareas.com.gestor.tareas.dto.UsuarioRegistroDTO;
import com.gestor.tareas.com.gestor.tareas.service.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
//@RequestMapping("/registro")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @ModelAttribute("usuario")
    public UsuarioRegistroDTO retornarNuevoUsarioRegistroDTO() {
        return new UsuarioRegistroDTO();
    }

    @GetMapping("/login")
    public String mostrarFormularioLogin() {
        return "login";
    }

    @GetMapping("/registro")
    public String mostrarFormularioDeRegistro() {
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarCuentaDeUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO registroDTO) {
        usuarioService.Save(registroDTO);
        return "redirect:/registro?exito";


    }



}

// th:text="${#authentication.principal.name}"