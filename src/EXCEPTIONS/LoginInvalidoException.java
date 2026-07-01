package EXCEPTIONS;

public class LoginInvalidoException extends RuntimeException {

    public LoginInvalidoException(String mensagem) {
        super(mensagem);
    }
}