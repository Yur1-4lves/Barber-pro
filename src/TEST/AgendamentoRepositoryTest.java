package TEST;

import REPOSITORIES.AgendamentoRepository;
import SISTEMA.Agendamento;
import SISTEMA.Barbeiro;
import SISTEMA.Cliente;
import SISTEMA.Servico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AgendamentoRepositoryTest {

    private AgendamentoRepository repository;

    private Agendamento agendamento1;
    private Agendamento agendamento2;

    @BeforeEach
    void setUp() {

        repository = new AgendamentoRepository();

        Cliente cliente = new Cliente(
                1,
                "João",
                "joao@email.com",
                "88999999999",
                "123456"
        );

        Barbeiro barbeiro = new Barbeiro(
                1,
                "Carlos",
                "carlos@email.com",
                "88988888888",
                "123456",
                "Degradê"
        );

        Servico servico = new Servico(
                1,
                "Corte",
                "Corte tradicional",
                35.0,
                40
        );

        agendamento1 = new Agendamento(
                1,
                cliente,
                barbeiro,
                servico,
                "08:00"
        );

        agendamento2 = new Agendamento(
                2,
                cliente,
                barbeiro,
                servico,
                "09:00"
        );
    }

    @Test
    void deveSalvarAgendamento() {

        repository.salvar(agendamento1);

        assertEquals(1, repository.listarAgendamentos().size());
        assertSame(agendamento1, repository.listarAgendamentos().get(0));
    }

    @Test
    void deveSalvarMultiplosAgendamentos() {

        repository.salvar(agendamento1);
        repository.salvar(agendamento2);

        List<Agendamento> lista = repository.listarAgendamentos();

        assertAll(
                () -> assertEquals(2, lista.size()),
                () -> assertTrue(lista.contains(agendamento1)),
                () -> assertTrue(lista.contains(agendamento2))
        );
    }

    @Test
    void deveBuscarAgendamentoPorId() {

        repository.salvar(agendamento1);

        Agendamento encontrado = repository.buscarPorId(1);

        assertSame(agendamento1, encontrado);
    }

    @Test
    void deveRetornarNullQuandoIdNaoExistir() {

        repository.salvar(agendamento1);

        assertNull(repository.buscarPorId(999));
    }

    @Test
    void deveRetornarNullQuandoRepositorioEstiverVazio() {

        assertNull(repository.buscarPorId(1));
    }

    @Test
    void deveCancelarAgendamentoExistente() {

        repository.salvar(agendamento1);

        repository.cancelar(1);

        assertEquals(
                "CANCELADO",
                repository.buscarPorId(1).getStatus()
        );
    }

    @Test
    void naoDeveLancarExcecaoAoCancelarAgendamentoInexistente() {

        assertDoesNotThrow(() -> repository.cancelar(999));
    }

    @Test
    void cancelarNaoDeveRemoverAgendamentoDaLista() {

        repository.salvar(agendamento1);

        repository.cancelar(1);

        assertAll(
                () -> assertEquals(1, repository.listarAgendamentos().size()),
                () -> assertNotNull(repository.buscarPorId(1)),
                () -> assertEquals(
                        "CANCELADO",
                        repository.buscarPorId(1).getStatus()
                )
        );
    }

    @Test
    void listarAgendamentosDeveRetornarListaVaziaInicialmente() {

        assertTrue(repository.listarAgendamentos().isEmpty());
    }

    @Test
    void listarAgendamentosDeveRetornarListaComElementos() {

        repository.salvar(agendamento1);
        repository.salvar(agendamento2);

        List<Agendamento> lista = repository.listarAgendamentos();

        assertEquals(2, lista.size());
    }

    @Test
    void listarAgendamentosRetornaReferenciaDaListaInterna() {

        repository.salvar(agendamento1);

        List<Agendamento> lista = repository.listarAgendamentos();

        lista.clear();

        assertTrue(repository.listarAgendamentos().isEmpty());
    }

    @Test
    void devePermitirSalvarAgendamentosDuplicados() {

        repository.salvar(agendamento1);
        repository.salvar(agendamento1);

        assertEquals(
                2,
                repository.listarAgendamentos().size()
        );
    }

    @Test
    void devePermitirSalvarAgendamentoNulo() {

        repository.salvar(null);

        assertEquals(1, repository.listarAgendamentos().size());
        assertNull(repository.listarAgendamentos().get(0));
    }
}