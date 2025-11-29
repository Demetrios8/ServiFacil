package controller;

import model.Agendamento;
import model.Avaliacao;
import service.AvaliacaoService;

public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    public AvaliacaoController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    public Avaliacao avaliarServico(Agendamento agendamento, int nota, String comentario) {
        return avaliacaoService.avaliarServico(agendamento, nota, comentario);
    }
}
