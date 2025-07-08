package com.autobots.automanager.models;

import com.autobots.automanager.entidades.Email;
import org.springframework.stereotype.Component;

@Component
public class AtualizadorEmail {
    public Email atualizarEmail(Email email,Email setEmail){
        email.setEndereco(setEmail.getEndereco());
        return email;
    }
}
