package service;

import EXCEPTIONS.HorarioIndisponivelException;
import REPOSITORIES.AgendamentoRepository;
import SISTEMA.Agenda;
import SISTEMA.Agendamento;
import SISTEMA.Barbeiro;

public class AgendamentoService {

    private AgendamentoRepository repository;

    public AgendamentoService(AgendamentoRepository repository) {
        this.repository = repository;
    }

    public void criarAgendamento(Agendamento agendamento) {
        if (!oBarbeiroTrabalhaNesseHorario(agendamento)) {
            throw new HorarioIndisponivelException("Horário indisponível.");
        }

        Barbeiro barbeiro = agendamento.getBarbeiro();

        Agenda agenda = barbeiro.getAgenda();

        for (Agendamento a : agenda.getAgendamentos()) {

            boolean mesmaData = a.getHorario().equals(agendamento.getHorario());

            if (mesmaData) {
                throw new HorarioIndisponivelException("Já existe um agendamento neste horário.");
            }
        }

        repository.salvar(agendamento);
    }

    public void cancelarAgendamento(int id) {

        Agendamento agendamento = repository.buscarPorId(id);

        if (agendamento != null) {

            agendamento.cancelarAgendamento();

            agendamento.getBarbeiro()
                    .getAgenda()
                    .adicionarHorario(
                            agendamento.getHorario());

        }

    }

    public boolean oBarbeiroTrabalhaNesseHorario(Agendamento agendamento) {
        return oBarbeiroTrabalhaNesseHorario(
                agendamento.getBarbeiro(),
                agendamento.getHorario()
        );
    }

    public boolean oBarbeiroTrabalhaNesseHorario(Barbeiro barbeiro, String horario) {
        Agenda agenda = barbeiro.getAgenda();
        return agenda.verificarDisponibilidade(horario);
    }
}
