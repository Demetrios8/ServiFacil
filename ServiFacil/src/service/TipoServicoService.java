package service;

import model.TipoServico;
import java.util.ArrayList;
import java.util.List;

public class TipoServicoService {

    private final List<TipoServico> tiposDeServico = new ArrayList<>();

    public TipoServicoService() {
        // Carrega os serviços padrão na inicialização
        tiposDeServico.add(new TipoServico(1, "Eletricista", 75.00));
        tiposDeServico.add(new TipoServico(2, "Encanador", 65.00));
        tiposDeServico.add(new TipoServico(3, "Jardineiro", 50.00));
        tiposDeServico.add(new TipoServico(4, "Diarista", 80.00));
        tiposDeServico.add(new TipoServico(5, "Técnico de Informática", 90.00));
    }

    public List<TipoServico> listarTiposDeServico() {
        return new ArrayList<>(tiposDeServico); // Retorna uma cópia para proteger a lista original
    }

    public TipoServico criarTipoServico(String descricao, double valorPorHora) {
        TipoServico novoServico = new TipoServico(descricao, valorPorHora);
        tiposDeServico.add(novoServico);
        System.out.println("Novo tipo de serviço criado: " + novoServico.getDescricao());
        return novoServico;
    }

    public void imprimirTiposDeServico() {
        System.out.println("Tipos de Serviço Disponíveis:");
        for (TipoServico tipo : tiposDeServico) {
            System.out.printf("- %s (R$%.2f/hora)\n", tipo.getDescricao(), tipo.getValorPorHora());
        }
    }
}
