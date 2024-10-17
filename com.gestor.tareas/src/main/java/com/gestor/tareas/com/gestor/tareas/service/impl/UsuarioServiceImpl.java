package com.gestor.tareas.com.gestor.tareas.service.impl;

import com.gestor.tareas.com.gestor.tareas.dto.UsuarioRegistroDTO;
import com.gestor.tareas.com.gestor.tareas.model.Rol;
import com.gestor.tareas.com.gestor.tareas.model.Usuario;
import com.gestor.tareas.com.gestor.tareas.repository.IUsuarioRepository;
import com.gestor.tareas.com.gestor.tareas.service.EncriptacionContrasenaService;
import com.gestor.tareas.com.gestor.tareas.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements IUsuarioService {


    private final EncriptacionContrasenaService encriptacionContrasenaService;
    @Autowired
    @Lazy
    public UsuarioServiceImpl(EncriptacionContrasenaService encriptacionContrasenaService){
        this.encriptacionContrasenaService = encriptacionContrasenaService;
    }

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public Usuario Save(UsuarioRegistroDTO usuarioRegistroDTO) {
        Usuario usuario = new Usuario();
        usuario.setEmail(usuarioRegistroDTO.getEmail());
        usuario.setNombre(usuarioRegistroDTO.getNombre());
        usuario.setApellido(usuarioRegistroDTO.getApellido());
        usuario.setPassword(encriptacionContrasenaService.encryptPassword(usuarioRegistroDTO.getPassword()));

        Rol rol = new Rol("ROLE_USER");
        usuario.setRoles(Arrays.asList(rol));

        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario findByEmail(String email) {

        return usuarioRepository.findByEmail(email);
    }
    /*
    public Usuario Save(UsuarioRegistroDTO usuarioRegistroDTO) {
        Usuario usuario = new Usuario(usuarioRegistroDTO.getNombre(),
                usuarioRegistroDTO.getApellido(),
                usuarioRegistroDTO.getEmail(),
                usuarioRegistroDTO.getPassword(),
                Arrays.asList(new Rol("ROLE_USER")));
        return usuarioRepository.save(usuario);
    }
*/

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario o password inválidos");
        }

        return new User(usuario.getEmail(), usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles) {
        return roles.stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
                .collect(Collectors.toList());
    }
}
