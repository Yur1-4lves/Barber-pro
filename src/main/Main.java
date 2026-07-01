package main;

import CONTROLLERS.AgendamentoController;
import CONTROLLERS.BarbeiroController;
import CONTROLLERS.ClienteController;

import EXCEPTIONS.HorarioIndisponivelException;
import REPOSITORIES.AgendamentoRepository;
import REPOSITORIES.BarbeiroRepository;
import REPOSITORIES.ClienteRepository;

import SERVICES.AgendamentoService;

import SISTEMA.*;
import UTILITÁRIOS.GeradorDeId;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ClienteRepository clienteRepository = new ClienteRepository();
        BarbeiroRepository barbeiroRepository = new BarbeiroRepository();
        AgendamentoRepository agendamentoRepository = new AgendamentoRepository();

        AgendamentoService agendamentoService = new AgendamentoService(agendamentoRepository);
       
        GeradorDeId geradorDeIdParaCliente = new GeradorDeId();
        GeradorDeId geradorDeIdParaBarbeiro = new GeradorDeId();
        GeradorDeId geradorDeIdParaAgendamento = new GeradorDeId();

        ClienteController clienteController = new ClienteController(clienteRepository);
        BarbeiroController barbeiroController = new BarbeiroController(barbeiroRepository);
        AgendamentoController agendamentoController = new AgendamentoController(
                agendamentoService,
                agendamentoRepository
        );



        int opcao;

        do {
            System.out.println("\n===== BARBERPRO =====");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Cadastrar Barbeiro");
            System.out.println("3 - Listar Clientes");
            System.out.println("4 - Listar Barbeiros");
            System.out.println("5 - Criar Agendamento");
            System.out.println("6 - Cancelar Agendamento");
            System.out.println("7 - Exibir Agenda");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("\nCADASTRO CLIENTE");

                    int idCliente = geradorDeIdParaCliente.gerarId();

                    System.out.print("Nome: ");
                    String nomeCliente = scanner.nextLine();

                    System.out.print("Email: ");
                    String emailCliente = scanner.nextLine();

                    System.out.print("Telefone: ");
                    String telefoneCliente = scanner.nextLine();

                    System.out.print("Senha: ");
                    String senhaCliente = scanner.nextLine();

                    Cliente cliente = new Cliente(
                            geradorDeIdParaCliente.gerarId(),
                            nomeCliente,
                            emailCliente,
                            telefoneCliente,
                            senhaCliente
                    );

                    clienteController.cadastrarCliente(cliente);

                    System.out.println("Cliente cadastrado!");
                    break;

                case 2:
                    System.out.println("\nCADASTRO BARBEIRO");


                    int idBarbeiro = geradorDeIdParaBarbeiro.gerarId();

                    System.out.print("Nome: ");
                    String nomeBarbeiro = scanner.nextLine();

                    System.out.print("Email: ");
                    String emailBarbeiro = scanner.nextLine();

                    System.out.print("Telefone: ");
                    String telefoneBarbeiro = scanner.nextLine();

                    System.out.print("Senha: ");
                    String senhaBarbeiro = scanner.nextLine();

                    System.out.print("Especialidade: ");

                    String especialidade = scanner.nextLine();

                    Barbeiro barbeiro = new Barbeiro(
                            idBarbeiro,
                            nomeBarbeiro,
                            emailBarbeiro,
                            telefoneBarbeiro,
                            senhaBarbeiro,
                            especialidade
                    );

                    barbeiro.getAgenda().adicionarHorario("08:00");
                    barbeiro.getAgenda().adicionarHorario("09:00");
                    barbeiro.getAgenda().adicionarHorario("10:00");
                    barbeiro.getAgenda().adicionarHorario("11:00");
                    barbeiro.getAgenda().adicionarHorario("14:00");
                    barbeiro.getAgenda().adicionarHorario("15:00");
                    barbeiro.getAgenda().adicionarHorario("16:00");
                    barbeiro.getAgenda().adicionarHorario("17:00");
                    barbeiro.getAgenda().adicionarHorario("18:00");

                    barbeiroController.cadastrarBarbeiro(barbeiro);

                    System.out.println("Barbeiro cadastrado!");

                    break;

                case 3:
                    System.out.println("\nLISTA DE CLIENTES");

                    for (Cliente c : clienteRepository.listarTodos()) {
                        System.out.println(c);
                        System.out.println("----------------");
                    }

                    break;

                case 4:
                    System.out.println("\nLISTA DE BARBEIROS");

                    for (Barbeiro b : barbeiroRepository.listarTodos()) {
                        System.out.println(b);
                        System.out.println("----------------");
                    }

                    break;

                case 5:

                    int idAgendamento = geradorDeIdParaAgendamento.gerarId();

                    for (Cliente c : clienteRepository.listarTodos()) {
                        System.out.println("ID: " + c.getId() + ": " + c.getNome());
                        System.out.println("----------------");
                    }

                    System.out.print("ID do Cliente: ");
                    int clienteId = scanner.nextInt();

                    Cliente cli = clienteRepository.buscarPorId(clienteId);
                    if (cli == null) {
                        System.out.println("Cliente inexistente.");

                        break;
                    }

                    for (Barbeiro b : barbeiroRepository.listarTodos()) {
                        System.out.println("ID: " + b.getId() + ": " + b.getNome());
                        System.out.println("----------------");
                    }

                    System.out.print("ID do Barbeiro: ");
                    int barbeiroId = scanner.nextInt();

                    scanner.nextLine();



                    Barbeiro barb = barbeiroRepository.buscarPorId(barbeiroId);

                    if (barb == null) {
                        System.out.println("Barbeiro inexistente.");

                        break;
                    }



                    System.out.println(barb.getAgenda().listarHorariosLivres());

                    System.out.print("Horario: ");
                    String horario = scanner.nextLine();

                    boolean horarioDisponivel = barb.getAgenda().verificarDisponibilidade(horario);

                    if (!horarioDisponivel) {
                        throw new HorarioIndisponivelException("Horário indisponivel");
                    }

                    Servico servico = new Servico(
                            1,
                            "Corte",
                            "Corte simples",
                            30.0,
                            30
                    );

                    Agendamento ag = new Agendamento(
                            idAgendamento,
                            cli,
                            barb,
                            servico,
                            horario
                    );

                    agendamentoController.criarAgendamento(ag);
                    barb.getAgenda().adicionarAgendamento(ag);

                    System.out.println("Agendamento criado.");

                    break;

                case 6:

                    System.out.println("\n===== AGENDAMENTOS =====");

                    for (Agendamento a : agendamentoRepository.listarAgendamentos()) {

                        System.out.println(
                                "ID: " + a.getId() +
                                        " | Cliente: " + a.getCliente().getNome() +
                                        " | Barbeiro: " + a.getBarbeiro().getNome() +
                                        " | Horário: " + a.getHorario());
                    }

                    if(agendamentoRepository.listarAgendamentos().isEmpty()){
                        System.out.println("Não existe nenhum agendamento.");
                    }else {

                        System.out.println("----------------------------");

                        System.out.print("Informe o ID do agendamento que deseja cancelar: ");
                        int id = scanner.nextInt();

                        agendamentoController.cancelarAgendamento(id);
                    }

                    break;

                case 7:
                    if (barbeiroRepository.listarTodos().isEmpty()) {

                        System.out.println("Não existe nenhum barbeiro cadastrado.");
                    }else {

                        for (Barbeiro b : barbeiroRepository.listarTodos()) {

                            System.out.println(
                                    "ID: " + b.getId() +
                                            " | Nome: " + b.getNome() +
                                            " | Especialidade: " + b.getEspecialidade());
                        }

                            System.out.print("ID Barbeiro: ");

                            int agendaBarb = scanner.nextInt();

                            Barbeiro br = barbeiroRepository.buscarPorId(agendaBarb);

                            if (br != null) {
                                br.visualizarAgenda();
                            }

                    }

                    break;


                case 0:
                    System.out.println("Encerrando sistema...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        scanner.close();
    }
}
