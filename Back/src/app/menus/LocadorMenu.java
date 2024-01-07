package app.menus;

import controllers.FeedController;
import controllers.LocadorController;
import models.Locador;
import services.LocadorService;
import util.Validacao;

import java.util.Scanner;

import static app.menus.SessaoMenu.locadorLogado;

public class LocadorMenu {
        private static final Scanner scanner = new Scanner(System.in);
        private  LocadorController locadorController = new LocadorController();
        private  LocadorService locadorService = new LocadorService();
        private static int cadastro;
        private static int area;

        public static void menuInicial(Locador usuarioLogado) {
            int menuAreaLogada;
            do {
                System.out.println("Bem-vindo a área logada");
                System.out.println("O que você deseja fazer");
                System.out.println("1 - Imprimir Perfil");
                System.out.println("2 - Atualizar Perfil");
                System.out.println("3 - Acessar meus terreno");
                System.out.println("4 - Acessar feed");
                System.out.println("5 - Deletar Perfil");
                System.out.println("0 - Sair");

                menuAreaLogada = Validacao.validarInt();

                switch (menuAreaLogada) {
                    case 1:
                        buscarUsuario();
                        menuAreaLogada = -1;
                        break;
                    case 2:
                        menuAtualizarPerfil(usuarioLogado);
                        menuAreaLogada = -1;
                        break;
                    case 3:
                        menuTerreno();
                        //menuAreaLogada = -1;
                        break;
                    case 4:
                        menuDeletar();
                        break;
                }
            } while (menuAreaLogada < 0 || menuAreaLogada > 5);
        }

        private void buscarUsuario(){
            String usuario = locadorService.imprimirPerfil(locadorLogado.getId());
            System.out.println(usuario);
        }
        private void menuAtualizarPerfil(Locador usuarioLogado){
            System.out.println("Vamos atualizar seu perfil:");
            String nomeUsuarioAtualizar = Validacao.validarString("Usuário novo:");
            String emailAtualizar = Validacao.validarString("E-mail novo:");
            String nomeAtualizar = Validacao.validarString("Nome novo:");
            String dataAtualizar = Validacao.validarString("Data de nascimento:");
            String atualizandoPerfil = locadorController.atualizarPerfil(
                    new Locador(
                            usuarioLogado.getId(),
                            nomeUsuarioAtualizar,
                            emailAtualizar,
                            usuarioLogado.getSenha(),
                            nomeAtualizar,
                            dataAtualizar)
            );
            System.out.println(atualizandoPerfil);
        }
        private void menuTerreno(Locador usuarioLogado){
            int menuTerrenos;
            do {
                System.out.println("Bem-vindo ao menu dos terrenos do locador");
                System.out.println("O que você deseja fazer!");
                System.out.println("1 - Vizualizar terrenos");
                System.out.println("2 - Cancelar Contratos");
                System.out.println("0 - Voltar ao menu Anterior");
                System.out.println("Digite a opção desejada: ");
                menuTerrenos = Integer.parseInt(scanner.nextLine());

                switch (menuTerrenos) {
                    case 1:
                        System.out.println(FeedController.mostrarTerrenosDisponveis());
                        break;
                    case 2:
                        System.out.println(locadorController.resgatarTerrenosArrendados(usuarioLogado));

                        System.out.println("Digite o Contrato a ser cancelado: ");
                        System.out.println(locadorController.cancelarcontrato());



                //TODO: Precisa Continuar
            } while (menuTerrenos == 0);
        }
        private static void menuDeletar(){
            System.out.println("Tem certeza que deseja deletar seu perfil?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");
            int i = Validacao.validarInt();
            if (i == 1) {
                locadorController.deletarPerfil(locadorLogado.getId());
                System.out.println("Perfil deletado");
            }
            int menuAreaLogada = 0;
        }


    }
