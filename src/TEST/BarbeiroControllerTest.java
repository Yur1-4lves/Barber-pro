package TEST;

import CONTROLLERS.BarbeiroController;
import REPOSITORIES.BarbeiroRepository;
import SISTEMA.Barbeiro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BarbeiroControllerTest {

    private BarbeiroRepository repository;
    private BarbeiroController controller;

    private Barbeiro barbeiro1;
    private Barbeiro barbeiro2;

    @BeforeEach
    void setUp() {

        repository = new BarbeiroRepository();
        controller = new BarbeiroController(repository);

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
    void deveCadastrarBarbeiro() {

        controller.cadastrarBarbeiro(barbeiro1);

        assertAll(
                () -> assertEquals(1, repository.listarTodos().size()),
                () -> assertSame(barbeiro1, repository.buscarPorId(1))
        );
    }

    @Test
    void naoDeveCadastrarBarbeiroComEmailDuplicado() {

        controller.cadastrarBarbeiro(barbeiro1);

        Barbeiro duplicado = new Barbeiro(
                3,
                "Pedro",
                "CARLOS@EMAIL.COM",
                "88777777777",
                "111111",
                "Social"
        );

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> controller.cadastrarBarbeiro(duplicado)
        );

        assertEquals(
                "E-mail já cadastrado.",
                exception.getMessage()
        );
    }

    @Test
    void deveListarBarbeirosQuandoExistiremCadastros() {

        controller.cadastrarBarbeiro(barbeiro1);
        controller.cadastrarBarbeiro(barbeiro2);

        List<Barbeiro> lista = controller.listarBarbeiros();

        assertAll(
                () -> assertEquals(2, lista.size()),
                () -> assertTrue(lista.contains(barbeiro1)),
                () -> assertTrue(lista.contains(barbeiro2))
        );
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoExistiremBarbeiros() {

        List<Barbeiro> lista = controller.listarBarbeiros();

        assertNotNull(lista);
        assertTrue(lista.isEmpty());
    }

    @Test
    void deveAtualizarDisponibilidadeParaFalse() {

        controller.cadastrarBarbeiro(barbeiro1);

        controller.atualizarDisponibilidade(
                1,
                false
        );

        assertFalse(
                repository.buscarPorId(1).isDisponibilidade()
        );
    }

    @Test
    void deveAtualizarDisponibilidadeParaTrue() {

        controller.cadastrarBarbeiro(barbeiro1);

        controller.atualizarDisponibilidade(
                1,
                false
        );

        controller.atualizarDisponibilidade(
                1,
                true
        );

        assertTrue(
                repository.buscarPorId(1).isDisponibilidade()
        );
    }

    @Test
    void naoDeveLancarExcecaoQuandoBarbeiroNaoExistir() {

        assertDoesNotThrow(() ->
                controller.atualizarDisponibilidade(
                        999,
                        false
                )
        );
    }

    @Test
    void deveAtualizarDisponibilidadeMaisDeUmaVez() {

        controller.cadastrarBarbeiro(barbeiro1);

        controller.atualizarDisponibilidade(1, false);
        assertFalse(repository.buscarPorId(1).isDisponibilidade());

        controller.atualizarDisponibilidade(1, true);
        assertTrue(repository.buscarPorId(1).isDisponibilidade());

        controller.atualizarDisponibilidade(1, false);
        assertFalse(repository.buscarPorId(1).isDisponibilidade());
    }

    @Test
    void listarBarbeirosDeveRetornarAMesmaListaDoRepositorio() {

        controller.cadastrarBarbeiro(barbeiro1);

        List<Barbeiro> lista = controller.listarBarbeiros();

        assertSame(
                repository.listarTodos(),
                lista
        );
    }
}