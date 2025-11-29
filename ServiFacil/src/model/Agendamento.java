package model;

import enums.StatusAgendamento;

import java.time.LocalDateTime;

public class Agendamento {
    private int id;
    private Cliente cliente;
    private Prestador prestador;
    private TipoServico tipoServico;
    private LocalDateTime dataHora;
    private StatusAgendamento status;
    private int duracaoEstimadaHoras;
    private double orcamento;
    private Avaliacao avaliacao; // Novo campo
    private static int proximoId = 1;

    public Agendamento(Cliente cliente, Prestador prestador, TipoServico tipoServico, LocalDateTime dataHora, int duracaoEstimadaHoras, double orcamento) {
        this.id = proximoId++;
        this.cliente = cliente;
        this.prestador = prestador;
        this.tipoServico = tipoServico;
        this.dataHora = dataHora;
        this.duracaoEstimadaHoras = duracaoEstimadaHoras;
        this.orcamento = orcamento;
        this.status = StatusAgendamento.PENDENTE;
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Prestador getPrestador() {
        return prestador;
    }

    public TipoServico getTipoServico() {
        return tipoServico;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public StatusAgendamento getStatus() {
        return status;
    }

    public void setStatus(StatusAgendamento status) {
        this.status = status;
    }

    public int getDuracaoEstimadaHoras() {
        return duracaoEstimadaHoras;
    }

    public double getOrcamento() {
        return orcamento;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    @Override
    public String toString() {
        String avaliacaoStr = (avaliacao != null) ? ", avaliacao=" + avaliacao.getNota() + " estrelas" : "";
        return "Agendamento{" +
                "id=" + id +
                ", cliente=" + cliente.getNome() +
                ", prestador=" + prestador.getNome() +
                ", tipoServico=" + tipoServico.getDescricao() +
                ", dataHora=" + dataHora +
                ", duracaoEstimada=" + duracaoEstimadaHoras + "h" +
                ", orcamento=R$" + String.format("%.2f", orcamento) +
                ", status=" + status.getDescricao() +
                avaliacaoStr +
                '}';
    }
}
