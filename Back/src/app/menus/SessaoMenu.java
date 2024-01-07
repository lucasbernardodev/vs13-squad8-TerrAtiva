package app.menus;

import controllers.LocadorController;
import controllers.LocatarioController;
import controllers.LoginController;
import models.Locador;
import models.Locatario;
import services.LocadorService;
import services.LocatarioService;
import util.ConferenciaDeUsuario;
import util.RetornaId;
import util.Validacao;

public class SessaoMenu {
    private static final LocadorController locadorController = new LocadorController();
    private static final LocatarioController locatarioController = new LocatarioController();
    private  static  final  LoginController loginController = new LoginController();
    private static final LocadorService locadorService = new LocadorService();
    private static final LocatarioService locatarioService = new LocatarioService();
    private static final ConferenciaDeUsuario conferenciaDeUsuario = new ConferenciaDeUsuario();
    private static final RetornaId retornaId = new RetornaId();
    private static int cadastro;
    private static int area;

    //TODO: Verificar forma de salvar usuario logado globalmente na aplicação.
    static Locador locadorLogado = new Locador(0, "","","","");
    static Locatario locatarioLogado = new Locatario(0, "","","","");

    public static void menuInicial() {

        do{
            System.out.println("Selecione uma opção: ");
            System.out.println("1 - Login");
            System.out.println("2 - Cadastrar-se");

            cadastro = Validacao.validarInt();

            switch (cadastro){
                case 1:
                    menuLogin();
                    break;
                case 2:
                    menuCadastro();
                    System.out.println("|-------------------------------------------|");
                    System.out.println("Bem vindo de volta! Selecione uma opção: ");
                    menuLogin();
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, insira um valor válido.");
            }

        } while (cadastro < 1 || cadastro > 2);

        if (area == 1){
            //TODO: Chamar menur inicial do LocadorMenu
            System.err.println("Stop App");
            System.err.println("Menu Em Construção");
//            do {
//                System.out.println("Bem-vindo a área logada");
//                System.out.println("O que você deseja fazer");
//                System.out.println("1 - Imprimir Perfil");
//                System.out.println("2 - Atualizar Perfil");
//                System.out.println("3 - Entrar no menu de terrenos");
//                System.out.println("4 - Deletar Perfil");
//                System.out.println("0 - Sair");
//                menuAreaLogada = Integer.parseInt(scanner.nextLine());
//                switch (menuAreaLogada){
//                    case 1:
//                        locadorService.imprimirPerfil(locadorLogado.getId());
//                        break;
//                    case 2:
//                        System.out.println("Vamos atualizar seu perfil:");
//                        String nomeUsuarioAtualizar = Validacao.validarString("Usuário novo:");
//                        String emailAtualizar = Validacao.validarString("E-mail novo:");
//                        String nomeAtualizar = Validacao.validarString("Nome novo:");
//                        String dataAtualizar = Validacao.validarString("Data de nascimento:");
//                        locadorController.atualizarPerfil(
//                                new Locador(
//                                        locadorLogado.getId(),
//                                        nomeUsuarioAtualizar,
//                                        emailAtualizar,
//                                        nomeAtualizar,
//                                        dataAtualizar));
//                        break;
//                    case 3:
//                        int menuTerrenos;
//                        do {
//                            System.out.println("Bem-vindo ao menu dos terrenos do locador");
//                            System.out.println("O que você deseja fazer!");
//                            System.out.println("1 - Cadastrar terreno");
//                            System.out.println("2 - Atualizar dados dos terrenos");
//                            System.out.println("3 - Deletar terreno");
//                            System.out.println("0 - Sair");
//                            menuTerrenos = Integer.parseInt(scanner.nextLine());
//
//                            //TODO: Precisa Continuar
//                        } while (menuTerrenos == 0);
//                    case 4:
//                        System.out.println("Tem certeza que seja deletar seu perfil?");
//                        System.out.println("1 - Sim");
//                        System.out.println("2 - Não");
//                        int i = Validacao.validarInt();
//                        if (i == 1) {
//                            locadorController.deletarPerfil(locadorLogado.getId());
//                            System.out.println("Perfil deletado");
//                        }
//                        menuAreaLogada = 0;
//                        break;
//                }
//            } while (menuAreaLogada != 0);
        }
        if (area == 2){
            //TODO: Chamar menur inicial do LocadorMenu
            System.err.println("Stop App");
            System.err.println("Menu Em Construção");

//            do {
//                System.out.println("Bem-vindo a área logada");
//                System.out.println("O que você deseja fazer");
//                System.out.println("1 - Imprimir Perfil");
//                System.out.println("2 - Atualizar Perfil");
//                System.out.println("3 - Entrar no menu de terrenos");
//                System.out.println("4 - Deletar Perfil");
//                System.out.println("0 - Sair");
//                menuAreaLogada = Integer.parseInt(scanner.nextLine());
//                switch (menuAreaLogada){
//                    case 1:
//                        locatarioService.imprimirPerfil(locatarioLogado.getId());
//                        break;
//                    case 2:
//                        System.out.println("Vamos atualizar seu perfil:");
//                        String nomeUsuarioAtualizar = Validacao.validarString("Usuário novo:");
//                        String emailAtualizar = Validacao.validarString("E-mail novo:");
//                        String nomeAtualizar = Validacao.validarString("Nome novo:");
//                        String dataAtualizar = Validacao.validarString("Data de nascimento:");
//                        locadorController.atualizarPerfil(
//                                new Locador(
//                                        locadorLogado.getId(),
//                                        nomeUsuarioAtualizar,
//                                        emailAtualizar,
//                                        nomeAtualizar,
//                                        dataAtualizar));
//                        break;
//                    case 3:
//                        int menuTerrenos;
//                        do {
//                            System.out.println("Bem-vindo ao menu dos terrenos do locador");
//                            System.out.println("O que você deseja fazer!");
//                            System.out.println("1 - Cadastrar terreno");
//                            System.out.println("2 - Atualizar dados dos terrenos");
//                            System.out.println("3 - Deletar terreno");
//                            System.out.println("0 - Sair");
//                            menuTerrenos = Integer.parseInt(scanner.nextLine());
//
//                            //TODO: Precisa Continuar
//                        } while (menuTerrenos == 0);
//                    case 4:
//                        System.out.println("Tem certeza que seja deletar seu perfil?");
//                        System.out.println("1 - Sim");
//                        System.out.println("2 - Não");
//                        int i = Validacao.validarInt();
//                        if (i == 1) {
//                            locadorController.deletarPerfil(locadorLogado.getId());
//                            System.out.println("Perfil deletado");
//                        }
//                        menuAreaLogada = 0;
//                        break;
//                }
//            } while (menuAreaLogada != 0);
        }
    }

    private static void menuCadastro(){
        System.out.println("Bem vindo a área de cadastro!");
        System.out.println("""
                            1 - Quero arrentar de alguém
                            2 - Quero arrentar para alguém
                            0 - Digite zero para retornar a etapa anterior.
                            """);

        int locadorOuLocatario = Validacao.validarInt();

        switch (locadorOuLocatario){
            case 1:
                System.out.println("Vamos precisar de alguns dados para seu cadastro");
                System.out.println(
                        locadorController.cadastrar(
                                Validacao.validarString("Nome de usuário: "),
                                Validacao.validarString("E-mail: "),
                                Validacao.validarString("Senha: "),
                                Validacao.validarString("Nome completo: "),
                                Validacao.validarString("Data de nascimento: ")));
                cadastro = 1;
                break;
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
                menuInicial();
                break;
            default:
                System.out.println("Valor digitado inválido, vamos recomeçar");
    }
}

    private static void menuLogin() {
        boolean retorno;

        escolheAreaDeLogin();

        if (area == 0) menuInicial();

        do {

            String nome = Validacao.validarString("Digite seu nome de usuário:");
            String senha = Validacao.validarString("Digite sua senha:");

            retorno = conferenciaDeUsuario.conferencia(area, nome, senha);

            if (!retorno) {
                System.err.println("Dados incorretos, tente novamente");
                int opcao;
                do {
                    System.out.println("Escolha uma das opções abaixo:");
                    System.out.println("1 - Tentar novamente");
                    System.out.println("0 - Voltar ao menu anterior");
                    opcao = Validacao.validarInt();
                    if (opcao == 0) escolheAreaDeLogin();
                } while (opcao < 0 || opcao > 1);

            } else {
                if (area == 1) {
                    loginController.loginLocador(area, nome, senha);
                    System.out.println("Logado!");
                }
                if (area == 2) {
                    loginController.loginLocatario(area, nome, senha);
                    System.out.println("Logado!");
                }
            }
        } while (!retorno);
    }

    private static void escolheAreaDeLogin() {
        do {
            System.out.println("Área de Login");
            System.out.println("Selecione uma opção: ");
            System.out.println("1 - Locador");
            System.out.println("2 - Locatario");
            System.out.println("0 - Voltar ao menu anterior");
            area = Validacao.validarInt();
            if (area < 0 || area > 2) System.out.println("Opção inválida. Por favor, insira um valor válido.");
        } while (area < 0 || area > 2);
    }


}


