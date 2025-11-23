package service;

import model.Cliente;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ClienteService {

    private final Collection<Cliente> clientes = new ArrayList<>();

    public void cadastrarCliente(String nome, String telefone, String email) {
        Cliente novoCliente = new Cliente(nome, telefone, email);
        clientes.add(novoCliente);
        System.out.println("Cliente cadastrado com sucesso: " + novoCliente);
    }

    public Collection<Cliente> listarClientes() {
        return clientes;
    }

    public Cliente buscarClientePorId(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }
}
