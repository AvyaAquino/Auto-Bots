package com.autobots.automanager.controllers;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.models.ClienteSelecionador;
import com.autobots.automanager.models.EnderecoAtualizador;
import com.autobots.automanager.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
    @Autowired
    private ClienteRepository clienteRepositorio;

    @Autowired
    private ClienteSelecionador clienteSelecionador;

    @Autowired
    private EnderecoAtualizador atualizador;

    @PutMapping("/atualizar/{id}")
    public void atualizarEndereco(@RequestBody Endereco endereco,@PathVariable Long id) {
        List<Cliente> clientes = clienteRepositorio.findAll();
        Cliente cliente = clienteSelecionador.selecionar(clientes, id);
        atualizador.atualizar(cliente.getEndereco(), endereco);
        clienteRepositorio.save(cliente);
    }

    @GetMapping("/visualizar/{id}")
    public void atualizarEndereca(@RequestBody Endereco endereco,@PathVariable Long id) {
        List<Cliente> clientes = clienteRepositorio.findAll();
        Cliente cliente = clienteSelecionador.selecionar(clientes, id);
        atualizador.atualizar(cliente.getEndereco(), endereco);
        clienteRepositorio.save(cliente);
    }
}
