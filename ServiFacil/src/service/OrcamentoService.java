package service;

import enums.TipoServico;

public class OrcamentoService {

    public double calcularOrcamento(TipoServico tipoServico, int duracaoEstimadaHoras) {
        if (tipoServico == null || duracaoEstimadaHoras <= 0) {
            return 0.0;
        }
        return tipoServico.getValorPorHora() * duracaoEstimadaHoras;
    }
}
