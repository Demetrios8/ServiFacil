package model;

public class TipoServico {
    private int id;
    private String descricao;
    private double valorPorHora;
    private static int proximoId = 1;

    public TipoServico(String descricao, double valorPorHora) {
        this.id = proximoId++;
        this.descricao = descricao;
        this.valorPorHora = valorPorHora;
    }

    // Construtor usado para carregar serviços padrão com IDs fixos
    public TipoServico(int id, String descricao, double valorPorHora) {
        this.id = id;
        this.descricao = descricao;
        this.valorPorHora = valorPorHora;
        if (id >= proximoId) {
            proximoId = id + 1;
        }
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValorPorHora() {
        return valorPorHora;
    }

    @Override
    public String toString() {
        return "TipoServico{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", valorPorHora=" + valorPorHora +
                '}';
    }
}
