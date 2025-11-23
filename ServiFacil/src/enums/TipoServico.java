package enums;

public enum TipoServico {
    ELETRICISTA("Eletricista"),
    ENCANADOR("Encanador"),
    JARDINEIRO("Jardineiro"),
    DIARISTA("Diarista"),
    TECNICO_INFORMATICA("Técnico de Informática");

    private final String descricao;

    TipoServico(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
