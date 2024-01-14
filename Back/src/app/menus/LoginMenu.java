package app.menus;

import controllers.LoginController;
import util.Validacao;

public class LoginMenu {
    private final LoginController loginController = new LoginController();

    public void inicio() {
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
//                    menuCadastro();
//                    System.out.println("|-------------------------------------------|");
//                    System.out.println("Bem vindo de volta! Selecione uma opção: ");
//                    login();
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, insira um valor válido.");
            }

        } while (opcaoSelecionada < 1 || opcaoSelecionada > 2);

//        if (area == 1){
//            System.out.println();
//            LocadorMenu.menuInicial();
//        }
//        if (area == 2){
//            LocatarioMenu.menuInicial();
//
//        }
    }

    private void login() {
        boolean retorno;

        do {

            String email= Validacao.validarString("Digite seu email:");
            String senha = Validacao.validarString("Digite sua senha:");

            retorno = loginController.loginUsuario(email, senha);

        } while (!retorno);
    }
}
