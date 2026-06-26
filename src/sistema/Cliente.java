package SISTEMA;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {

    private List<Agendamento> listaAgendamentos;
    private List<Avaliacao> listaAvaliacoes;

    public Cliente() {
        listaAgendamentos = new ArrayList<>();
        listaAvaliacoes = new ArrayList<>();
    }

    public Cliente(
            int id,
            String nome,
            String email,
            String telefone,
            String senha
    ) {
        super(id, nome, email, telefone, senha);

        listaAgendamentos = new ArrayList<>();
        listaAvaliacoes = new ArrayList<>();
    }

    public void realizarAgendamento(Agendamento agendamento) {
        listaAgendamentos.add(agendamento);
    }

    public void cancelarAgendamento(Agendamento agendamento) {
        listaAgendamentos.remove(agendamento);
    }

    public void visualizarAgendamentos() {
        for (Agendamento a : listaAgendamentos) {
            System.out.println(a);
        }
    }

    public void avaliarBarbeiro(Avaliacao avaliacao) {
        listaAvaliacoes.add(avaliacao);
    }

    public List<Agendamento> getListaAgendamentos() {
        return listaAgendamentos;
    }

    public List<Avaliacao> getListaAvaliacoes() {
        return listaAvaliacoes;
    }
}
