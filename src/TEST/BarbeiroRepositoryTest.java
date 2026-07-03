package TEST;

import REPOSITORIES.BarbeiroRepository;
import SISTEMA.Barbeiro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BarbeiroRepositoryTest {

    private BarbeiroRepository repository;

    private Barbeiro barbeiro1;
    private Barbeiro barbeiro2;

    @BeforeEach
    void setUp() {

        repository = new BarbeiroRepository();

        barbeiro1 = new Barbeiro(
                1,
                "Carlos",
                "carlos@email.com",
                "88999999999",
                "123456",
                "Degradê"
        );

        barbeiro2 = new Barbeiro(
                2,
                "Marcos",
                "marcos@email.com",
                "88988888888",
                "654321",
                "Navalhado"
        );
    }

    @Test
    void deveSalvarBarbeiro() {

        repository.salvar(barbeiro1);

        assertEquals(1, repository.listarTodos().size());
        assertSame(barbeiro1, repository.listarTodos().get(0));
    }

    @Test
    void deveSalvarDoisBarbeiros() {

        repository.salvar(barbeiro1);
        repository.salvar(barbeiro2);

        List<Barbeiro> barbeiros = repository.listarTodos();

        assertAll(
                () -> assertEquals(2, barbeiros.size()),
                () -> assertTrue(barbeiros.contains(barbeiro1)),
                () -> assertTrue(barbeiros.contains(barbeiro2))
        );
    }

    @Test
    void naoDevePermitirSalvarBarbeiroComEmailDuplicado() {

        repository.salvar(barbeiro1);

        Barbeiro duplicado = new Barbeiro(
                3,
                "Outro",
                "CARLOS@EMAIL.COM",
                "88777777777",
                "999999",
                "Social"
        );

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> repository.salvar(duplicado)
        );

        assertEquals(
                "E-mail já cadastrado.",
                exception.getMessage()
        );

        assertEquals(1, repository.listarTodos().size());
    }

    @Test
    void deveBuscarBarbeiroPorId() {

        repository.salvar(barbeiro1);

        Barbeiro encontrado = repository.buscarPorId(1);

        assertSame(barbeiro1, encontrado);
    }

    @Test
    void deveRetornarNullQuandoIdNaoExistir() {

        repository.salvar(barbeiro1);

        assertNull(repository.buscarPorId(999));
    }

    @Test
    void deveRetornarNullQuandoRepositorioEstiverVazio() {

        assertNull(repository.buscarPorId(1));
    }

    @Test
    void deveRemoverBarbeiroExistente() {

        repository.salvar(barbeiro1);
        repository.salvar(barbeiro2);

        repository.remover(1);

        assertAll(
                () -> assertEquals(1, repository.listarTodos().size()),
                () -> assertNull(repository.buscarPorId(1)),
                () -> assertSame(barbeiro2, repository.buscarPorId(2))
        );
    }

    @Test
    void naoDeveLancarExcecaoAoRemoverBarbeiroInexistente() {

        assertDoesNotThrow(() -> repository.remover(999));

        assertTrue(repository.listarTodos().isEmpty());
    }

    @Test
    void deveRetornarListaVaziaInicialmente() {

        assertTrue(repository.listarTodos().isEmpty());
    }

    @Test
    void listarTodosDeveRetornarListaComElementos() {

        repository.salvar(barbeiro1);
        repository.salvar(barbeiro2);

        List<Barbeiro> barbeiros = repository.listarTodos();

        assertEquals(2, barbeiros.size());
    }

    @Test
    void listarTodosDeveRetornarReferenciaDaListaInterna() {

        repository.salvar(barbeiro1);

        List<Barbeiro> lista = repository.listarTodos();

        lista.clear();

        assertTrue(repository.listarTodos().isEmpty());
    }

    @Test
    void deveManterOrdemDeInsercao() {

        repository.salvar(barbeiro1);
        repository.salvar(barbeiro2);

        List<Barbeiro> lista = repository.listarTodos();

        assertAll(
                () -> assertSame(barbeiro1, lista.get(0)),
                () -> assertSame(barbeiro2, lista.get(1))
        );
    }
}