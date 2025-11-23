package model;

import enums.StatusAgendamento;
import enums.TipoServico;

import java.time.LocalDateTime;

public class Agendamento {
    private int id;
    private Cliente cliente;
    private Prestador prestador;
    private TipoServico tipoServico;
    private LocalDateTime dataHora;
    private StatusAgendamento status;
    private static int proximoId = 1;

    public Agendamento(Cliente cliente, Prestador prestador, TipoServico tipoServico, LocalDateTime dataHora) {
        this.id = proximoId++;
        this.cliente = cliente;
        this.prestador = prestador;
        this.tipoServico = tipoServico;
        this.dataHora = dataHora;
        this.status = StatusAgendamento.PENDENTE; // Status inicial
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

    @Override
    public String toString() {
        return "Agendamento{" +
                "id=" + id +
                ", cliente=" + cliente.getNome() +
                ", prestador=" + prestador.getNome() +
                ", tipoServico=" + tipoServico.getDescricao() +
                ", dataHora=" + dataHora +
                ", status=" + status.getDescricao() +
                '}';
    }
}
