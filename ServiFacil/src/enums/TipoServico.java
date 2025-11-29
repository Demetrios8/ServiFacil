package enums;

public enum TipoServico {
    ELETRICISTA("Eletricista", 75.00),
    ENCANADOR("Encanador", 65.00),
    JARDINEIRO("Jardineiro", 50.00),
    DIARISTA("Diarista", 80.00),
    TECNICO_INFORMATICA("Técnico de Informática", 90.00);

    private final String descricao;
    private final double valorPorHora;

    TipoServico(String descricao, double valorPorHora) {
        this.descricao = descricao;
        this.valorPorHora = valorPorHora;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValorPorHora() {
        return valorPorHora;
    }
}
