package service;

import model.Agendamento;
import model.Avaliacao;
import enums.StatusAgendamento;

public class AvaliacaoService {

    public Avaliacao avaliarServico(Agendamento agendamento, int nota, String comentario) {
        if (agendamento == null) {
            System.out.println("Erro: Agendamento não pode ser nulo.");
            return null;
        }
        if (agendamento.getStatus() != StatusAgendamento.CONCLUIDO) {
            System.out.println("Erro: Só é possível avaliar serviços com status 'Concluído'.");
            return null;
        }
        if (agendamento.getAvaliacao() != null) {
            System.out.println("Erro: Este serviço já foi avaliado.");
            return null;
        }

        Avaliacao novaAvaliacao = new Avaliacao(nota, comentario);
        agendamento.setAvaliacao(novaAvaliacao);
        System.out.println("Avaliação registrada com sucesso!");
        return novaAvaliacao;
    }
}
