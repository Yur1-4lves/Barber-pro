package sistema;

public class Servico {

    private int id;
    private String nome;
    private String descricao;
    private double preco;
    private int duracao;

    public Servico() {
    }

    public Servico(int id, String nome, String descricao,
                   double preco, int duracao) {

        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.duracao = duracao;
    }

    public void alterarPreco(double novoPreco) {
        this.preco = novoPreco;
    }

    public void exibirServico() {
        System.out.println(this);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public int getDuracao() {
        return duracao;
    }

    @Override
    public String toString() {
        return "Serviço: " + nome +
                "\nDescrição: " + descricao +
                "\nPreço: R$ " + preco +
                "\nDuração: " + duracao + " min";
    }
}