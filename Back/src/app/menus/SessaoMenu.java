package app.menus;

import controllers.LocadorController;
import controllers.LocatarioController;
import controllers.TerrenoController;
import models.Locador;
import models.Locatario;
import services.LocadorService;
import services.LocatarioService;
import services.TerrenoService;
import util.ConferenciaDeUsuario;
import util.RetornaId;
import util.Validacao;

import java.util.Scanner;

public class SessaoMenu {
    static Scanner scanner = new Scanner(System.in);
    static LocadorController locadorController = new LocadorController();
    static LocatarioController locatarioController = new LocatarioController();
    static TerrenoController terrenoController = new TerrenoController();
    static LocadorService locadorService = new LocadorService();
    static LocatarioService locatarioService = new LocatarioService();
    static TerrenoService terrenoService = new TerrenoService();
    static ConferenciaDeUsuario conferenciaDeUsuario = new ConferenciaDeUsuario();
    static RetornaId retornaId = new RetornaId();
    static int cadastro;

    public static void menuInicial(){

        do{
            System.out.println("Selecione uma opção: ");
            System.out.println("1 - Login");
            System.out.println("2 - Cadastrar-se");

            cadastro = Validacao.validarInt();

            switch (cadastro){
                case 1:
                    break;
                case 2:
                    menuCadastro();
            }

        } while (cadastro < 1 || cadastro > 2);

        boolean retorno;
        int area;
        Locador locadorLogado = new Locador(0, "","","","");
        Locatario locatarioLogado = new Locatario(0, "","","","");
        do {
            System.out.println("Área de Login");
            System.out.println("Selecione uma opção: ");
            System.out.println("1 - Locador");
            System.out.println("2 - Locatario");
            area = Validacao.validarInt();
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
                    locadorLogado = locadorService.resgatarLocador(
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


        int menuAreaLogada;
        if (area == 1){
            do {
                System.out.println("Bem-vindo a área logada");
                System.out.println("O que você deseja fazer");
                System.out.println("1 - Imprimir Perfil");
                System.out.println("2 - Atualizar Perfil");
                System.out.println("3 - Entrar no menu de terrenos");
                System.out.println("4 - Deletar Perfil");
                System.out.println("0 - Sair");
                menuAreaLogada = Integer.parseInt(scanner.nextLine());
                switch (menuAreaLogada){
                    case 1:
                        locadorService.imprimirPerfil(locadorLogado.getId());
                        break;
                    case 2:
                        System.out.println("Vamos atualizar seu perfil:");
                        String nomeUsuarioAtualizar = Validacao.validarString("Usuário novo:");
                        String emailAtualizar = Validacao.validarString("E-mail novo:");
                        String nomeAtualizar = Validacao.validarString("Nome novo:");
                        String dataAtualizar = Validacao.validarString("Data de nascimento:");
                        locadorController.atualizarPerfil(
                                new Locador(
                                        locadorLogado.getId(),
                                        nomeUsuarioAtualizar,
                                        emailAtualizar,
                                        nomeAtualizar,
                                        dataAtualizar));
                        break;
                    case 3:
                        int menuTerrenos;
                        do {
                            System.out.println("Bem-vindo ao menu dos terrenos do locador");
                            System.out.println("O que você deseja fazer!");
                            System.out.println("1 - Cadastrar terreno");
                            System.out.println("2 - Atualizar dados dos terrenos");
                            System.out.println("3 - Deletar terreno");
                            System.out.println("0 - Sair");
                            menuTerrenos = Integer.parseInt(scanner.nextLine());

                            //TODO: Precisa Continuar
                        } while (menuTerrenos == 0);
                    case 4:
                        System.out.println("Tem certeza que seja deletar seu perfil?");
                        System.out.println("1 - Sim");
                        System.out.println("2 - Não");
                        int i = Validacao.validarInt();
                        if (i == 1) {
                            locadorController.deletarPerfil(locadorLogado.getId());
                            System.out.println("Perfil deletado");
                        }
                        menuAreaLogada = 0;
                        break;
                }
            } while (menuAreaLogada != 0);
        }
        if (area == 2){
            do {
                System.out.println("Bem-vindo a área logada");
                System.out.println("O que você deseja fazer");
                System.out.println("1 - Imprimir Perfil");
                System.out.println("2 - Atualizar Perfil");
                System.out.println("3 - Entrar no menu de terrenos");
                System.out.println("4 - Deletar Perfil");
                System.out.println("0 - Sair");
                menuAreaLogada = Integer.parseInt(scanner.nextLine());
                switch (menuAreaLogada){
                    case 1:
                        locatarioService.imprimirPerfil(locatarioLogado.getId());
                        break;
                    case 2:
                        System.out.println("Vamos atualizar seu perfil:");
                        String nomeUsuarioAtualizar = Validacao.validarString("Usuário novo:");
                        String emailAtualizar = Validacao.validarString("E-mail novo:");
                        String nomeAtualizar = Validacao.validarString("Nome novo:");
                        String dataAtualizar = Validacao.validarString("Data de nascimento:");
                        locadorController.atualizarPerfil(
                                new Locador(
                                        locadorLogado.getId(),
                                        nomeUsuarioAtualizar,
                                        emailAtualizar,
                                        nomeAtualizar,
                                        dataAtualizar));
                        break;
                    case 3:
                        int menuTerrenos;
                        do {
                            System.out.println("Bem-vindo ao menu dos terrenos do locador");
                            System.out.println("O que você deseja fazer!");
                            System.out.println("1 - Cadastrar terreno");
                            System.out.println("2 - Atualizar dados dos terrenos");
                            System.out.println("3 - Deletar terreno");
                            System.out.println("0 - Sair");
                            menuTerrenos = Integer.parseInt(scanner.nextLine());

                            //TODO: Precisa Continuar
                        } while (menuTerrenos == 0);
                    case 4:
                        System.out.println("Tem certeza que seja deletar seu perfil?");
                        System.out.println("1 - Sim");
                        System.out.println("2 - Não");
                        int i = Validacao.validarInt();
                        if (i == 1) {
                            locadorController.deletarPerfil(locadorLogado.getId());
                            System.out.println("Perfil deletado");
                        }
                        menuAreaLogada = 0;
                        break;
                }
            } while (menuAreaLogada != 0);
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
                break;
            default:
                System.out.println("Valor digitado inválido, vamos recomeçar");
    }
}

}


