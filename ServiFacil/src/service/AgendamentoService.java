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

public class AgendamentoService {

    private final Collection<Agendamento> agendamentos = new ArrayList<>();
    private final PrestadorService prestadorService;
    private final OrcamentoService orcamentoService;

    public AgendamentoService(PrestadorService prestadorService, OrcamentoService orcamentoService) {
        this.prestadorService = prestadorService;
        this.orcamentoService = orcamentoService;
    }

    public Agendamento agendarServico(Cliente cliente, TipoServico tipoServico, LocalDateTime dataHora, int duracaoEstimadaHoras) {
        // 1. Encontrar prestadores disponíveis para o tipo de serviço
        List<Prestador> prestadoresDisponiveis = prestadorService.buscarPrestadoresPorTipoServico(tipoServico);

        // 2. Verificar se algum deles está livre no horário solicitado
        Prestador prestadorAlocado = null;
        for (Prestador p : prestadoresDisponiveis) {
            if (isPrestadorLivre(p, dataHora)) {
                prestadorAlocado = p;
                break;
            }
        }

        if (prestadorAlocado != null) {
            // 3. Calcular o orçamento
            double orcamento = orcamentoService.calcularOrcamento(tipoServico, duracaoEstimadaHoras);

            // 4. Criar e salvar o agendamento
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

    public Collection<Agendamento> listarAgendamentos() {
        return agendamentos;
    }
}
