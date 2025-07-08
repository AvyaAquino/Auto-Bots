package com.autobots.automanager.controllers;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.models.*;
import com.autobots.automanager.repositorys.ClienteRepository;
import com.autobots.automanager.repositorys.EnderecoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
    @Autowired
    private ClienteRepository clienteRepositorio;

    @Autowired
    private ClienteSelecionador selecionador;

    @Autowired
    private AdicionadorLinkEndereco adicionadorLink;

    @Autowired
    private EnderecoAtualizador enderecoAtualizador;

    @Autowired
    private EnderecoSelecionador enderecoSelecionador;

    @Autowired
    private EnderecoRepository enderecoRepositorio;

    @GetMapping("/visualizar/{id}")
    public ResponseEntity<Endereco> visualizarEndereco(@PathVariable long id) {
        List<Endereco> enderecos = enderecoRepositorio.findAll();
        Endereco endereco = enderecoSelecionador.selecionar(enderecos, id);
        if (endereco == null){
            ResponseEntity<Endereco> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return resposta;
        } else {
            adicionadorLink.adicionarLink(endereco);
            ResponseEntity<Endereco> resposta = new ResponseEntity<>(endereco, HttpStatus.OK);
            return resposta;
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Endereco>> listaEnderecos(){
        List<Endereco> enderecos = enderecoRepositorio.findAll();
        if (enderecos.isEmpty()) {
            ResponseEntity<List<Endereco>> resposta = new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return resposta;
        } else {
            adicionadorLink.adicionarLink(enderecos);
            ResponseEntity<List<Endereco>> resposta = new ResponseEntity<>(enderecos, HttpStatus.FOUND);
            return resposta;
        }
    }

    @GetMapping("/cliente/{clienteid}")
    public ResponseEntity<Endereco> visualizarEnderecoCliente(@PathVariable long clienteid) {
        List<Endereco> enderecos = enderecoRepositorio.findAll();
        Endereco endereco = enderecoSelecionador.selecionar(enderecos, clienteid);
        if (endereco == null){
            ResponseEntity<Endereco> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return resposta;
        } else {
            adicionadorLink.adicionarLink(endereco);
            ResponseEntity<Endereco> resposta = new ResponseEntity<>(endereco, HttpStatus.OK);
            return resposta;
        }
    }

    @PutMapping("/atualizar/{clienteid")
    public ResponseEntity<?> atualizarEndereco(@RequestBody Endereco endereco, @PathVariable long clienteid){
        HttpStatus status = HttpStatus.CONFLICT;
        List<Cliente> clientes = clienteRepositorio.findAll();
        Cliente cliente = selecionador.selecionar(clientes, clienteid);
        if (cliente == null){
            status = HttpStatus.BAD_REQUEST;
        } else {
            enderecoAtualizador.atualizar(cliente.getEndereco(), endereco);
            clienteRepositorio.save(cliente);
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(status);
    }
}
