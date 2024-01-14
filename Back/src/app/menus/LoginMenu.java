package app.menus;

import util.Validacao;

public class LoginMenu {

    public static void inicio() {

        int opcaoSelecionada;

        do{
            System.out.println("Bem-vindo ao Terra Viva, o seu aplicativo de arrendamento de terras!");
            System.out.println("Selecione uma opção: ");
            System.out.println("1 - Login");
            System.out.println("2 - Cadastrar-se");

            opcaoSelecionada = Validacao.validarInt();

            switch (opcaoSelecionada){
                case 1:
                    login();
                    break;
                case 2:
                    menuCadastro();
                    System.out.println("|-------------------------------------------|");
                    System.out.println("Bem vindo de volta! Selecione uma opção: ");
                    login();
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, insira um valor válido.");
            }

        } while (opcaoSelecionada < 1 || opcaoSelecionada > 2);

        if (area == 1){
            System.out.println();
            LocadorMenu.menuInicial();
        }
        if (area == 2){
            LocatarioMenu.menuInicial();

        }
    }

    private static void login() {
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
}
