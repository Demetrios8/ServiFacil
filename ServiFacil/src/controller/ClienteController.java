package controller;

import model.Cliente;
import service.ClienteService;

import java.util.Collection;

public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public void cadastrarCliente(String nome, String telefone, String email) {
        clienteService.cadastrarCliente(nome, telefone, email);
    }

    public Collection<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    public Cliente buscarClientePorId(int id) {
        return clienteService.buscarClientePorId(id);
    }
}
