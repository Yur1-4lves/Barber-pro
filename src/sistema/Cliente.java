package sistema;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {

    private List<Agendamento> listaAgendamentos;


    public Cliente() {
        listaAgendamentos = new ArrayList<>();
    }

    public Cliente(
            int proximoId,
            String nome,
            String email,
            String telefone,
            String senha
    ) {
        super(proximoId, nome, email, telefone, senha);

        listaAgendamentos = new ArrayList<>();
    }

    public void realizarAgendamento(Agendamento agendamento) {
        listaAgendamentos.add(agendamento);
    }

    public void cancelarAgendamento(Agendamento agendamento) {
        listaAgendamentos.remove(agendamento);
    }

    public void visualizarAgendamentos() {
        for (Agendamento a : listaAgendamentos) {
            System.out.println(a);
        }
    }

    public List<Agendamento> getListaAgendamentos() {
        return listaAgendamentos;
    }

}
