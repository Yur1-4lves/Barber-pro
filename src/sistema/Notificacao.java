package sistema;

import interfaces.Notificavel;

public class Notificacao implements Notificavel {

    private int id;
    private String mensagem;
    private String destinatario;
    private String dataEnvio;
    private boolean lida;

    public Notificacao() {
    }

    public Notificacao(int id,
                       String mensagem,
                       String destinatario,
                       String dataEnvio) {

        this.id = id;
        this.mensagem = mensagem;
        this.destinatario = destinatario;
        this.dataEnvio = dataEnvio;
        this.lida = false;
    }

    @Override
    public void enviarNotificacao(String mensagem) {
        System.out.println("Mensagem enviada: " + mensagem);
    }

    @Override
    public void receberNotificacao(String mensagem) {
        System.out.println("Nova notificação: " + mensagem);
    }

    public void marcarComoLida() {
        lida = true;
    }

    public void visualizarNotificacao() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Mensagem: " + mensagem +
                "\nDestinatário: " + destinatario +
                "\nData: " + dataEnvio +
                "\nLida: " + lida;
    }
}