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
  
  public int getId (){
    return id;
  }
  public void setId(int id){
    this.id = id;
  }
  public String getNome (){
    return nome;
  }
  public void setNome (String nome){

  }
  public String getEmail (){
    return email;
  }
  public void setEmail (String email){
  }
  public String getTelefone (){
    return telefone;
  }
  public void setTelefone (String telefone){
  }
  public String getSenha (){
    return senha;
  }
  public void setSenha (String senha){
  }
}
