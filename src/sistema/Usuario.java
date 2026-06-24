package sistema;

import interfaces.Autenticavel;

public abstract class Usuario implements Autenticavel {

    private int id;
    private String nome;
    private String email;
    private String telefone;
    private String senha;

    public Usuario() {
    }

    public Usuario(int id, String nome, String email,
                   String telefone, String senha) {

        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
    }

    @Override
    public boolean login(String email, String senha) {
        return this.email.equals(email)
                && this.senha.equals(senha);
    }

    @Override
    public void logout() {
        System.out.println("Logout realizado.");
    }

    public void alterarSenha(String novaSenha) {
        this.senha = novaSenha;
    }

    public void atualizarDados(String nome,
                               String email,
                               String telefone) {

        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                "\nNome: " + nome +
                "\nEmail: " + email +
                "\nTelefone: " + telefone;
    }
}