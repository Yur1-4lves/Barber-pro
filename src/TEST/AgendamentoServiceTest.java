package TEST;

import EXCEPTIONS.HorarioIndisponivelException;
import REPOSITORIES.AgendamentoRepository;
import SERVICES.AgendamentoService;
import SISTEMA.Agenda;
import SISTEMA.Agendamento;
import SISTEMA.Barbeiro;
import SISTEMA.Cliente;
import SISTEMA.Servico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgendamentoServiceTest {

    private AgendamentoRepository repository;
    private AgendamentoService service;

    private Cliente cliente;
    private Barbeiro barbeiro;
    private Servico servico;

    @BeforeEach
    void setUp() {

        repository = new AgendamentoRepository();
        service = new AgendamentoService(repository);

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
    void deveCriarAgendamentoQuandoHorarioEstiverDisponivel() {

        Agenda agenda = barbeiro.getAgenda();
        agenda.adicionarHorario("08:00");

        Agendamento agendamento = new Agendamento(
                1,
                cliente,
                barbeiro,
                servico,
                "08:00"
        );

        service.criarAgendamento(agendamento);

        assertEquals(
                1,
                repository.listarAgendamentos().size()
        );

        assertSame(
                agendamento,
                repository.buscarPorId(1)
        );
    }

    @Test
    void deveLancarExcecaoQuandoHorarioNaoFizerParteDaAgenda() {

        Agendamento agendamento = new Agendamento(
                1,
                cliente,
                barbeiro,
                servico,
                "20:00"
        );

        HorarioIndisponivelException exception =
                assertThrows(
                        HorarioIndisponivelException.class,
                        () -> service.criarAgendamento(agendamento)
                );

        assertEquals(
                "Horário indisponível.",
                exception.getMessage()
        );

        assertTrue(repository.listarAgendamentos().isEmpty());
    }

    @Test
    void deveLancarExcecaoQuandoHorarioJaEstiverOcupado() {

        Agenda agenda = barbeiro.getAgenda();

        agenda.adicionarHorario("08:00");

        Agendamento primeiro = new Agendamento(
                1,
                cliente,
                barbeiro,
                servico,
                "08:00"
        );

        agenda.adicionarAgendamento(primeiro);

        Agendamento segundo = new Agendamento(
                2,
                cliente,
                barbeiro,
                servico,
                "08:00"
        );

        HorarioIndisponivelException exception =
                assertThrows(
                        HorarioIndisponivelException.class,
                        () -> service.criarAgendamento(segundo)
                );

        assertEquals(
                "Já existe um agendamento neste horário.",
                exception.getMessage()
        );

        assertTrue(repository.listarAgendamentos().isEmpty());
    }

    @Test
    void deveCancelarAgendamentoExistente() {

        Agenda agenda = barbeiro.getAgenda();
        agenda.adicionarHorario("08:00");

        Agendamento agendamento = new Agendamento(
                1,
                cliente,
                barbeiro,
                servico,
                "08:00"
        );

        repository.salvar(agendamento);

        service.cancelarAgendamento(1);

        assertEquals(
                "CANCELADO",
                repository.buscarPorId(1).getStatus()
        );

        assertTrue(
                agenda.verificarDisponibilidade("08:00")
        );
    }

    @Test
    void naoDeveLancarExcecaoAoCancelarAgendamentoInexistente() {

        assertDoesNotThrow(
                () -> service.cancelarAgendamento(999)
        );
    }

    @Test
    void deveRetornarTrueQuandoBarbeiroTrabalharNoHorario() {

        barbeiro.getAgenda().adicionarHorario("09:00");

        assertTrue(
                service.oBarbeiroTrabalhaNesseHorario(
                        barbeiro,
                        "09:00"
                )
        );
    }

    @Test
    void deveRetornarFalseQuandoBarbeiroNaoTrabalharNoHorario() {

        assertFalse(
                service.oBarbeiroTrabalhaNesseHorario(
                        barbeiro,
                        "22:00"
                )
        );
    }

    @Test
    void deveVerificarDisponibilidadeRecebendoAgendamento() {

        barbeiro.getAgenda().adicionarHorario("10:00");

        Agendamento agendamento = new Agendamento(
                5,
                cliente,
                barbeiro,
                servico,
                "10:00"
        );

        assertTrue(
                service.oBarbeiroTrabalhaNesseHorario(
                        agendamento
                )
        );
    }
}