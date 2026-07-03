package TEST;

import SISTEMA.Agenda;
import SISTEMA.Barbeiro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BarbeiroTest {

    private Barbeiro barbeiro;

    @BeforeEach
    void setUp() {
        barbeiro = new Barbeiro(
                1,
                "Carlos",
                "carlos@email.com",
                "88999999999",
                "123456",
                "Degradê"
        );
    }

    @Test
    void construtorPadraoDeveInicializarAgendaEDisponibilidade() {

        Barbeiro novo = new Barbeiro();

        assertAll(
                () -> assertNotNull(novo.getAgenda()),
                () -> assertTrue(novo.isDisponibilidade()),
                () -> assertEquals(0.0, novo.getNotaMedia())
        );
    }

    @Test
    void construtorCompletoDeveInicializarAgendaEDisponibilidade() {

        assertAll(
                () -> assertNotNull(barbeiro.getAgenda()),
                () -> assertTrue(barbeiro.isDisponibilidade()),
                () -> assertEquals("Degradê", barbeiro.getEspecialidade()),
                () -> assertEquals(0.0, barbeiro.getNotaMedia())
        );
    }

    @Test
    void deveAtualizarDisponibilidadeParaFalse() {

        barbeiro.atualizarDisponibilidade(false);

        assertFalse(barbeiro.isDisponibilidade());
    }

    @Test
    void deveAtualizarDisponibilidadeParaTrue() {

        barbeiro.atualizarDisponibilidade(false);

        barbeiro.atualizarDisponibilidade(true);

        assertTrue(barbeiro.isDisponibilidade());
    }

    @Test
    void deveAlternarDisponibilidadeVariasVezes() {

        barbeiro.atualizarDisponibilidade(false);
        assertFalse(barbeiro.isDisponibilidade());

        barbeiro.atualizarDisponibilidade(true);
        assertTrue(barbeiro.isDisponibilidade());

        barbeiro.atualizarDisponibilidade(false);
        assertFalse(barbeiro.isDisponibilidade());
    }

    @Test
    void visualizarAgendaNaoDeveLancarExcecao() {

        assertDoesNotThrow(() ->
                barbeiro.visualizarAgenda());
    }

    @Test
    void visualizarAgendaComHorariosNaoDeveLancarExcecao() {

        barbeiro.getAgenda().adicionarHorario("08:00");
        barbeiro.getAgenda().adicionarHorario("09:00");

        assertDoesNotThrow(() ->
                barbeiro.visualizarAgenda());
    }

    @Test
    void confirmarAgendamentoNaoDeveLancarExcecao() {

        assertDoesNotThrow(() ->
                barbeiro.confirmarAgendamento());
    }

    @Test
    void agendaDeveSerSempreAMesmaInstancia() {

        Agenda agenda1 = barbeiro.getAgenda();
        Agenda agenda2 = barbeiro.getAgenda();

        assertSame(agenda1, agenda2);
    }

    @Test
    void agendaDevePermitirAdicionarHorarios() {

        Agenda agenda = barbeiro.getAgenda();

        agenda.adicionarHorario("08:00");

        assertTrue(
                agenda.verificarDisponibilidade("08:00")
        );
    }

    @Test
    void toStringDeveConterEspecialidade() {

        String texto = barbeiro.toString();

        assertTrue(texto.contains("Especialidade"));
        assertTrue(texto.contains("Degradê"));
    }

    @Test
    void toStringNaoDeveSerNulo() {

        assertNotNull(barbeiro.toString());
    }

    @Test
    void agendaInicialDeveEstarVazia() {

        assertTrue(
                barbeiro.getAgenda()
                        .getAgendamentos()
                        .isEmpty()
        );
    }

    @Test
    void disponibilidadeInicialDeveSerTrue() {

        assertTrue(barbeiro.isDisponibilidade());
    }

    @Test
    void notaMediaInicialDeveSerZero() {

        assertEquals(
                0.0,
                barbeiro.getNotaMedia()
        );
    }
}