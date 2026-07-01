package SISTEMA;

import java.util.ArrayList;
import java.util.List;

public class Barbeiro extends Usuario {

    private String especialidade;
    private Agenda agenda;
    private double notaMedia;
    private boolean disponibilidade;


    public Barbeiro() {
        agenda = new Agenda();
        disponibilidade = true;
    }

    public Barbeiro(
            int id,
            String nome,
            String email,
            String telefone,
            String senha,
            String especialidade
    ) {
        super(id, nome, email, telefone, senha);

        this.especialidade = especialidade;
        this.agenda = new Agenda();
        this.disponibilidade = true;
    }

    public void visualizarAgenda() {
        System.out.println(agenda);
    }

    public void atualizarDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public void confirmarAgendamento() {
        System.out.println("Agendamento confirmado.");
    }


    public String getEspecialidade() {
        return especialidade;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public double getNotaMedia() {
        return notaMedia;
    }

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    @Override
    public String toString() {
        return super.toString() + "\nEspecialidade: " + especialidade;
    }
}
