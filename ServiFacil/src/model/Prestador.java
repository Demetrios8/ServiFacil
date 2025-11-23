package model;

import enums.TipoServico;
import java.util.List;

public class Prestador {
    private int id;
    private String nome;
    private String telefone;
    private List<TipoServico> servicos;
    private boolean disponivel = true;
    private static int proximoId = 1;

    public Prestador(String nome, String telefone, List<TipoServico> servicos) {
        this.id = proximoId++;
        this.nome = nome;
        this.telefone = telefone;
        this.servicos = servicos;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public List<TipoServico> getServicos() {
        return servicos;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "Prestador{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", servicos=" + servicos +
                ", disponivel=" + disponivel +
                '}';
    }
}
