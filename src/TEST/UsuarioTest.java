package TEST;

import SISTEMA.Cliente;
import SISTEMA.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    private Usuario usuario;

    @BeforeEach
    void setUp() {

        usuario = new Cliente(
                1,
                "João",
                "joao@email.com",
                "88999999999",
                "123456"
        );
    }

    @Test
    void deveRealizarLoginComCredenciaisValidas() {

        assertTrue(
                usuario.login(
                        "joao@email.com",
                        "123456"
                )
        );
    }

    @Test
    void naoDeveRealizarLoginComEmailIncorreto() {

        assertFalse(
                usuario.login(
                        "teste@email.com",
                        "123456"
                )
        );
    }

    @Test
    void naoDeveRealizarLoginComSenhaIncorreta() {

        assertFalse(
                usuario.login(
                        "joao@email.com",
                        "senhaErrada"
                )
        );
    }

    @Test
    void naoDeveRealizarLoginQuandoEmailESenhaForemInvalidos() {

        assertFalse(
                usuario.login(
                        "teste@email.com",
                        "senha"
                )
        );
    }

    @Test
    void deveAlterarSenha() {

        usuario.alterarSenha("novaSenha");

        assertTrue(
                usuario.login(
                        "joao@email.com",
                        "novaSenha"
                )
        );

        assertFalse(
                usuario.login(
                        "joao@email.com",
                        "123456"
                )
        );
    }

    @Test
    void devePermitirAlterarSenhaParaVazia() {

        usuario.alterarSenha("");

        assertTrue(
                usuario.login(
                        "joao@email.com",
                        ""
                )
        );
    }

    @Test
    void devePermitirAlterarSenhaParaNull() {

        usuario.alterarSenha(null);

        assertNull(usuario.getSenha());

        assertFalse(
                usuario.login(
                        "joao@email.com",
                        "123456"
                )
        );
    }

    @Test
    void deveAtualizarDadosDoUsuario() {

        usuario.atualizarDados(
                "Pedro",
                "pedro@email.com",
                "88988888888"
        );

        assertAll(
                () -> assertEquals("Pedro", usuario.getNome()),
                () -> assertEquals("pedro@email.com", usuario.getEmail()),
                () -> assertEquals("88988888888", usuario.getTelefone())
        );
    }

    @Test
    void devePermitirAtualizarDadosComValoresNulos() {

        usuario.atualizarDados(
                null,
                null,
                null
        );

        assertAll(
                () -> assertNull(usuario.getNome()),
                () -> assertNull(usuario.getEmail()),
                () -> assertNull(usuario.getTelefone())
        );
    }

    @Test
    void logoutNaoDeveLancarExcecao() {

        assertDoesNotThrow(usuario::logout);
    }

    @Test
    void toStringDeveConterInformacoesDoUsuario() {

        String texto = usuario.toString();

        assertAll(
                () -> assertTrue(texto.contains("ID: 1")),
                () -> assertTrue(texto.contains("João")),
                () -> assertTrue(texto.contains("joao@email.com")),
                () -> assertTrue(texto.contains("88999999999"))
        );
    }

    @Test
    void toStringNaoDeveConterSenha() {

        String texto = usuario.toString();

        assertFalse(texto.contains("123456"));
    }

    @Test
    void deveAlterarSenhaMaisDeUmaVez() {

        usuario.alterarSenha("111");

        assertTrue(
                usuario.login(
                        "joao@email.com",
                        "111"
                )
        );

        usuario.alterarSenha("222");

        assertTrue(
                usuario.login(
                        "joao@email.com",
                        "222"
                )
        );

        assertFalse(
                usuario.login(
                        "joao@email.com",
                        "111"
                )
        );
    }

    @Test
    void deveAtualizarDadosMaisDeUmaVez() {

        usuario.atualizarDados(
                "Carlos",
                "carlos@email.com",
                "111111111"
        );

        usuario.atualizarDados(
                "Marcos",
                "marcos@email.com",
                "222222222"
        );

        assertAll(
                () -> assertEquals("Marcos", usuario.getNome()),
                () -> assertEquals("marcos@email.com", usuario.getEmail()),
                () -> assertEquals("222222222", usuario.getTelefone())
        );
    }

    @Test
    void loginComEmailNullDeveRetornarFalse() {

        assertFalse(
                usuario.login(
                        null,
                        "123456"
                )
        );
    }

    @Test
    void loginComSenhaNullDeveRetornarFalse() {

        assertFalse(
                usuario.login(
                        "joao@email.com",
                        null
                )
        );
    }
}