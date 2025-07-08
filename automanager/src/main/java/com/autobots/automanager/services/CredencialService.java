package com.autobots.automanager.services;

import com.autobots.automanager.DTO.CredencialDto;
import com.autobots.automanager.entidades.Credencial;
import com.autobots.automanager.entidades.Usuario;
import com.autobots.automanager.models.AdicionadorLinkCredencial;
import com.autobots.automanager.models.SelecionadorUsuario;
import com.autobots.automanager.models.VerificadorPermissao;
import com.autobots.automanager.repositorys.CredencialRepository;
import com.autobots.automanager.repositorys.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CredencialService {

    @Autowired
    private CredencialRepository repositorioCredencial;

    @Autowired
    private UsuarioRepository repositorioUsuario;

    @Autowired
    private AdicionadorLinkCredencial adicionadorLinkCredencial;

    @Autowired
    private SelecionadorUsuario selecionadorUsuario;

    @Autowired
    private VerificadorPermissao verificadorPermissao;

    public List<Credencial> listarCredenciais() {
        List<Credencial> credencials = repositorioCredencial.findActiveCredentials();
        adicionadorLinkCredencial.adicionarLink(credencials);
        return credencials;
    }

    public Credencial visualizarCredencial(Long id) {
        Credencial credencial = repositorioCredencial.findActiveCredentialById(id).orElse(null);
        if (credencial != null) {
            adicionadorLinkCredencial.adicionarLink(credencial);
        }
        return credencial;
    }

    public Credencial listarCredenciaisUsuario(Long idUsuario, String username) {
        List<Usuario> usuarios = repositorioUsuario.findAll();
        Usuario usuario = repositorioUsuario.findById(idUsuario).orElse(null);
        Usuario usuarioSelecionado = selecionadorUsuario.selecionarUsername(usuarios, username);

        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }

        boolean permissao = verificadorPermissao.verificar(usuarioSelecionado.getPerfis(), usuario.getPerfis());

        if (!permissao) {
            throw new IllegalArgumentException("Usuário sem permissão.");
        }

        Credencial credencial = usuario.getCredencial();
        adicionadorLinkCredencial.adicionarLink(credencial);
        return credencial;
    }

    public void cadastrarCredencial (Long idUsuario, CredencialDto credencial, String username) {
        List<Usuario> usuarios = repositorioUsuario.findAll();
        Usuario usuarioSelecionado = selecionadorUsuario.selecionarUsername(usuarios, username);
        Usuario usuario = repositorioUsuario.findById(idUsuario).orElse(null);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }

        boolean permissao = verificadorPermissao.verificar(usuarioSelecionado.getPerfis(), usuario.getPerfis());

        if (!permissao) {
            throw new IllegalArgumentException("Usuário sem permissão.");
        }

        Credencial credencialCadastrar = new Credencial();
        if (credencial.nomeUsuario().isPresent()) {
            credencialCadastrar.setNomeUsuario(credencial.nomeUsuario().get());
        }
        if (credencial.senha().isPresent()) {
            credencialCadastrar.setSenha(credencial.senha().get());
        }
        credencialCadastrar.setInativo(false);
        repositorioCredencial.save(credencialCadastrar);

        usuario.setCredencial(credencialCadastrar);
        repositorioUsuario.save(usuario);
    }

    public void atualizarCredencial(Long id, CredencialDto credencial, String username) {
        Credencial credencialAtualizado = repositorioCredencial.findActiveCredentialById(id).orElse(null);
        List<Usuario> usuarios = repositorioUsuario.findAll();
        Usuario usuarioSelecionado = selecionadorUsuario.selecionarUsername(usuarios, username);
        Usuario usuarioAtualizado = null;
        for (Usuario usuario : usuarios) {
            if (usuario.getCredencial().getId().equals(id)) {
                usuarioAtualizado = usuario;
                break;
            }
        }
        if (usuarioAtualizado == null) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }

        boolean permissao = verificadorPermissao.verificar(usuarioSelecionado.getPerfis(), usuarioAtualizado.getPerfis());

        if (!permissao) {
            throw new IllegalArgumentException("Usuário sem permissão.");
        }

        if (credencialAtualizado != null) {
            if (credencial.nomeUsuario().isPresent()) {
                credencialAtualizado.setNomeUsuario(credencial.nomeUsuario().get());
            }
            if (credencial.senha().isPresent()) {
                credencialAtualizado.setSenha(credencial.senha().get());
            }
            repositorioCredencial.save(credencialAtualizado);
        }

        else {
            throw new IllegalArgumentException("Credencial não encontrada.");
        }
    }

    public void deletarCredencial(Long id) {
        Credencial credencial = repositorioCredencial.findById(id).orElse(null);
        if (credencial != null) {
            credencial.setInativo(true);
            repositorioCredencial.save(credencial);
        }
        else {
            throw new IllegalArgumentException("Credencial não encontrada.");
        }
    }
}