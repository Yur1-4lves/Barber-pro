package sistema;

import java.util.LinkedList;
import java.util.Queue;

public class FilaEspera {

    private Queue<Cliente> listaClientes;

    private String horarioDesejado;
    private Servico servicoDesejado;

    public FilaEspera() {
        listaClientes = new LinkedList<>();
    }

    public void adicionarCliente(Cliente cliente) {
        listaClientes.offer(cliente);
    }

    public Cliente removerCliente() {
        return listaClientes.poll();
    }

    public Cliente verificarFila() {
        return listaClientes.peek();
    }

    public void notificarCliente() {

        Cliente cliente = listaClientes.peek();

        if(cliente != null){
            System.out.println(
                    "Cliente " +
                            cliente.getNome() +
                            " possui vaga disponível."
            );
        }
    }

    public Queue<Cliente> getListaClientes() {
        return listaClientes;
    }
}