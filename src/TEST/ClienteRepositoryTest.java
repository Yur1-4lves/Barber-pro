package TEST;

import REPOSITORIES.ClienteRepository;
import SISTEMA.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClienteRepositoryTest {

    private ClienteRepository repository;

    private Cliente cliente1;
    private Cliente cliente2;

    @BeforeEach
    void setUp() {

        repository = new ClienteRepository();

        cliente1 = new Cliente(
                1,
                "João",
                "joao@email.com",
                "88999999999",
                "123456"
        );

        cliente2 = new Cliente(
                2,
                "Maria",
                "maria@email.com",
                "88988888888",
                "654321"
        );
    }

    @Test
    void deveSalvarCliente() {

        repository.salvar(cliente1);

        assertEquals(1, repository.listarTodos().size());
        assertSame(cliente1, repository.listarTodos().get(0));
    }

    @Test
    void deveSalvarDoisClientes() {

        repository.salvar(cliente1);
        repository.salvar(cliente2);

        List<Cliente> clientes = repository.listarTodos();

        assertAll(
                () -> assertEquals(2, clientes.size()),
                () -> assertTrue(clientes.contains(cliente1)),
                () -> assertTrue(clientes.contains(cliente2))
        );
    }

    @Test
    void naoDevePermitirSalvarClientesComMesmoEmail() {

        Cliente duplicado = new Cliente(
                3,
                "Pedro",
                "JOAO@EMAIL.COM",
                "88977777777",
                "999999"
        );

        repository.salvar(cliente1);

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
    void deveBuscarClientePorId() {

        repository.salvar(cliente1);

        Cliente encontrado = repository.buscarPorId(1);

        assertSame(cliente1, encontrado);
    }

    @Test
    void deveRetornarNullQuandoIdNaoExistir() {

        repository.salvar(cliente1);

        assertNull(repository.buscarPorId(999));
    }

    @Test
    void deveRetornarNullQuandoRepositorioEstiverVazio() {

        assertNull(repository.buscarPorId(1));
    }

    @Test
    void deveBuscarClientePorEmail() {

        repository.salvar(cliente1);

        Cliente encontrado =
                repository.buscarPorEmail("joao@email.com");

        assertSame(cliente1, encontrado);
    }

    @Test
    void deveBuscarClientePorEmailIgnorandoMaiusculasEMinusculas() {

        repository.salvar(cliente1);

        Cliente encontrado =
                repository.buscarPorEmail("JOAO@EMAIL.COM");

        assertSame(cliente1, encontrado);
    }

    @Test
    void deveRetornarNullQuandoEmailNaoExistir() {

        repository.salvar(cliente1);

        assertNull(
                repository.buscarPorEmail("teste@email.com")
        );
    }

    @Test
    void deveRetornarNullAoBuscarEmailComRepositorioVazio() {

        assertNull(
                repository.buscarPorEmail("teste@email.com")
        );
    }

    @Test
    void deveRemoverClienteExistente() {

        repository.salvar(cliente1);
        repository.salvar(cliente2);

        repository.remover(1);

        assertAll(
                () -> assertEquals(1, repository.listarTodos().size()),
                () -> assertNull(repository.buscarPorId(1)),
                () -> assertSame(cliente2, repository.buscarPorId(2))
        );
    }

    @Test
    void naoDeveLancarExcecaoAoRemoverClienteInexistente() {

        assertDoesNotThrow(
                () -> repository.remover(999)
        );

        assertTrue(repository.listarTodos().isEmpty());
    }

    @Test
    void deveRetornarListaVaziaInicialmente() {

        assertTrue(repository.listarTodos().isEmpty());
    }

    @Test
    void listarTodosDeveRetornarReferenciaDaListaInterna() {

        repository.salvar(cliente1);

        List<Cliente> lista = repository.listarTodos();

        lista.clear();

        assertTrue(repository.listarTodos().isEmpty());
    }

    @Test
    void deveManterOrdemDeInsercao() {

        repository.salvar(cliente1);
        repository.salvar(cliente2);

        List<Cliente> clientes = repository.listarTodos();

        assertAll(
                () -> assertSame(cliente1, clientes.get(0)),
                () -> assertSame(cliente2, clientes.get(1))
        );
    }
}