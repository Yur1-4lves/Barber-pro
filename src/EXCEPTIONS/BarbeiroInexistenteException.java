package EXCEPTIONS;

public class BarbeiroInexistenteException extends RuntimeException {

    public BarbeiroInexistenteException(String mensagem) {
        super(mensagem);
    }
}