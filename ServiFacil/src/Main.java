import controller.AgendamentoController;
import controller.ClienteController;
import controller.PrestadorController;
import controller.TipoServicoController;
import enums.TipoServico;
import model.Agendamento;
import model.Cliente;
import model.Prestador;
import service.AgendamentoService;
import service.ClienteService;
import service.OrcamentoService;
import service.PrestadorService;
import service.TipoServicoService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // --- Inicialização dos serviços e controladores ---
        ClienteService clienteService = new ClienteService();
        PrestadorService prestadorService = new PrestadorService();
        OrcamentoService orcamentoService = new OrcamentoService();
        AgendamentoService agendamentoService = new AgendamentoService(prestadorService, orcamentoService);
        TipoServicoService tipoServicoService = new TipoServicoService();

        ClienteController clienteController = new ClienteController(clienteService);
        PrestadorController prestadorController = new PrestadorController(prestadorService);
        AgendamentoController agendamentoController = new AgendamentoController(agendamentoService);
        TipoServicoController tipoServicoController = new TipoServicoController(tipoServicoService);

        Scanner scanner = new Scanner(System.in);
        int opcao;

        System.out.println("### Bem-vindo ao ServiFácil ###");

        do {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Cadastrar Prestador");
            System.out.println("4. Listar Prestadores");
            System.out.println("5. Agendar Serviço");
            System.out.println("6. Listar Agendamentos");
            System.out.println("7. Listar Tipos de Serviço");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    cadastrarCliente(scanner, clienteController);
                    break;
                case 2:
                    listarClientes(clienteController);
                    break;
                case 3:
                    cadastrarPrestador(scanner, prestadorController, tipoServicoController);
                    break;
                case 4:
                    listarPrestadores(prestadorController);
                    break;
                case 5:
                    agendarServico(scanner, agendamentoController, clienteController, tipoServicoController, orcamentoService);
                    break;
                case 6:
                    listarAgendamentos(agendamentoController);
                    break;
                case 7:
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

    private static void cadastrarCliente(Scanner scanner, ClienteController clienteController) {
        System.out.println("\n--- Cadastro de Cliente ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        clienteController.cadastrarCliente(nome, telefone, email);
    }

    private static void listarClientes(ClienteController clienteController) {
        System.out.println("\n--- Clientes Cadastrados ---");
        Collection<Cliente> clientes = clienteController.listarClientes();
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            clientes.forEach(System.out::println);
        }
    }

    private static void cadastrarPrestador(Scanner scanner, PrestadorController prestadorController, TipoServicoController tipoServicoController) {
        System.out.println("\n--- Cadastro de Prestador ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        List<TipoServico> servicosPrestador = new ArrayList<>();
        List<TipoServico> todosOsServicos = tipoServicoController.listarTiposDeServico();
        int opServico;
        do {
            System.out.println("Selecione os serviços que o prestador oferece (digite 0 para finalizar):");
            for (int i = 0; i < todosOsServicos.size(); i++) {
                System.out.printf("%d. %s\n", (i + 1), todosOsServicos.get(i).getDescricao());
            }
            System.out.print("Opção: ");
            opServico = scanner.nextInt();
            scanner.nextLine();

            if (opServico > 0 && opServico <= todosOsServicos.size()) {
                TipoServico servicoEscolhido = todosOsServicos.get(opServico - 1);
                if (!servicosPrestador.contains(servicoEscolhido)) {
                    servicosPrestador.add(servicoEscolhido);
                    System.out.println(servicoEscolhido.getDescricao() + " adicionado.");
                } else {
                    System.out.println("Serviço já adicionado.");
                }
            }
        } while (opServico != 0);

        if (servicosPrestador.isEmpty()) {
            System.out.println("Cadastro cancelado. É necessário selecionar ao menos um serviço.");
            return;
        }
        prestadorController.cadastrarPrestador(nome, telefone, servicosPrestador);
    }

    private static void listarPrestadores(PrestadorController prestadorController) {
        System.out.println("\n--- Prestadores Cadastrados ---");
        Collection<Prestador> prestadores = prestadorController.listarPrestadores();
        if (prestadores.isEmpty()) {
            System.out.println("Nenhum prestador cadastrado.");
        } else {
            prestadores.forEach(System.out::println);
        }
    }

    private static void agendarServico(Scanner scanner, AgendamentoController agendamentoController, ClienteController clienteController, TipoServicoController tipoServicoController, OrcamentoService orcamentoService) {
        System.out.println("\n--- Agendamento de Serviço ---");
        System.out.print("Digite o ID do Cliente: ");
        int idCliente = scanner.nextInt();
        scanner.nextLine();
        Cliente cliente = clienteController.buscarClientePorId(idCliente);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.println("Selecione o tipo de serviço:");
        List<TipoServico> todosOsServicos = tipoServicoController.listarTiposDeServico();
        for (int i = 0; i < todosOsServicos.size(); i++) {
            System.out.printf("%d. %s (R$%.2f/hora)\n", (i + 1), todosOsServicos.get(i).getDescricao(), todosOsServicos.get(i).getValorPorHora());
        }
        System.out.print("Opção: ");
        int opServico = scanner.nextInt();
        scanner.nextLine();
        if (opServico <= 0 || opServico > todosOsServicos.size()) {
            System.out.println("Opção de serviço inválida.");
            return;
        }
        TipoServico tipoServico = todosOsServicos.get(opServico - 1);

        System.out.print("Digite a duração estimada do serviço (em horas): ");
        int duracao = scanner.nextInt();
        scanner.nextLine();
        if (duracao <= 0) {
            System.out.println("Duração inválida.");
            return;
        }

        double orcamento = orcamentoService.calcularOrcamento(tipoServico, duracao);
        System.out.printf("O orçamento para este serviço é: R$%.2f. Deseja confirmar? (S/N): ", orcamento);
        String confirmacao = scanner.nextLine();

        if (!confirmacao.equalsIgnoreCase("S")) {
            System.out.println("Agendamento cancelado.");
            return;
        }

        System.out.print("Digite a data e hora do agendamento (dd/MM/yyyy HH:mm): ");
        String dataHoraStr = scanner.nextLine();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime dataHora = LocalDateTime.parse(dataHoraStr, formatter);
            agendamentoController.agendarServico(cliente, tipoServico, dataHora, duracao);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data/hora inválido. Use dd/MM/yyyy HH:mm.");
        }
    }

    private static void listarAgendamentos(AgendamentoController agendamentoController) {
        System.out.println("\n--- Agendamentos ---");
        Collection<Agendamento> agendamentos = agendamentoController.listarAgendamentos();
        if (agendamentos.isEmpty()) {
            System.out.println("Nenhum agendamento realizado.");
        } else {
            agendamentos.forEach(System.out::println);
        }
    }
}
