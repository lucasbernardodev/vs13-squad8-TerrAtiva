package app.menus;
import controllers.LocatarioController;
import models.Locatario;
import services.LocatarioService;
import util.ConferenciaDeUsuario;
import util.RetornaId;
import util.Validacao;

import java.util.Scanner;

import static app.menus.TempMenu.locadorService;

public class LocatarioMenu {
    static Scanner scanner = new Scanner(System.in);
    static LocatarioController locatarioController = new LocatarioController();
    static LocatarioService locatarioService = new LocatarioService();
    static ConferenciaDeUsuario conferenciaDeUsuario = new ConferenciaDeUsuario();
    static RetornaId retornaId = new RetornaId();
    static int cadastro;
    private static int area;

    static Locatario locatarioLogado = new Locatario(0, "","","","");
    public static void menuLocatario() {
        do {
            System.out.println("Selecione uma opção: ");
            System.out.println("1 - Login");
            System.out.println("2 - Cadastrar-se");

            cadastro = Validacao.validarInt();

            switch (cadastro) {
                case 1:
                    menuLogin();
                    break;
                case 2:
                    menuCadastro();
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, insira um valor válido.");
            }

        } while (cadastro < 1 || cadastro > 2);

        boolean retorno;

        int area;

        Locatario locatarioLogado = new Locatario(0, "", "", "", "");
        do {
            System.out.println("Área de Login");
            System.out.println("Selecione uma opção: ");
            System.out.println("2 - Locatario");
            area = Validacao.validarInt();
            String nomeLocatario = Validacao.validarString("Digite seu nome de usuário:");
            String senhaLocatario = Validacao.validarString("Digite sua senha:");
            retorno = conferenciaDeUsuario.conferencia(area, nomeLocatario, senhaLocatario);
            if (!retorno) {
                System.out.println("Dados incorretos, tente novamente");
            } else {
                if (area == 2) {
                    locatarioLogado = locatarioService.resgatarLocatarios(
                            retornaId.retornaId(area, nomeLocatario, senhaLocatario));
                    System.err.println("Logado!");
                }
            }
        } while (!retorno);

        int menuAreaLogada;
        if (area == 2) {
            do {
                System.out.println("Bem-vindo a área logada");
                System.out.println("O que você deseja fazer");
                System.out.println("1 - Imprimir Perfil");
                System.out.println("2 - Atualizar Perfil");
                //preciso verificar opções específicas para o Locatário
                System.out.println("0 - Sair");
                menuAreaLogada = Integer.parseInt(scanner.nextLine());
                switch (menuAreaLogada) {
                    case 1:
                        locatarioService.imprimirPerfil(locatarioLogado.getId());
                        break;
                    case 2:
                        System.out.println("Vamos atualizar seu perfil:");
                        String nomeUsuarioAtualizar = Validacao.validarString("Usuário novo:");
                        String emailAtualizar = Validacao.validarString("E-mail novo:");
                        String nomeAtualizar = Validacao.validarString("Nome novo:");
                        String dataAtualizar = Validacao.validarString("Data de nascimento:");
                        locatarioController.atualizarPerfil(
                                new Locatario(
                                        locatarioLogado.getId(),
                                        nomeUsuarioAtualizar,
                                        emailAtualizar,
                                        nomeAtualizar,
                                        dataAtualizar));
                        break;
                    // Adicione aqui mais opções específicas para o Locatário
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida");
                }
            } while (menuAreaLogada != 0);
        }
    }

    public static void menuCadastro() {
        System.out.println("Bem vindo a área de cadastro!");
        System.out.println("""
                2 - Quero arrendar para alguém
                0 - Digite zero para retornar a etapa anterior.
                """);

        int locatarioOuLocador = Validacao.validarInt();

        switch (locatarioOuLocador) {
            case 2:
                System.out.println("Vamos precisar de alguns dados para seu cadastro");
                System.out.println(
                        locatarioController.cadastrar(
                                Validacao.validarString("Nome de usuário: "),
                                Validacao.validarString("E-mail: "),
                                Validacao.validarString("Senha: "),
                                Validacao.validarString("Nome completo: "),
                                Validacao.validarString("Data de nascimento: ")));
                cadastro = 1;
                break;
            case 0:
                break;
            default:
                System.out.println("Valor digitado inválido, vamos recomeçar");
        }
    }
    private static void menuLogin() {
        boolean retorno;

        do {
            System.out.println("Área de Login");
            System.out.println("Selecione uma opção: ");
            System.out.println("1 - Locador");
            System.out.println("2 - Locatario");
            area = Validacao.validarInt();
            if (area < 1 || area > 2) System.out.println("Opção inválida. Por favor, insira um valor válido.");
        } while (area < 1 || area > 2);

        do {

            String nomeLocador = Validacao.validarString("Digite seu nome de usuário:");
            String senhaLocador = Validacao.validarString("Digite sua senha:");
            retorno = conferenciaDeUsuario.conferencia(area, nomeLocador, senhaLocador);
            if (!retorno)
            {
                System.out.println("Dados incorretos, tente novamente");
            }
            else
            {
                if (area == 1) {
                    Object locadorLogado = locadorService.resgatarLocador(
                            retornaId.retornaId(area, nomeLocador, senhaLocador));
                    System.err.println("Logado!");
                }
                if (area == 2) {
                    locatarioLogado = locatarioService.resgatarLocatarios(
                            retornaId.retornaId(area, nomeLocador, senhaLocador));
                    System.err.println("Logado!");
                }
            }
        } while (!retorno);
    }


}
