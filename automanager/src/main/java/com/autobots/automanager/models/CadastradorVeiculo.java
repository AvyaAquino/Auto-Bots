package com.autobots.automanager.models;

import com.autobots.automanager.entidades.Usuario;
import com.autobots.automanager.entidades.Veiculo;
import com.autobots.automanager.entidades.Venda;
import com.autobots.automanager.repositorys.UsuarioRepository;
import com.autobots.automanager.repositorys.VeiculoRepository;
import com.autobots.automanager.repositorys.VendaRespository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CadastradorVeiculo {

    @Autowired
    private VeiculoRepository repositorioVeiculo;

    @Autowired
    private UsuarioRepository repositorioUsuario;

    @Autowired
    private VendaRespository repositorioVenda;

    @Autowired
    private CadastradorVenda cadastradorVenda;

    public Veiculo cadastrarVeiculo(Veiculo veiculo){
        Veiculo setVeiculo = new Veiculo();
        setVeiculo.setModelo(veiculo.getModelo());
        setVeiculo.setPlaca(veiculo.getPlaca());
        setVeiculo.setTipo(veiculo.getTipo());

        repositorioVeiculo.save(setVeiculo);

        if (veiculo.getProprietario() != null) {
            Usuario proprietario = new Usuario();
            proprietario.setNome(veiculo.getProprietario().getNome());
            proprietario.getVeiculos().add(setVeiculo);
            setVeiculo.setProprietario(proprietario);
        }
        if (veiculo.getVendas() != null) {
            for (Venda venda : veiculo.getVendas()) {
                Venda vendaAtual = cadastradorVenda.cadastrarVenda(venda);
                vendaAtual.setVeiculo(setVeiculo);
                setVeiculo.getVendas().add(vendaAtual);
            }
        }

        repositorioVeiculo.save(setVeiculo);

        return setVeiculo;
    }
}
