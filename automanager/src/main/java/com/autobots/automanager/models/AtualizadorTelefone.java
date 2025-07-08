package com.autobots.automanager.models;

import com.autobots.automanager.entidades.Telefone;
import org.springframework.stereotype.Component;

@Component
public class AtualizadorTelefone {
    public Telefone atualizarTelefone(Telefone telefone, Telefone setTelefone){
        telefone.setDdd(setTelefone.getDdd());
        telefone.setNumero(setTelefone.getNumero());
        return telefone;
    }
}
