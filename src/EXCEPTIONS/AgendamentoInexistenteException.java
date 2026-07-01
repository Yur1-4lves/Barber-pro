package EXCEPTIONS;

public class AgendamentoInexistenteException extends RuntimeException {

    public AgendamentoInexistenteException(String mensagem) {
        super(mensagem);
    }
}