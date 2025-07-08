package com.autobots.automanager.models;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entidades.Email;

@Component
public class AtualizadorEmail {
    public Email atualizarEmail(Email email,Email setEmail){
        email.setEndereco(setEmail.getEndereco());
        return email;
    }
}
