package repository;

import SISTEMA.Agendamento;

import java.util.ArrayList;
import java.util.List;

public class AgendamentoRepository {

    private List<Agendamento> agendamentos;

    public AgendamentoRepository() {
        agendamentos = new ArrayList<>();
    }

    public void salvar(Agendamento agendamento) {
        agendamentos.add(agendamento);
    }

    public void cancelar(int id) {
        Agendamento agendamento = buscarPorId(id);

        if (agendamento != null) {
            agendamento.cancelarAgendamento();
        }
    }

    public List<Agendamento> listarAgendamentos() {
        return agendamentos;
    }

    public Agendamento buscarPorId(int id) {
        for (Agendamento agendamento : agendamentos) {
            if (agendamento.getId() == id) {
                return agendamento;
            }
        }

        return null;
    }
}
