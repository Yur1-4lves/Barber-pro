package controller;

import REPOSITORIES.BarbeiroRepository;
import SISTEMA.Barbeiro;

import java.util.List;

public class BarbeiroController {

    private BarbeiroRepository repository;

    public BarbeiroController(
            BarbeiroRepository repository) {

        this.repository = repository;
    }

    public void cadastrarBarbeiro(
            Barbeiro barbeiro) {

        repository.salvar(barbeiro);
    }

    public List<Barbeiro> listarBarbeiros() {
        return repository.listarTodos();
    }

    public void atualizarDisponibilidade(
            int id,
            boolean disponibilidade) {

        Barbeiro barbeiro =
                repository.buscarPorId(id);

        if (barbeiro != null) {

            barbeiro.atualizarDisponibilidade(
                    disponibilidade);
        }
    }
}
