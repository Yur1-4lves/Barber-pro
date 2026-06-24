package sistema;

public class Avaliacao {

    private int id;
    private Cliente cliente;
    private Barbeiro barbeiro;

    private int nota;
    private String comentario;

    public Avaliacao() {
    }

    public Avaliacao(int id,
                     Cliente cliente,
                     Barbeiro barbeiro,
                     int nota,
                     String comentario) {

        this.id = id;
        this.cliente = cliente;
        this.barbeiro = barbeiro;
        this.nota = nota;
        this.comentario = comentario;
    }

    public void enviarAvaliacao() {
        barbeiro.adicionarAvaliacao(this);
    }

    public void editarComentario(String comentario) {
        this.comentario = comentario;
    }

    public void excluirAvaliacao() {
        comentario = "";
        nota = 0;
    }

    public int getNota() {
        return nota;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Barbeiro getBarbeiro() {
        return barbeiro;
    }

    @Override
    public String toString() {
        return "Nota: " + nota +
                "\nComentário: " + comentario;
    }
}