package TEST;

import SISTEMA.Servico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServicoTest {

    private Servico servico;

    @BeforeEach
    void setUp() {
        servico = new Servico(
                1,
                "Corte",
                "Corte tradicional",
                35.0,
                40
        );
    }

    @Test
    void deveAlterarPreco() {

        servico.alterarPreco(50.0);

        assertEquals(50.0, servico.getPreco());
    }

    @Test
    void deveAlterarPrecoParaZero() {

        servico.alterarPreco(0.0);

        assertEquals(0.0, servico.getPreco());
    }

    @Test
    void devePermitirPrecoNegativo() {

        servico.alterarPreco(-10.0);

        assertEquals(-10.0, servico.getPreco());
    }

    @Test
    void deveAlterarPrecoVariasVezes() {

        servico.alterarPreco(45.0);
        assertEquals(45.0, servico.getPreco());

        servico.alterarPreco(60.0);
        assertEquals(60.0, servico.getPreco());

        servico.alterarPreco(15.5);
        assertEquals(15.5, servico.getPreco());
    }

    @Test
    void exibirServicoNaoDeveLancarExcecao() {

        assertDoesNotThrow(servico::exibirServico);
    }

    @Test
    void toStringDeveConterTodasAsInformacoes() {

        String texto = servico.toString();

        assertAll(
                () -> assertTrue(texto.contains("Corte")),
                () -> assertTrue(texto.contains("Corte tradicional")),
                () -> assertTrue(texto.contains("35.0")),
                () -> assertTrue(texto.contains("40"))
        );
    }

    @Test
    void toStringNaoDeveSerNulo() {

        assertNotNull(servico.toString());
    }

    @Test
    void construtorPadraoDeveCriarObjetoSemLancarExcecao() {

        Servico novo = new Servico();

        assertAll(
                () -> assertEquals(0, novo.getId()),
                () -> assertNull(novo.getNome()),
                () -> assertNull(novo.getDescricao()),
                () -> assertEquals(0.0, novo.getPreco()),
                () -> assertEquals(0, novo.getDuracao())
        );
    }

    @Test
    void toStringDoConstrutorPadraoNaoDeveSerNulo() {

        Servico novo = new Servico();

        String texto = novo.toString();

        assertNotNull(texto);
        assertTrue(texto.contains("Serviço"));
    }
}