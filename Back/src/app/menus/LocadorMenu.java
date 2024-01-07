package app.menus;

import controllers.LocadorController;
import models.Locador;
import services.LocadorService;
import util.Validacao;

import java.util.Scanner;

import static app.menus.SessaoMenu.locadorLogado;

public class LocadorMenu {
        private static final Scanner scanner = new Scanner(System.in);
        private static final LocadorController locadorController = new LocadorController();
        private static final LocadorService locadorService = new LocadorService();
        private static int cadastro;
        private static int area;

        public static void menuInicial() {
            int menuAreaLogada;
            do {
                System.out.println("Bem-vindo a área logada TESTE");
                System.out.println("O que você deseja fazer");
                System.out.println("1 - Imprimir Perfil");
                System.out.println("2 - Atualizar Perfil");
                System.out.println("3 - Entrar no menu de terrenos");
                System.out.println("4 - Deletar Perfil");
                System.out.println("0 - Sair");

                menuAreaLogada = Integer.parseInt(scanner.nextLine());

                switch (menuAreaLogada) {
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
                        break;
                    case 4:
                        System.out.println("Tem certeza que deseja deletar seu perfil?");
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
