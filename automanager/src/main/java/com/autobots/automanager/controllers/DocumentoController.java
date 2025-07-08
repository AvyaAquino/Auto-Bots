package com.autobots.automanager.controllers;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.models.ClienteSelecionador;
import com.autobots.automanager.models.DocumentoAtualizador;
import com.autobots.automanager.models.DocumentoCadastrador;
import com.autobots.automanager.models.DocumentoRemovedor;
import com.autobots.automanager.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documento")
public class DocumentoController {
    @Autowired
    private ClienteRepository repositorio;

    @Autowired
    private ClienteSelecionador selecionador;

    @Autowired
    private DocumentoCadastrador cadastrador;

    @Autowired
    private DocumentoRemovedor removedor;

    @Autowired
    private DocumentoAtualizador atualizador;

    @PostMapping("/cadastro{id}")
        public void cadastrarDocumento(@RequestBody List<Documento> documento, @PathVariable long id) {
            List<Cliente> clientes = repositorio.findAll();
            Cliente cliente = selecionador.selecionar(clientes, id);
            cadastrador.cadastro(cliente, documento);
            repositorio.save(cliente);
        }

    @GetMapping("/vizualizar/{id}")
        public List<Documento> vizualizarDocumento(@PathVariable long id){
            List<Cliente> clientes = repositorio.findAll();
            Cliente cliente = selecionador.selecionar(clientes, id);
            return cliente.getDocumentos();
        }

    @PutMapping("/atualizar/{id}")
        public void atualizarDocumento(@RequestBody List<Documento> documento, @PathVariable long id){
            List<Cliente> clientes = repositorio.findAll();
            Cliente cliente = selecionador.selecionar(clientes, id);
            atualizador.atualizar(cliente.getDocumentos(), documento);
            repositorio.save(cliente);
        }

    @DeleteMapping("/excluir/{id}")
    public void excluirDocumento(@RequestBody List<Documento> documento, @PathVariable long id){
        List<Cliente> clientes = repositorio.findAll();
        Cliente cliente = selecionador.selecionar(clientes, id);
        removedor.remover(cliente, documento);
        repositorio.save(cliente);
    }
}
