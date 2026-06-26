package SISTEMA;

import java.util.ArrayList;
import java.util.List;

public class Agenda {

    private Barbeiro barbeiro;

    private List<Agendamento> listaAgendamentos;
    private List<String> horariosDisponiveis;

    public Agenda() {
        listaAgendamentos = new ArrayList<>();
        horariosDisponiveis = new ArrayList<>();
    }

    public void adicionarHorario(String horario) {
        horariosDisponiveis.add(horario);
    }

    public void removerHorario(String horario) {
        horariosDisponiveis.remove(horario);
    }

    public boolean verificarDisponibilidade(String horario) {
        return horariosDisponiveis.contains(horario);
    }

    public List<String> listarHorarios() {
        return horariosDisponiveis;
    }

    public void adicionarAgendamento(Agendamento agendamento) {
        listaAgendamentos.add(agendamento);
    }

    public List<Agendamento> getListaAgendamentos() {
        return listaAgendamentos;
    }

    @Override
    public String toString() {
        return listaAgendamentos.toString();
    }
}
