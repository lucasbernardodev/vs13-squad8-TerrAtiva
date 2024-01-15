package app.menus;

import models.Usuario;
import util.Logo;
import util.Validacao;

public class UsuarioMenu {
    public void inicio() {
        int opcaoSelecionada;
        do{
            System.out.println(Logo.logo);
            System.out.println("Bem-vindo de volta " + Usuario.instancia.getNome() + "!");
            System.out.println("Selecione uma opção: ");
            System.out.println("1 - Acessar Perfil");
            System.out.println("2 - Acessar Terrenos");
            System.out.println("3 - Acessar Feed");
            System.out.println("4 - Sair");

            opcaoSelecionada = Validacao.validarInt();

            switch (opcaoSelecionada){
                case 1:
                    menuPerfil();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, insira um valor válido.");
            }

        } while (opcaoSelecionada < 1 || opcaoSelecionada > 4);

    }

    private void menuPerfil() {
        int opcaoSelecionada;

        do{
            System.out.println("|-------------------------------|");
            System.out.println("Menu De Perfil");
            System.out.println("|-------------------------------|");
            System.out.println("Selecione uma opção: ");
            System.out.println("1 - Exibir Dados Do Usuario");
            System.out.println("2 - Editar Perfil");
            System.out.println("3 - Deletar Perfil");
            System.out.println("4 - Voltar ao menu anterior");

            opcaoSelecionada = Validacao.validarInt();

            switch (opcaoSelecionada){
                case 1:
                    System.out.println(Usuario.getInstancia());
                    menuPerfil();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    inicio();
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, insira um valor válido.");
            }

        } while (opcaoSelecionada < 1 || opcaoSelecionada > 4);
    }
}
