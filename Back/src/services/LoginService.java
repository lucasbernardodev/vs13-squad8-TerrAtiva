package services;

import interfaces.GerenciamentoSessao;

import java.util.Scanner;

public class LoginService implements GerenciamentoSessao {

    public final void menu(){
        Scanner sc = new Scanner(System.in);
        int opcao = 0;
        do {
            System.out.println("Selecione uma opção para continuar: ");
            System.out.println("1 - Cadastre-se");
            System.out.println("2 - Login");
            System.out.println("3 - Alterar senha");
            System.out.println("0 - Sair");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1:
                    cadastrarUsuario();
                    break;
                case 2:
                    logarUsuario();
                    break;
                case 3:
                    alterarSenha();
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

    }

    @Override
    public void logarUsuario() {

    }

    @Override
    public void alterarSenha() {

    }
}
