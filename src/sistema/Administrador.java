package sistema;

public class Administrador extends Usuario {

    private int nivelAcesso;

    public Administrador() {
    }

    public Administrador(int id,
                         String nome,
                         String email,
                         String telefone,
                         String senha,
                         int nivelAcesso) {

        super(id, nome, email, telefone, senha);
        this.nivelAcesso = nivelAcesso;
    }

    public void cadastrarBarbeiro() {
        System.out.println("Barbeiro cadastrado.");
    }

    public void removerBarbeiro() {
        System.out.println("Barbeiro removido.");
    }

    public void visualizarRelatorios() {
        System.out.println("Exibindo relatórios...");
    }

    public void gerenciarUsuarios() {
        System.out.println("Gerenciando usuários...");
    }

    public int getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(int nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }
}w