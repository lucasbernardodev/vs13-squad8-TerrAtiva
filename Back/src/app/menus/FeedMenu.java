package app.menus;

import controllers.FeedController;
import database.BancoDeDados;
import database.DadosMocados;
import util.Validacao;

public class FeedMenu {

    public static void main(String[] args) {
        feedMenu();
    }

    private static void feedMenu() {
        int opcao;
        do {
            System.out.println("### Feed ###");
            System.out.println("Seleciona uma opção: ");
            System.out.println("1 - Listar todos anúncios");
            System.out.println("2 - Selecionar filtros");
            if (confereUsuario()) System.out.println("3 - Arrendar terreno");
            opcao = Validacao.validarInt();

            switch (opcao) {
                case 1:
                    FeedController.mostrarTerrenosDisponveis();
                    break;
                case 2:
                    feedFiltrado();
                    break;
                case 3:
                    if (confereUsuario()) menuArrendamento();
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }


        } while (opcao != 0);
    }

    private static void feedFiltrado() {
        int opcao;
        do {
            System.out.println("### Feed ###");
            System.out.println("Selecione um filtro: ");
            System.out.println("1 - Localização");
            System.out.println("2 - Valor");
            System.out.println("3 - Título");
            System.out.println("4 - Tamanho");
            System.out.println("0 - Voltar");
            opcao = Validacao.validarInt();

            switch (opcao) {
                case 1:
                    FeedController.mostrarTerrenosPorLocalizacao(
                            Validacao.validarString("Digite o local:"));
                    break;
                case 2:
                    FeedController.mostrarTerrenosPorLocalizacao(
                            Validacao.validarString("Digite o valor máximo:"));
                    break;
                case 3:
                    FeedController.mostrarTerrenosPorLocalizacao(
                            Validacao.validarString("Digite parte do título:"));
                    break;
                case 4:
                    FeedController.mostrarTerrenosPorLocalizacao(
                            Validacao.validarString("Digite o tamanho:"));
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }


        } while (opcao != 0);
    }
    private static void menuArrendamento() {

    }


    private static boolean confereUsuario() { //PRA TESTES, SUBSTITUIR PELO DE BAIXO
        return true;
    }
//    private static boolean confereUsuario() {
//        return SessaoMenu.locadorLogado.getId() != 0;
//    }

}
