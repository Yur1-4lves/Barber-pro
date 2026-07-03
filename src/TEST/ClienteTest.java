package TEST;

import SISTEMA.Agendamento;
import SISTEMA.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente(
                1,
                "João",
                "joao@email.com",
                "88999999999",
                "123456"
        );
    }

    @Test
    void deveInicializarListaDeAgendamentosVazia() {

        assertNotNull(cliente.getListaAgendamentos());
        assertTrue(cliente.getListaAgendamentos().isEmpty());
    }

    @Test
    void construtorPadraoDeveInicializarListaVazia() {

        Cliente novo = new Cliente();

        assertNotNull(novo.getListaAgendamentos());
        assertTrue(novo.getListaAgendamentos().isEmpty());
    }

    @Test
    void deveRealizarAgendamento() {

        Agendamento agendamento = new Agendamento();

        cliente.realizarAgendamento(agendamento);

        assertEquals(1, cliente.getListaAgendamentos().size());
        assertSame(agendamento, cliente.getListaAgendamentos().get(0));
    }

    @Test
    void deveRealizarMultiplosAgendamentos() {

        Agendamento a1 = new Agendamento();
        Agendamento a2 = new Agendamento();

        cliente.realizarAgendamento(a1);
        cliente.realizarAgendamento(a2);

        List<Agendamento> lista = cliente.getListaAgendamentos();

        assertAll(
                () -> assertEquals(2, lista.size()),
                () -> assertSame(a1, lista.get(0)),
                () -> assertSame(a2, lista.get(1))
        );
    }

    @Test
    void deveCancelarAgendamentoExistente() {

        Agendamento agendamento = new Agendamento();

        cliente.realizarAgendamento(agendamento);

        cliente.cancelarAgendamento(agendamento);

        assertTrue(cliente.getListaAgendamentos().isEmpty());
    }

    @Test
    void naoDeveLancarExcecaoAoCancelarAgendamentoInexistente() {

        Agendamento agendamento = new Agendamento();

        assertDoesNotThrow(() ->
                cliente.cancelarAgendamento(agendamento));

        assertTrue(cliente.getListaAgendamentos().isEmpty());
    }

    @Test
    void deveVisualizarAgendamentosSemLancarExcecao() {

        Agendamento agendamento = new Agendamento();

        cliente.realizarAgendamento(agendamento);

        assertDoesNotThrow(cliente::visualizarAgendamentos);
    }

    @Test
    void deveVisualizarListaVaziaSemLancarExcecao() {

        assertDoesNotThrow(cliente::visualizarAgendamentos);
    }

    @Test
    void devePermitirAgendamentoNulo() {

        cliente.realizarAgendamento(null);

        assertEquals(1, cliente.getListaAgendamentos().size());
        assertNull(cliente.getListaAgendamentos().get(0));
    }

    @Test
    void devePermitirCancelarAgendamentoNulo() {

        cliente.realizarAgendamento(null);

        cliente.cancelarAgendamento(null);

        assertTrue(cliente.getListaAgendamentos().isEmpty());
    }

    @Test
    void getListaAgendamentosDeveRetornarReferenciaDaListaInterna() {

        List<Agendamento> lista = cliente.getListaAgendamentos();

        lista.add(new Agendamento());

        assertEquals(1, cliente.getListaAgendamentos().size());
    }
}