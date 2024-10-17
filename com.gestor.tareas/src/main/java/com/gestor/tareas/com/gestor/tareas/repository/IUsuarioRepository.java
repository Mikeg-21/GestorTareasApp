package com.gestor.tareas.com.gestor.tareas.repository;

import com.gestor.tareas.com.gestor.tareas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
     Usuario findByEmail(String email);

}
