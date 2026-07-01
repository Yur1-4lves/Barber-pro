package utils;

public class Formatador {

    public static String formatarHorario(String horario) {
        return horario;
    }

    public static String formatarTelefone(String telefone) {
        if (telefone.length() == 11) {
            return "(" + telefone.substring(0, 2) + ") " + telefone.substring(2, 7) + "-" + telefone.substring(7);
        }

        return telefone;
    }
}
