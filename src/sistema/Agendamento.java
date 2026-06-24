package sistema;

public class Agendamento {

    private int id;
    private Cliente cliente;
    private Barbeiro barbeiro;
    private Servico servico;

    private String data;
    private String horario;
    private String status;

    public Agendamento() {
        status = "PENDENTE";
    }

    public Agendamento(int id,
                       Cliente cliente,
                       Barbeiro barbeiro,
                       Servico servico,
                       String data,
                       String horario) {

        this.id = id;
        this.cliente = cliente;
        this.barbeiro = barbeiro;
        this.servico = servico;
        this.data = data;
        this.horario = horario;
        this.status = "PENDENTE";
    }

    public void confirmarAgendamento() {
        status = "CONFIRMADO";
    }

    public void cancelarAgendamento() {
        status = "CANCELADO";
    }

    public void reagendar(String novaData,
                          String novoHorario) {

        this.data = novaData;
        this.horario = novoHorario;
    }

    public void exibirDetalhes() {
        System.out.println(this);
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Barbeiro getBarbeiro() {
        return barbeiro;
    }

    public Servico getServico() {
        return servico;
    }

    public String getData() {
        return data;
    }

    public String getHorario() {
        return horario;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {

        return "Agendamento #" + id +
                "\nCliente: " + cliente.getNome() +
                "\nBarbeiro: " + barbeiro.getNome() +
                "\nServiço: " + servico.getNome() +
                "\nData: " + data +
                "\nHorário: " + horario +
                "\nStatus: " + status;
    }
}