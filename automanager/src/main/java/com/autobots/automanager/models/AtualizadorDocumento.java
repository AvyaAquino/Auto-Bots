package com.autobots.automanager.models;

import com.autobots.automanager.entidades.Documento;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AtualizadorDocumento {
    public Documento atualizarDocumento(Documento documento,Documento setDocumento) {
        documento.setTipo(setDocumento.getTipo());
        documento.setNumero(setDocumento.getNumero());
        documento.setDataEmissao(new Date());
        return documento;
    }
}
