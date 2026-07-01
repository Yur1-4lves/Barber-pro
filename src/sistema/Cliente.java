package SISTEMA;

import INTERFACES.Gerenciavel;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario implements Gerenciavel{

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

    @Override
    public void realizarAgendamento(Agendamento agendamento) {
        listaAgendamentos.add(agendamento);
    }

    @Override
    public void cancelarAgendamento(Agendamento agendamento) {
        listaAgendamentos.remove(agendamento);
    }

    @Override
    public void visualizarAgendamentos() {
        for (Agendamento a : listaAgendamentos) {
            System.out.println(a);
        }
    }

    public List<Agendamento> getListaAgendamentos() {
        return listaAgendamentos;
    }

}
