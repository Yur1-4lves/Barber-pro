package SISTEMA;

public abstract class Usuario {
  private int id;
  private String nome;
  private String email;
  private String telefone;
  private String senha;

  public Usuario (String nome, String email, String telefone, String senha){
    this.setNome (nome);
    this.setEmail (email);
    this.setTelefone (telefone);
    this.setSenha (senha);
}
