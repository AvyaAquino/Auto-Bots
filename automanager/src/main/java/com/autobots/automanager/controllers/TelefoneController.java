package com.autobots.automanager.controllers;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.models.ClienteSelecionador;
import com.autobots.automanager.models.TelefoneAtualizador;
import com.autobots.automanager.models.TelefoneCadastrar;
import com.autobots.automanager.models.TelefoneRemovedor;
import com.autobots.automanager.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/telefone")
public class TelefoneController {
    @Autowired
    private ClienteRepository clienteRepositorio;

    @Autowired
    private ClienteSelecionador clienteSelecionador;

    @Autowired
    private TelefoneCadastrar telefoneCadastrar;

    @Autowired
    private TelefoneRemovedor telefoneRemovedor;

    @Autowired
    private TelefoneAtualizador telefoneAtualizador;

    @PostMapping("/cadastro/{id}")
    public void cadastroTelefone(@RequestBody List<Telefone> telefones, @PathVariable long id) {
        List<Cliente> clientes = clienteRepositorio.findAll();
        Cliente cliente = clienteSelecionador.selecionar(clientes, id);
        telefoneCadastrar.cadastro(cliente, telefones);
        clienteRepositorio.save(cliente);
    }

    @PutMapping("/atualizar/{id}")
    public void atualizarTelefone(@RequestBody List<Telefone> telefones, @PathVariable long id){
        List<Cliente> clientes = clienteRepositorio.findAll();
        Cliente cliente = clienteSelecionador.selecionar(clientes, id);
        telefoneAtualizador.atualizar(cliente.getTelefones(), telefones);
        clienteRepositorio.save(cliente);
    }

    @DeleteMapping("/excluir")
    public void excluirTelefone(@RequestBody List<Telefone> telefones, @PathVariable long id){
        List<Cliente> clientes = clienteRepositorio.findAll();
        Cliente cliente = clienteSelecionador.selecionar(clientes, id);
        telefoneRemovedor.remover(cliente, telefones);
        clienteRepositorio.save(cliente);
    }

    @GetMapping("/visualizar/{id}")
    public void visualizarTelefone(@RequestBody List<Telefone> telefones, @PathVariable long id){
        List<Cliente> clientes = clienteRepositorio.findAll();
        Cliente cliente = clienteSelecionador.selecionar(clientes, id);
        telefoneAtualizador.atualizar(cliente.getTelefones(), telefones);
        clienteRepositorio.save(cliente);
    }
}
