package CONTROLLERS;

import REPOSITORIES.AgendamentoRepository;
import SERVICES.AgendamentoService;
import SISTEMA.Agendamento;

import java.util.List;

public class AgendamentoController {

    private AgendamentoService service;
    private AgendamentoRepository repository;

    public AgendamentoController(AgendamentoService service, AgendamentoRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    public void criarAgendamento(Agendamento agendamento) {
        service.criarAgendamento(agendamento);
    }

    public void cancelarAgendamento(int id) {
        service.cancelarAgendamento(id);
    }

    public List<Agendamento> listarAgendamentos() {
        return repository.listarAgendamentos();
    }
}
