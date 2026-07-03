package TEST;

import CONTROLLERS.ClienteController;
import REPOSITORIES.ClienteRepository;
import SISTEMA.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteControllerTest {

    private ClienteRepository repository;
    private ClienteController controller;

    private Cliente cliente;

    @BeforeEach
    void setUp() {

        repository = new ClienteRepository();
        controller = new ClienteController(repository);

        cliente = new Cliente(
                1,
                "João",
                "joao@email.com",
                "88999999999",
                "123456"
        );
    }

    @Test
    void deveCadastrarCliente() {

        controller.cadastrarCliente(cliente);

        assertAll(
                () -> assertEquals(1, repository.listarTodos().size()),
                () -> assertSame(cliente, repository.buscarPorId(1))
        );
    }

    @Test
    void naoDeveCadastrarClienteComEmailDuplicado() {

        controller.cadastrarCliente(cliente);

        Cliente duplicado = new Cliente(
                2,
                "Pedro",
                "JOAO@EMAIL.COM",
                "88777777777",
                "654321"
        );

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> controller.cadastrarCliente(duplicado)
        );

        assertEquals(
                "E-mail já cadastrado.",
                exception.getMessage()
        );
    }

    @Test
    void deveRealizarLoginComCredenciaisValidas() {

        controller.cadastrarCliente(cliente);

        assertTrue(
                controller.loginCliente(
                        "joao@email.com",
                        "123456"
                )
        );
    }

    @Test
    void naoDeveRealizarLoginComSenhaIncorreta() {

        controller.cadastrarCliente(cliente);

        assertFalse(
                controller.loginCliente(
                        "joao@email.com",
                        "senhaErrada"
                )
        );
    }

    @Test
    void naoDeveRealizarLoginQuandoClienteNaoExistir() {

        assertFalse(
                controller.loginCliente(
                        "inexistente@email.com",
                        "123456"
                )
        );
    }

    @Test
    void deveEditarPerfilDoCliente() {

        controller.cadastrarCliente(cliente);

        controller.editarPerfil(
                1,
                "Carlos",
                "carlos@email.com",
                "88988888888"
        );

        Cliente atualizado = repository.buscarPorId(1);

        assertAll(
                () -> assertEquals("Carlos", atualizado.getNome()),
                () -> assertEquals("carlos@email.com", atualizado.getEmail()),
                () -> assertEquals("88988888888", atualizado.getTelefone())
        );
    }

    @Test
    void naoDeveLancarExcecaoAoEditarClienteInexistente() {

        assertDoesNotThrow(() ->
                controller.editarPerfil(
                        999,
                        "Teste",
                        "teste@email.com",
                        "11111111111"
                )
        );
    }

    @Test
    void devePermitirEditarDadosComValoresNulos() {

        controller.cadastrarCliente(cliente);

        controller.editarPerfil(
                1,
                null,
                null,
                null
        );

        Cliente atualizado = repository.buscarPorId(1);

        assertAll(
                () -> assertNull(atualizado.getNome()),
                () -> assertNull(atualizado.getEmail()),
                () -> assertNull(atualizado.getTelefone())
        );
    }

    @Test
    void devePermitirEditarPerfilMaisDeUmaVez() {

        controller.cadastrarCliente(cliente);

        controller.editarPerfil(
                1,
                "Pedro",
                "pedro@email.com",
                "11111111111"
        );

        controller.editarPerfil(
                1,
                "Marcos",
                "marcos@email.com",
                "22222222222"
        );

        Cliente atualizado = repository.buscarPorId(1);

        assertAll(
                () -> assertEquals("Marcos", atualizado.getNome()),
                () -> assertEquals("marcos@email.com", atualizado.getEmail()),
                () -> assertEquals("22222222222", atualizado.getTelefone())
        );
    }
}