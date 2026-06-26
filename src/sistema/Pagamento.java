package SISTEMA;

import INTERFACES.Pagavel;

public class Pagamento implements Pagavel {

    private int id;
    private double valor;
    private String formaPagamento;
    private String statusPagamento;
    private String dataPagamento;

    public Pagamento() {
        statusPagamento = "PENDENTE";
    }

    public Pagamento(
            int id,
            double valor,
            String formaPagamento,
            String dataPagamento
    ) {
        this.id = id;
        this.valor = valor;
        this.formaPagamento = formaPagamento;
        this.dataPagamento = dataPagamento;
        this.statusPagamento = "PENDENTE";
    }

    @Override
    public boolean realizarPagamento() {
        statusPagamento = "PROCESSANDO";
        return true;
    }

    @Override
    public void confirmarPagamento() {
        statusPagamento = "PAGO";
    }

    public String gerarComprovante() {
        return "Pagamento #" + id + "\nValor: R$ " + valor + "\nForma: " + formaPagamento + "\nStatus: " + statusPagamento;
    }

    public int getId() {
        return id;
    }

    public double getValor() {
        return valor;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public String getStatusPagamento() {
        return statusPagamento;
    }

    public String getDataPagamento() {
        return dataPagamento;
    }

    @Override
    public String toString() {
        return gerarComprovante();
    }
}
