package TEST;

import SISTEMA.Agendamento;
import SISTEMA.Barbeiro;
import SISTEMA.Cliente;
import SISTEMA.Servico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgendamentoTest {

    private Cliente cliente;
    private Barbeiro barbeiro;
    private Servico servico;
    private Agendamento agendamento;

    @BeforeEach
    void setUp() {

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

        agendamento = new Agendamento(
                1,
                cliente,
                barbeiro,
                servico,
                "08:00"
        );
    }

    @Test
    void deveCriarAgendamentoComStatusPendente() {

        assertEquals("PENDENTE", agendamento.getStatus());
    }

    @Test
    void construtorPadraoDeveIniciarComStatusPendente() {

        Agendamento novo = new Agendamento();

        assertEquals("PENDENTE", novo.getStatus());
    }

    @Test
    void deveConfirmarAgendamento() {

        agendamento.confirmarAgendamento();

        assertEquals("CONFIRMADO", agendamento.getStatus());
    }

    @Test
    void deveCancelarAgendamento() {

        agendamento.cancelarAgendamento();

        assertEquals("CANCELADO", agendamento.getStatus());
    }

    @Test
    void deveCancelarMesmoDepoisDeConfirmado() {

        agendamento.confirmarAgendamento();

        agendamento.cancelarAgendamento();

        assertEquals("CANCELADO", agendamento.getStatus());
    }

    @Test
    void deveReagendarHorario() {

        agendamento.reagendar("10:30");

        assertEquals("10:30", agendamento.getHorario());
    }

    @Test
    void devePermitirReagendarParaHorarioNulo() {

        agendamento.reagendar(null);

        assertNull(agendamento.getHorario());
    }

    @Test
    void devePermitirReagendarParaHorarioVazio() {

        agendamento.reagendar("");

        assertEquals("", agendamento.getHorario());
    }

    @Test
    void deveRetornarIdCorreto() {

        assertEquals(1, agendamento.getId());
    }

    @Test
    void deveRetornarClienteCorreto() {

        assertSame(cliente, agendamento.getCliente());
    }

    @Test
    void deveRetornarBarbeiroCorreto() {

        assertSame(barbeiro, agendamento.getBarbeiro());
    }

    @Test
    void deveRetornarServicoCorreto() {

        assertSame(servico, agendamento.getServico());
    }

    @Test
    void deveRetornarHorarioCorreto() {

        assertEquals("08:00", agendamento.getHorario());
    }

    @Test
    void toStringDeveConterInformacoesDoAgendamento() {

        String texto = agendamento.toString();

        assertAll(
                () -> assertTrue(texto.contains("Agendamento #1")),
                () -> assertTrue(texto.contains("João")),
                () -> assertTrue(texto.contains("Carlos")),
                () -> assertTrue(texto.contains("Corte")),
                () -> assertTrue(texto.contains("08:00")),
                () -> assertTrue(texto.contains("PENDENTE"))
        );
    }

    @Test
    void exibirDetalhesNaoDeveLancarExcecao() {

        assertDoesNotThrow(() -> agendamento.exibirDetalhes());
    }

    @Test
    void construtorPadraoDeveInicializarCamposComoNulos() {

        Agendamento novo = new Agendamento();

        assertAll(
                () -> assertEquals(0, novo.getId()),
                () -> assertNull(novo.getCliente()),
                () -> assertNull(novo.getBarbeiro()),
                () -> assertNull(novo.getServico()),
                () -> assertNull(novo.getHorario()),
                () -> assertEquals("PENDENTE", novo.getStatus())
        );
    }

    @Test
    void toStringDoConstrutorPadraoDeveLancarNullPointerException() {

        Agendamento novo = new Agendamento();

        assertThrows(
                NullPointerException.class,
                novo::toString
        );
    }
}