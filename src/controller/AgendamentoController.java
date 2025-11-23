package controller;

import model.Agendamento;
import model.Cliente;
import enums.TipoServico;
import service.AgendamentoService;

import java.time.LocalDateTime;
import java.util.Collection;

public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    public Agendamento agendarServico(Cliente cliente, TipoServico tipoServico, LocalDateTime dataHora) {
        return agendamentoService.agendarServico(cliente, tipoServico, dataHora);
    }

    public Collection<Agendamento> listarAgendamentos() {
        return agendamentoService.listarAgendamentos();
    }
}
