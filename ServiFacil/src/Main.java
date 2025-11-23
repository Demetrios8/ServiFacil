import controller.ClienteController;
import controller.TipoServicoController;
import model.Cliente;
import service.ClienteService;
import service.TipoServicoService;

import java.util.Collection;
import java.util.Scanner;

 public class Main {
    public static void main(String[] args) {
        // --- Inicialização dos serviços e controladores ---
        ClienteService clienteService = new ClienteService();
        ClienteController clienteController = new ClienteController(clienteService);

        TipoServicoService tipoServicoService = new TipoServicoService();
        TipoServicoController tipoServicoController = new TipoServicoController(tipoServicoService);

        Scanner scanner = new Scanner(System.in);
        int opcao;

        System.out.println("### Bem-vindo ao ServiFácil ###");

        do {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Listar Tipos de Serviço");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha após o nextInt()

            switch (opcao) {
                case 1:
                    System.out.println("\n--- Cadastro de Cliente ---");
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    clienteController.cadastrarCliente(nome, telefone, email);
                    break;
                case 2:
                    System.out.println("\n--- Clientes Cadastrados ---");
                    Collection<Cliente> clientes = clienteController.listarClientes();
                    if (clientes.isEmpty()) {
                        System.out.println("Nenhum cliente cadastrado.");
                    } else {
                        clientes.forEach(System.out::println);
                    }
                    break;
                case 3:
                    System.out.println("\n--- Tipos de Serviço Disponíveis ---");
                    tipoServicoController.imprimirTiposDeServico();
                    break;
                case 0:
                    System.out.println("\nSaindo do sistema... Até logo!");
                    break;
                default:
                    System.out.println("\nOpção inválida! Tente novamente.");
                    break;
            }
        } while (opcao != 0);

        scanner.close();
    }
}
