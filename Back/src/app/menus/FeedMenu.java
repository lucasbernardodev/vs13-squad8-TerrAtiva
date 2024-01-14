/*
package app.menus;

import app.enums.TipoUsuario;
import controllers.FeedController;
import controllers.LoginController;
import util.Validacao;

public class FeedMenu {

    public static void feedMenu() {
        int opcao;
        do {
            System.out.println("### FEED ###");
            System.out.println("Seleciona uma opção: ");
            System.out.println("1 - Listar todos anúncios");
            System.out.println("2 - Selecionar filtros");
            System.out.println("3 - Meu perfil");
            System.out.println("0 - Voltar");
            opcao = Validacao.validarInt();

            switch (opcao) {
                case 1:
                    System.out.println(FeedController.mostrarTerrenosDisponveis());
                    break;
                case 2:
                    feedFiltrado();
                    break;
                case 3:
                    confereUsuario();
                    break;
                case 0:
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
                    System.out.println(FeedController.mostrarTerrenosPorLocalizacao(
                            Validacao.validarString("Digite o local:")));
                    break;
                case 2:
                    System.out.println("Digite o valor máximo:");
                    System.out.println(FeedController.mostrarTerrenosPorPrecoMenor(
                            Validacao.validarInt()));
                    break;
                case 3:
                    System.out.println(FeedController.mostrarTerrenosPorTitulo(
                            Validacao.validarString("Digite parte do título:")));
                    break;
                case 4:
                    System.out.println(FeedController.mostrarTerrenosPorTamanho(
                            Validacao.validarString("Digite o tamanho:")));
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }


        } while (opcao != 0);
    }
    private static void menuArrendamento() {

    }

    private static void confereUsuario() {
        if (LoginController.getTipoUsuario() == TipoUsuario.LOCADOR) {
            LocadorMenu.menuInicial();
        }
        else
        {
            LocatarioMenu.menuInicial();
        }

    }

}
*/
