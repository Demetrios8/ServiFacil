package service;

import model.Prestador;
import enums.TipoServico;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class PrestadorService {

    private final Collection<Prestador> prestadores = new ArrayList<>();

    public void cadastrarPrestador(String nome, String telefone, List<TipoServico> servicos) {
        Prestador novoPrestador = new Prestador(nome, telefone, servicos);
        prestadores.add(novoPrestador);
        System.out.println("Prestador cadastrado com sucesso: " + novoPrestador);
    }

    public Collection<Prestador> listarPrestadores() {
        return prestadores;
    }

    public Prestador buscarPrestadorPorId(int id) {
        for (Prestador prestador : prestadores) {
            if (prestador.getId() == id) {
                return prestador;
            }
        }
        return null;
    }

    public List<Prestador> buscarPrestadoresPorTipoServico(TipoServico tipoServico) {
        return prestadores.stream()
                .filter(p -> p.getServicos().contains(tipoServico) && p.isDisponivel())
                .collect(Collectors.toList());
    }
}
