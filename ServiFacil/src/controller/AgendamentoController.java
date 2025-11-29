package controller;

import model.Agendamento;
import model.Cliente;
import enums.StatusAgendamento;
import enums.TipoServico;
import service.AgendamentoService;

import java.time.LocalDateTime;
import java.util.Collection;

public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    public Agendamento agendarServico(Cliente cliente, TipoServico tipoServico, LocalDateTime dataHora, int duracaoEstimadaHoras) {
        return agendamentoService.agendarServico(cliente, tipoServico, dataHora, duracaoEstimadaHoras);
    }

    public Agendamento buscarAgendamentoPorId(int id) {
        return agendamentoService.buscarAgendamentoPorId(id);
    }

    public boolean atualizarStatusAgendamento(int id, StatusAgendamento novoStatus) {
        return agendamentoService.atualizarStatusAgendamento(id, novoStatus);
    }

    public Collection<Agendamento> listarAgendamentos() {
        return agendamentoService.listarAgendamentos();
    }
}
