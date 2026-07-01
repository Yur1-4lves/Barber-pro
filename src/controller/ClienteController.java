package controller;

import REPOSITORIES.ClienteRepository;
import SISTEMA.Cliente;

public class ClienteController {

    private ClienteRepository repository;

    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    public void cadastrarCliente(Cliente cliente) {
        repository.salvar(cliente);
    }

    public boolean loginCliente(
            String email,
            String senha) {

        Cliente cliente =
                repository.buscarPorEmail(email);

        if (cliente == null) {
            return false;
        }

        return cliente.login(email, senha);
    }

    public void editarPerfil(
            int id,
            String nome,
            String email,
            String telefone) {

        Cliente cliente =
                repository.buscarPorId(id);

        if (cliente != null) {

            cliente.atualizarDados(
                    nome,
                    email,
                    telefone);
        }
    }
}
