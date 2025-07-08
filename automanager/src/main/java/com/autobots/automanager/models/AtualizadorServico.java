package com.autobots.automanager.models;

import com.autobots.automanager.entidades.Servico;
import org.springframework.stereotype.Component;

@Component
public class AtualizadorServico {
    public Servico atualizarServico(Servico servico, Servico setServico){
        servico.setNome(setServico.getNome());
        servico.setDescricao(setServico.getDescricao());
        servico.setValor(setServico.getValor());
        return servico;
    }
}
