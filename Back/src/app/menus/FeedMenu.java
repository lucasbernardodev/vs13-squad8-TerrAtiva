package app.menus;

import app.enums.TipoUsuario;
import controllers.FeedController;
import util.Validacao;

public class FeedMenu {

    public static void feedMenu() {
        FeedController feed = new FeedController();
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
                    System.out.println(feed.mostrarTerrenosDisponveis());
                    break;
                case 2:
                    feedFiltrado();
                    break;
                case 3:
//                    confereUsuario(); TODO: ARRUMAR MÉTODO
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
        FeedController feed = new FeedController();
        int opcao;
        do {
            System.out.println("### Feed ###");
            System.out.println("Selecione um filtro: ");
            System.out.println("1 - Estado");
            System.out.println("2 - Valor");
            System.out.println("3 - Título");
            System.out.println("4 - Tamanho");
            System.out.println("5 - Limpar filtros");
            System.out.println("0 - Voltar");
            opcao = Validacao.validarInt();

            switch (opcao) {
                case 1:
                    System.out.println(feed.buscarEstados());
                    System.out.println(feed.mostrarTerrenosPorLocal(
                            Validacao.validarString("Digite o ID do estado: ")));
                    break;
                case 2:
                    System.out.println(feed.mostrarTerrenosPorPreco(
                            Validacao.validarString("Digite o valor aproximado: ")));
                    break;
                case 3:
                    System.out.println(feed.mostrarTerrenosPorCaracteristica(
                            Validacao.validarString("Digite uma ou mais palavra chave: ")));
                    break;
                case 4:
                    System.out.println(feed.mostrarTerrenosPorTamanho(
                            Validacao.validarString("Digite o tamanho: ")));
                    break;
                case 5:
                    feed.limparFiltros();
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }


        } while (opcao != 0);
    }

}
