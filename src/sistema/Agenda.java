package SISTEMA;

import java.util.ArrayList;
import java.util.List;

public class Agenda {

    private Barbeiro barbeiro;

    private List<Agendamento> agendamentos;
    private List<String> horariosDeTrabalho;

    public Agenda() {
        agendamentos = new ArrayList<>();
        horariosDeTrabalho = new ArrayList<>();
    }

    public void adicionarHorario(String horario) {
        horariosDeTrabalho.add(horario);
    }

    public void removerHorario(String horario) {
        horariosDeTrabalho.remove(horario);
    }

    public boolean verificarDisponibilidade(String horario) {
        return listarHorariosLivres().contains(horario);
    }

    private List<String> listarHorarios() {
        return horariosDeTrabalho;
    }

    public List<String> listarHorariosLivres() {
        List<String> horariosJaAgendados = new ArrayList<>();
        for (Agendamento a: agendamentos) {
            horariosJaAgendados.add(a.getHorario());
        }

        List<String> horariosLivres = new ArrayList<>();
        for (String h : horariosDeTrabalho) {
            if (!horariosJaAgendados.contains(h)) {
                horariosLivres.add(h);
            }
        }

        return horariosLivres;

    }

    public void adicionarAgendamento(Agendamento agendamento) {
        agendamentos.add(agendamento);
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    @Override
    public String toString() {
        return agendamentos.toString();
    }
}
