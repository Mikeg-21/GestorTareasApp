package com.gestor.tareas.com.gestor.tareas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncriptacionContrasenaService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public EncriptacionContrasenaService(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public String encryptPassword(String plainPassword){
        return bCryptPasswordEncoder.encode(plainPassword);

    }

  /*  public boolean verifyPassword(String plainPassword, String encodePassword){
        return bCryptPasswordEncoder.matches(plainPassword, encodePassword);
    }
*/
}
