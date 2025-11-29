package service;

import model.Agendamento;
import model.Prestador;
import enums.StatusAgendamento;
import model.TipoServico;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class PrestadorService {

    private final Collection<Prestador> prestadores = new ArrayList<>();
    private AgendamentoService agendamentoService; // Dependência para buscar avaliações

    // Injeção de dependência via setter para evitar referência circular na construção
    public void setAgendamentoService(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

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

    public double calcularNotaMedia(Prestador prestador) {
        if (agendamentoService == null) {
            return 0.0; // Retorna 0 se o serviço não estiver disponível
        }

        List<Agendamento> agendamentosDoPrestador = agendamentoService.listarAgendamentos().stream()
                .filter(a -> a.getPrestador().equals(prestador) &&
                              a.getStatus() == StatusAgendamento.CONCLUIDO &&
                              a.getAvaliacao() != null)
                .collect(Collectors.toList());

        if (agendamentosDoPrestador.isEmpty()) {
            return 0.0; // Nenhuma avaliação ainda
        }

        double somaDasNotas = agendamentosDoPrestador.stream()
                .mapToDouble(a -> a.getAvaliacao().getNota())
                .sum();

        return somaDasNotas / agendamentosDoPrestador.size();
    }
}
