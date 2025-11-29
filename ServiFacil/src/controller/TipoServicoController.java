package controller;

import model.TipoServico;
import service.TipoServicoService;

import java.util.List;

public class TipoServicoController {

    private final TipoServicoService tipoServicoService;

    public TipoServicoController(TipoServicoService tipoServicoService) {
        this.tipoServicoService = tipoServicoService;
    }

    public List<TipoServico> listarTiposDeServico() {
        return tipoServicoService.listarTiposDeServico();
    }

    public TipoServico criarTipoServico(String descricao, double valorPorHora) {
        return tipoServicoService.criarTipoServico(descricao, valorPorHora);
    }

    public void imprimirTiposDeServico() {
        tipoServicoService.imprimirTiposDeServico();
    }
}
