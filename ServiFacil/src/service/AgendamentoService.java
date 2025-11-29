package service;

import model.Agendamento;
import model.Cliente;
import model.Prestador;
import enums.TipoServico;
import enums.StatusAgendamento;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class AgendamentoService {

    private final Collection<Agendamento> agendamentos = new ArrayList<>();
    private final PrestadorService prestadorService;
    private final OrcamentoService orcamentoService;

    public AgendamentoService(PrestadorService prestadorService, OrcamentoService orcamentoService) {
        this.prestadorService = prestadorService;
        this.orcamentoService = orcamentoService;
    }

    public Agendamento agendarServico(Cliente cliente, TipoServico tipoServico, LocalDateTime dataHora, int duracaoEstimadaHoras) {
        List<Prestador> prestadoresDisponiveis = prestadorService.buscarPrestadoresPorTipoServico(tipoServico);

        Prestador prestadorAlocado = null;
        for (Prestador p : prestadoresDisponiveis) {
            if (isPrestadorLivre(p, dataHora)) {
                prestadorAlocado = p;
                break;
            }
        }

        if (prestadorAlocado != null) {
            double orcamento = orcamentoService.calcularOrcamento(tipoServico, duracaoEstimadaHoras);
            Agendamento novoAgendamento = new Agendamento(cliente, prestadorAlocado, tipoServico, dataHora, duracaoEstimadaHoras, orcamento);
            agendamentos.add(novoAgendamento);
            System.out.println("Agendamento realizado com sucesso: " + novoAgendamento);
            return novoAgendamento;
        } else {
            System.out.println("Nenhum prestador disponível para este serviço no horário solicitado.");
            return null;
        }
    }

    private boolean isPrestadorLivre(Prestador prestador, LocalDateTime dataHora) {
        return agendamentos.stream()
                .noneMatch(a -> a.getPrestador().equals(prestador) &&
                                a.getDataHora().isEqual(dataHora) &&
                                a.getStatus() != StatusAgendamento.CANCELADO &&
                                a.getStatus() != StatusAgendamento.CONCLUIDO);
    }

    public Agendamento buscarAgendamentoPorId(int id) {
        for (Agendamento agendamento : agendamentos) {
            if (agendamento.getId() == id) {
                return agendamento;
            }
        }
        return null;
    }

    public boolean atualizarStatusAgendamento(int id, StatusAgendamento novoStatus) {
        Agendamento agendamento = buscarAgendamentoPorId(id);
        if (agendamento != null) {
            agendamento.setStatus(novoStatus);
            System.out.println("Status do agendamento " + id + " atualizado para: " + novoStatus.getDescricao());
            return true;
        }
        return false;
    }

    public Collection<Agendamento> listarAgendamentos() {
        return agendamentos;
    }
}
