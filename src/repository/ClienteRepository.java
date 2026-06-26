package REPOSITORIES;

import SISTEMA.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteRepository {

    private List<Cliente> clientes;

    public ClienteRepository() {
        clientes = new ArrayList<>();
    }

    public void salvar(Cliente cliente) {
        for (Cliente c : clientes) {
            boolean mesmoEmail = c.getEmail().equalsIgnoreCase(cliente.getEmail());
            if (mesmoEmail) {
                throw new RuntimeException("E-mail já cadastrado.");
            }
        }

        clientes.add(cliente);
    }

    public Cliente buscarPorId(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }

        return null;
    }

    public List<Cliente> listarTodos() {
        return clientes;
    }

    public void remover(int id) {
        Cliente cliente = buscarPorId(id);

        if (cliente != null) {
            clientes.remove(cliente);
        }
    }

    public Cliente buscarPorEmail(String email) {
        for (Cliente cliente : clientes) {
            boolean mesmoEmail = cliente.getEmail().equalsIgnoreCase(email);
            if (mesmoEmail) {
                return cliente;
            }
        }

        return null;
    }
}
