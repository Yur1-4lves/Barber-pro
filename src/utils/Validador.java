package utils;

public class Validador {

    public static boolean validarEmail(String email) {
        return email != null
                && email.contains("@")
                && email.contains(".");
    }

    public static boolean validarSenha(String senha) {
        return senha != null && senha.length() >= 6;
    }

    public static boolean validarTelefone(String telefone) {
        return telefone != null && telefone.length() >= 10;
    }
}
