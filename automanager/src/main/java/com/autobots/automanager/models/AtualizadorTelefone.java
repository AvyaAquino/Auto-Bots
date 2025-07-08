package com.autobots.automanager.models;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entidades.Telefone;

@Component
public class AtualizadorTelefone {
    public Telefone atualizarTelefone(Telefone telefone, Telefone setTelefone){
        telefone.setDdd(setTelefone.getDdd());
        telefone.setNumero(setTelefone.getNumero());
        return telefone;
    }
}
