package repository;

import SISTEMA.Pagamento;

import java.util.ArrayList;
import java.util.List;

public class PagamentoRepository {

    private List<Pagamento> pagamentos;

    public PagamentoRepository() {
        pagamentos = new ArrayList<>();
    }

    public void salvar(Pagamento pagamento) {
        pagamentos.add(pagamento);
    }

    public Pagamento buscarPagamento(int id) {

        for (Pagamento pagamento :
                pagamentos) {

            if (pagamento.getId() == id) {
                return pagamento;
            }
        }

        return null;
    }

    public List<Pagamento> listarPagamentos() {
        return pagamentos;
    }
}
