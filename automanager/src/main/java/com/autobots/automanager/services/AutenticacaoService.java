package com.autobots.automanager.services;

import com.autobots.automanager.entidades.Credencial;
import com.autobots.automanager.entidades.Usuario;
import com.autobots.automanager.repositorys.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService {

    @Autowired
    private UsuarioRepository repositorioUsuario;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Usuario registrarUsuario(Credencial credencial) {
        Usuario usuario = new Usuario();
        usuario.setNome(credencial.getNomeUsuario());
        credencial.setSenha(passwordEncoder.encode(credencial.getSenha()));
        usuario.setCredencial(credencial);
        return repositorioUsuario.save(usuario);
    }
}
