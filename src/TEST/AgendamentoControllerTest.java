package TEST;

import CONTROLLERS.AgendamentoController;
import REPOSITORIES.AgendamentoRepository;
import SERVICES.AgendamentoService;
import SISTEMA.Agenda;
import SISTEMA.Agendamento;
import SISTEMA.Barbeiro;
import SISTEMA.Cliente;
import SISTEMA.Servico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AgendamentoControllerTest {

    private AgendamentoRepository repository;
    private AgendamentoService service;
    private AgendamentoController controller;

    private Cliente cliente;
    private Barbeiro barbeiro;
    private Servico servico;

    @BeforeEach
    void setUp() {

        repository = new AgendamentoRepository();
        service = new AgendamentoService(repository);
        controller = new AgendamentoController(service, repository);

        cliente = new Cliente(
                1,
                "João",
                "joao@email.com",
                "88999999999",
                "123456"
        );

        barbeiro = new Barbeiro(
                1,
                "Carlos",
                "carlos@email.com",
                "88988888888",
                "123456",
                "Degradê"
        );

        servico = new Servico(
                1,
                "Corte",
                "Corte tradicional",
                35.0,
                40
        );
    }

    @Test
    void deveCriarAgendamentoPeloController() {

        Agenda agenda = barbeiro.getAgenda();
        agenda.adicionarHorario("08:00");

        Agendamento agendamento = new Agendamento(
                1,
                cliente,
                barbeiro,
                servico,
                "08:00"
        );

        controller.criarAgendamento(agendamento);

        List<Agendamento> lista = controller.listarAgendamentos();

        assertEquals(1, lista.size());
        assertSame(agendamento, lista.get(0));
    }

    @Test
    void deveListarAgendamentos() {

        Agenda agenda = barbeiro.getAgenda();
        agenda.adicionarHorario("09:00");

        Agendamento agendamento = new Agendamento(
                2,
                cliente,
                barbeiro,
                servico,
                "09:00"
        );

        controller.criarAgendamento(agendamento);

        List<Agendamento> lista = controller.listarAgendamentos();

        assertFalse(lista.isEmpty());
        assertEquals(1, lista.size());
    }

    @Test
    void deveCancelarAgendamentoPeloController() {

        Agenda agenda = barbeiro.getAgenda();
        agenda.adicionarHorario("10:00");

        Agendamento agendamento = new Agendamento(
                3,
                cliente,
                barbeiro,
                servico,
                "10:00"
        );

        controller.criarAgendamento(agendamento);

        controller.cancelarAgendamento(3);

        assertEquals(
                "CANCELADO",
                repository.buscarPorId(3).getStatus()
        );
    }

    @Test
    void listarAgendamentosDeveRetornarListaVaziaInicialmente() {

        List<Agendamento> lista = controller.listarAgendamentos();

        assertNotNull(lista);
        assertTrue(lista.isEmpty());
    }

    @Test
    void naoDeveLancarExcecaoAoCancelarAgendamentoInexistente() {

        assertDoesNotThrow(() ->
                controller.cancelarAgendamento(999)
        );
    }
}