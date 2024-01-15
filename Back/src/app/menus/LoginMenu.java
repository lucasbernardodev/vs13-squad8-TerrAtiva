package app.menus;

import controllers.LoginController;
import controllers.UsuarioController;
import util.Logo;
import util.Validacao;
import util.formatter.ShowMenu;

import java.time.LocalDate;

public class LoginMenu {
    private static final LoginController loginController = new LoginController();
    private static final UsuarioController usuarioController = new UsuarioController();
    private static final UsuarioMenu usuarioMenu = new UsuarioMenu();

    public void inicio() {
        int opcaoSelecionada;

        do{
            System.out.println(Logo.logo);
            ShowMenu.header("Bem-vindo ao Terra Ativa, o seu aplicativo de arrendamento de terras!", 70);
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
                    cadastro();
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

    private void cadastro() {
        boolean confirm = true;


        ShowMenu.header("CADASTRO", 70);
        String nome = Validacao.validarString("Nome: ");
        String sobrenome = Validacao.validarString("Sobrenome: ");
        String email = Validacao.validarString("Email: ");
        String senha = Validacao.validarString("Senha: ");
        String cpf = Validacao.validarString("CPF: ");
        LocalDate dataDeNascimento = Validacao.validarData("Data De Nascimento: ");
        String sexo = Validacao.validarString("Sexo: ");
        String celular = Validacao.validarString("Celular: ");
        String telefoneFixo = Validacao.validarString("Telefone: ");

        while (confirm) {
            System.out.println("Confirma os dados?");
            System.out.println("S | Sim");
            System.out.println("N | Não");
            String choice = Validacao.validarString("Digite: ").toLowerCase();
            System.out.println();

            switch (choice) {
                case "s":
                    System.out.println(usuarioController.cadastrarUsuario(
                            nome,
                            sobrenome,
                            email,
                            senha,
                            cpf,
                            dataDeNascimento,
                            sexo,
                            "S",
                            celular,
                            telefoneFixo
                    ));
                    confirm = false;
                    break;
                case "n":
                    System.out.println("OPERAÇÃO CANCELADA");
                    confirm = false;
                    break;

                default:
                    System.out.println("Digite um valor válido!");
                    break;
            }

            inicio();
        }
    }
}
