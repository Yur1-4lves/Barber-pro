package INTERFACES;

import SISTEMA.Agendamento;

public interface Gerenciavel {

    void realizarAgendamento(Agendamento agendamento);

    void cancelarAgendamento(Agendamento agendamento);

    void visualizarAgendamentos();

}
