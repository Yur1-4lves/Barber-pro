package repository;

import SISTEMA.Barbeiro;

import java.util.ArrayList;
import java.util.List;

public class BarbeiroRepository {

    private List<Barbeiro> barbeiros;

    public BarbeiroRepository() {
        barbeiros = new ArrayList<>();
    }

    public void salvar(Barbeiro barbeiro) {

        for (Barbeiro b : barbeiros) {

            if (b.getEmail().equalsIgnoreCase(
                    barbeiro.getEmail())) {

                throw new RuntimeException(
                        "E-mail já cadastrado."
                );
            }
        }

        barbeiros.add(barbeiro);
    }

    public Barbeiro buscarPorId(int id) {

        for (Barbeiro barbeiro : barbeiros) {

            if (barbeiro.getId() == id) {
                return barbeiro;
            }
        }

        return null;
    }

    public List<Barbeiro> listarTodos() {
        return barbeiros;
    }

    public void remover(int id) {

        Barbeiro barbeiro = buscarPorId(id);

        if (barbeiro != null) {
            barbeiros.remove(barbeiro);
        }
    }
}
