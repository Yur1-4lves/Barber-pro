package EXCEPTIONS;

public class ClienteInexistenteException extends RuntimeException {

    public ClienteInexistenteException(String mensagem) {
        super(mensagem);
    }
}