package controller;

import model.Prestador;
import enums.TipoServico;
import service.PrestadorService;

import java.util.Collection;
import java.util.List;

public class PrestadorController {

    private final PrestadorService prestadorService;

    public PrestadorController(PrestadorService prestadorService) {
        this.prestadorService = prestadorService;
    }

    public void cadastrarPrestador(String nome, String telefone, List<TipoServico> servicos) {
        prestadorService.cadastrarPrestador(nome, telefone, servicos);
    }

    public Collection<Prestador> listarPrestadores() {
        return prestadorService.listarPrestadores();
    }

    public Prestador buscarPrestadorPorId(int id) {
        return prestadorService.buscarPrestadorPorId(id);
    }

    public List<Prestador> buscarPrestadoresPorTipoServico(TipoServico tipoServico) {
        return prestadorService.buscarPrestadoresPorTipoServico(tipoServico);
    }
}
