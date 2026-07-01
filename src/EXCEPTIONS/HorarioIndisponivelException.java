package EXCEPTIONS;

public class HorarioIndisponivelException extends RuntimeException {

    public HorarioIndisponivelException(String mensagem) {
        super(mensagem);
    }
}