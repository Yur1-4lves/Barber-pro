package TEST;

import SISTEMA.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AgendaTest {

    private Agenda agenda;

    @BeforeEach
    void setUp() {
        agenda = new Agenda();
    }

    @Test
    void deveAdicionarHorarioETornaLoDisponivel() {
        agenda.adicionarHorario("08:00");

        assertTrue(agenda.verificarDisponibilidade("08:00"));
    }

    @Test
    void deveAdicionarMultiplosHorarios() {
        agenda.adicionarHorario("08:00");
        agenda.adicionarHorario("09:00");
        agenda.adicionarHorario("10:00");

        List<String> horariosLivres = agenda.listarHorariosLivres();

        assertEquals(3, horariosLivres.size());
        assertTrue(horariosLivres.contains("08:00"));
        assertTrue(horariosLivres.contains("09:00"));
        assertTrue(horariosLivres.contains("10:00"));
    }

    @Test
    void deveRemoverHorarioExistente() {
        agenda.adicionarHorario("08:00");

        agenda.removerHorario("08:00");

        assertFalse(agenda.verificarDisponibilidade("08:00"));
    }

    @Test
    void naoDeveLancarExcecaoAoRemoverHorarioInexistente() {
        assertDoesNotThrow(() -> agenda.removerHorario("18:00"));
    }

    @Test
    void deveRetornarTodosOsHorariosQuandoNaoExistiremAgendamentos() {
        agenda.adicionarHorario("08:00");
        agenda.adicionarHorario("09:00");

        List<String> horarios = agenda.listarHorariosLivres();

        assertEquals(2, horarios.size());
        assertIterableEquals(List.of("08:00", "09:00"), horarios);
    }

    @Test
    void deveRetornarSomenteHorariosLivres() {

        agenda.adicionarHorario("08:00");
        agenda.adicionarHorario("09:00");
        agenda.adicionarHorario("10:00");

        Cliente cliente = new Cliente();
        Barbeiro barbeiro = new Barbeiro();
        Servico servico = new Servico();

        Agendamento agendamento =
                new Agendamento(
                        1,
                        cliente,
                        barbeiro,
                        servico,
                        "09:00"
                );

        agenda.adicionarAgendamento(agendamento);

        List<String> horarios = agenda.listarHorariosLivres();

        assertEquals(2, horarios.size());
        assertTrue(horarios.contains("08:00"));
        assertTrue(horarios.contains("10:00"));
        assertFalse(horarios.contains("09:00"));
    }

    @Test
    void deveRetornarHorarioIndisponivelQuandoJaAgendado() {

        agenda.adicionarHorario("08:00");

        Cliente cliente = new Cliente();
        Barbeiro barbeiro = new Barbeiro();
        Servico servico = new Servico();

        Agendamento agendamento =
                new Agendamento(
                        1,
                        cliente,
                        barbeiro,
                        servico,
                        "08:00"
                );

        agenda.adicionarAgendamento(agendamento);

        assertFalse(agenda.verificarDisponibilidade("08:00"));
    }

    @Test
    void deveAdicionarAgendamentoNaLista() {

        Cliente cliente = new Cliente();
        Barbeiro barbeiro = new Barbeiro();
        Servico servico = new Servico();

        Agendamento agendamento =
                new Agendamento(
                        1,
                        cliente,
                        barbeiro,
                        servico,
                        "08:00"
                );

        agenda.adicionarAgendamento(agendamento);

        assertEquals(1, agenda.getAgendamentos().size());
        assertSame(agendamento, agenda.getAgendamentos().get(0));
    }

    @Test
    void deveRetornarListaInternaDeAgendamentos() {

        List<Agendamento> lista = agenda.getAgendamentos();

        assertNotNull(lista);
        assertTrue(lista.isEmpty());
    }

    @Test
    void toStringDeveRetornarRepresentacaoDaListaDeAgendamentos() {

        Cliente cliente = new Cliente();
        Barbeiro barbeiro = new Barbeiro();
        Servico servico = new Servico();

        Agendamento agendamento =
                new Agendamento(
                        1,
                        cliente,
                        barbeiro,
                        servico,
                        "08:00"
                );

        agenda.adicionarAgendamento(agendamento);

        assertEquals(
                agenda.getAgendamentos().toString(),
                agenda.toString()
        );
    }
}