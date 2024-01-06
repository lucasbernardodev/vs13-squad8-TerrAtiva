package services;

import interfaces.GerenciamentoSessao;
import models.Locador;
import models.Locatario;

import java.util.Scanner;

public class LoginService implements GerenciamentoSessao {
    //TODO: adicionar verificações e tratamento de erros
    boolean logado = false;
    Locador locadorLogado;
    Locatario locatarioLogado;

    LocadorService locadorService = new LocadorService();
    LocatarioService locatarioService = new LocatarioService();


    Scanner sc = new Scanner(System.in);
    public final void menu(){
        Scanner sc = new Scanner(System.in);
        int opcao = 0;
        do {
            System.out.println("Selecione uma opção para continuar: ");
            System.out.println("1 - Cadastre-se");
            System.out.println("2 - Login");
            System.out.println("0 - Sair");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1:
                    cadastrarUsuario();
                    break;
                case 2:
                    if (logarUsuario()) {return;}
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

        @Override
    public void cadastrarUsuario() {
        System.out.println("Digite seu usuário: ");
        String usuario = sc.nextLine();
        System.out.println("Nome completo: ");
        String name = sc.nextLine();
        System.out.println("Digite seu email: ");
        String email = sc.nextLine();
        System.out.println("Senha: ");
        String senha = sc.nextLine();
        System.out.println("Data de nascimento: ");
        String nascimento = sc.nextLine();
        System.out.println("Tipo de usuário:");
        System.out.println("1 - Locador");
        System.out.println("2 - Locatário");
        int tipo = Integer.parseInt(sc.nextLine());
        if (tipo == 1) {
            locadorService.cadastrar(usuario, email, senha, name, nascimento);
            System.out.println("Cadastrado com sucesso!");
        } else if (tipo == 2) {
            locatarioService.cadastrar(usuario, email, senha, name, nascimento);
            System.out.println("Cadastrado com sucesso!");
        }
    }

    @Override
    public boolean logarUsuario() {

        int tipoUsuario;

        do {
            System.out.println("Digite seu email: ");
            String email = sc.nextLine();
            System.out.println("Digite sua senha: ");
            String senha = sc.nextLine();
            System.out.println("Tipo de usuário:");
            System.out.println("1 - Locador");
            System.out.println("2 - Locatário");
            System.out.println("0 - Voltar ao menu anterior");
            tipoUsuario = Integer.parseInt(sc.nextLine());
            switch (tipoUsuario) {
                case 1:
                    if (validarDados(email, senha, tipoUsuario)) {
                        this.logado = true;
                        this.locadorLogado = locadorService.resgatarLocadores(email);
                        System.out.println("Logado com sucesso!");
                        return true;
                    } else {
                        System.out.println("Dados incorretos.");
                    }
                    break;
                case 2:
                    if (validarDados(email, senha, tipoUsuario)) {
                        this.logado = true;
                        this.locatarioLogado = locatarioService.resgatarLocatarios(email);
                        System.out.println("Logado com sucesso!");
                        return true;
                    } else {
                        System.out.println("Dados incorretos.");
                    }
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (tipoUsuario < 0 || tipoUsuario > 2);
        return false;
    }

    public boolean validarDados(String email, String senha, int tipo) {
        if (tipo == 1) {
            if (locadorService.resgatarLocadores(email) != null) {
                Locador locadorVerificao = locadorService.resgatarLocadores(email);
                return (locadorVerificao.getEmail().equals(email) && locadorVerificao.getSenha().equals(senha));
            }
        } else if (tipo == 2) {
            if (locatarioService.resgatarLocatarios(email) != null) {
                Locatario locatarioVerificao = locatarioService.resgatarLocatarios(email);
                return (locatarioVerificao.getEmail().equals(email) && locatarioVerificao.getSenha().equals(senha));
            }
        }
        return false;
    }
}
