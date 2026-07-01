package SISTEMA;

public class Agendamento {

    private int id;
    private Cliente cliente;
    private Barbeiro barbeiro;
    private Servico servico;

    private String horario;
    private String status;

    public Agendamento() {
        status = "PENDENTE";
    }

    public Agendamento(
            int id,
            Cliente cliente,
            Barbeiro barbeiro,
            Servico servico,
            String horario
    ) {
        this.id = id;
        this.cliente = cliente;
        this.barbeiro = barbeiro;
        this.servico = servico;
        this.horario = horario;
        this.status = "PENDENTE";
    }

    public void confirmarAgendamento() {
        status = "CONFIRMADO";
    }

    public void cancelarAgendamento() {
        status = "CANCELADO";
    }

    public void reagendar(
            String novoHorario
    ) {
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
                "\nHorário: " + horario +
                "\nStatus: " + status;
    }
}
