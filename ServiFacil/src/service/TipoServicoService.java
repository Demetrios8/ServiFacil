package service;

import enums.TipoServico;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TipoServicoService {

    public List<TipoServico> listarTiposDeServico() {
        return Arrays.asList(TipoServico.values());
    }

    public void imprimirTiposDeServico() {
        System.out.println("Tipos de Serviço Disponíveis:");
        for (TipoServico tipo : listarTiposDeServico()) {
            System.out.println("- " + tipo.getDescricao());
        }
    }
}
