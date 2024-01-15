package app.menus;

import controllers.LoginController;
import util.Validacao;
import util.formatter.ShowMenu;

public class LoginMenu {
    private final LoginController loginController = new LoginController();
    private UsuarioMenu usuarioMenu = new UsuarioMenu();

    public void inicio() {
        int opcaoSelecionada;

        do{
            ShowMenu.header("Bem-vindo ao Terra Viva, o seu aplicativo de arrendamento de terras!", 70);
            System.out.println("Selecione uma opção: ");
            System.out.println("1 - Login");
            System.out.println("2 - Cadastrar-se");
            opcaoSelecionada = Validacao.validarInt("Digite: ");
            System.out.println();

            switch (opcaoSelecionada){
                case 1:
                    login();
                    break;
                case 2:
//                    TODO: Pendente implementação
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, insira um valor válido.");
            }

        } while (opcaoSelecionada < 1 || opcaoSelecionada > 2);

    }

    private void login() {
        boolean retorno;

        do {
            ShowMenu.header("LOGIN", 70);
            String email= Validacao.validarString("Digite seu email: ");
            String senha = Validacao.validarString("Digite sua senha: ");

            retorno = loginController.loginUsuario(email, senha);

        } while (!retorno);

        usuarioMenu.inicio();
    }
}
