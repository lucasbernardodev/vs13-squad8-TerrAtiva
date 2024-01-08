package app.menus;

import controllers.FeedController;
import controllers.LocadorController;
import controllers.LoginController;
import models.Locador;
import services.LocadorService;
import util.Validacao;

import java.util.Scanner;

import static app.menus.FeedMenu.feedMenu;

public class LocadorMenu {
        public static Locador locadorLogado = new Locador("", "","","","");
        private static final Scanner scanner = new Scanner(System.in);
        private static LocadorController locadorController = new LocadorController();
        private static LocadorService locadorService = new LocadorService();

        public static void menuInicial() {
            int menuAreaLogada;
            do {
                System.out.println("Bem-vindo a área logada");
                System.out.println("O que você deseja fazer");
                System.out.println("1 - Imprimir Perfil");
                System.out.println("2 - Atualizar Perfil");
                System.out.println("3 - Acessar meus terrenos");
                System.out.println("4 - Deletar perfil");
                System.out.println("5 - Acessar Feed");
                System.out.println("0 - Sair");

                menuAreaLogada = Validacao.validarInt();

                switch (menuAreaLogada) {
                    case 1:
                        buscarUsuario();
                        menuAreaLogada = -1;
                        break;
                    case 2:
                        menuAtualizarPerfil();
                        menuAreaLogada = -1;
                        break;
                    case 3:
                        menuTerreno(LoginController.getLocadorLogado());
                        break;
                    case 4:
                        menuDeletar();
                        break;
                    case 5:
                        feedMenu();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opção inválida");
                        break;
                }
            } while (menuAreaLogada < 0 || menuAreaLogada > 5);
        }

        private static void buscarUsuario(){
            String usuario = locadorService.imprimirPerfil(LoginController.getLocadorLogado().getId());
            System.out.println(usuario);
            menuInicial();
        }
        private static void menuAtualizarPerfil(){
            System.out.println("Vamos atualizar seu perfil:");
            String nomeUsuarioAtualizar = Validacao.validarString("Usuário novo:");
            String emailAtualizar = Validacao.validarString("E-mail novo:");
            String nomeAtualizar = Validacao.validarString("Nome novo:");
            String dataAtualizar = Validacao.validarString("Data de nascimento:");
            String atualizandoPerfil = locadorController.atualizarPerfil(
                    new Locador(
                            LoginController.getLocadorLogado().getId(),
                            nomeUsuarioAtualizar,
                            emailAtualizar,
                            LoginController.getLocadorLogado().getSenha(),
                            nomeAtualizar,
                            dataAtualizar)
            );
            System.out.println(atualizandoPerfil);
        }
        private static void menuTerreno(Locador usuarioLogado){
            int menuTerrenos;
            do {
                System.out.println("Bem-vindo ao menu dos terrenos do locador");
                System.out.println("O que você deseja fazer!");
                System.out.println("1 - Listar meus terrenos");
                System.out.println("2 - Arrendar Terrenos");
                System.out.println("3 - Cancelar Contratos");
                System.out.println("0 - Voltar ao menu Anterior");
                System.out.println("Digite a opção desejada: ");
                menuTerrenos = Integer.parseInt(scanner.nextLine());

                switch (menuTerrenos) {
                    case 1:
                        System.out.println(
                                locadorController.resgatarTerrenosArrendados(
                                        LoginController.getLocadorLogado()));
                        break;
                    case 2:
                        System.out.println(FeedController.mostrarTerrenosDisponveis());
                        System.out.println("Deseja arrendar um novo Terreno: ");
                        System.out.println("1 | SIM");
                        System.out.println("Outro | NÃO");
                        System.out.println("Digite: ");
                        int arrendarTerreno = Validacao.validarInt();

                        switch (arrendarTerreno) {
                            case 1:
                                System.out.println("Digite o ID do terreno: ");
                                int terrenoID = Validacao.validarInt();
                                System.out.println(locadorController.arrendarTerreno(terrenoID, LoginController.getLocadorLogado()));
                                break;
                            case 2:
                                break;
                        }
                        break;
                    case 3:
                        System.out.println(locadorController.resgatarTerrenosArrendados(LoginController.getLocadorLogado()));

                        System.out.println("Digite o número do contrato a ser cancelado: ");
                        int numeroContrato = Validacao.validarInt();
                        String resultadoCancelamento = locadorController.cancelarcontrato(numeroContrato, LoginController.getLocadorLogado());
                        System.out.println(resultadoCancelamento);
                        break;
                    case 0:
                        menuInicial();
                        break;
                }
            } while (menuTerrenos != 0);
        }
        private static void menuDeletar (){
            System.out.println("Tem certeza que deseja deletar seu perfil?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");

            int i = Validacao.validarInt();

            if (i == 1) {
                String deletar = locadorController.deletarPerfil(locadorLogado.getId());
                System.out.println(deletar);
                SessaoMenu.menuInicial();
            } else {
                menuInicial();
            }
        }
}
