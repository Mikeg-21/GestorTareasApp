package com.gestor.tareas.com.gestor.tareas.service;

import com.gestor.tareas.com.gestor.tareas.dto.UsuarioRegistroDTO;
import com.gestor.tareas.com.gestor.tareas.model.Usuario;
import jakarta.validation.constraints.Email;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUsuarioService extends UserDetailsService {

     Usuario Save(UsuarioRegistroDTO usuarioRegistroDTO);

     Usuario findByEmail(String email);
}
