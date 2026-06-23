package INTERFACES;

public interface Autenticavel {

    boolean login(String email, String senha);

    void logout();
}
