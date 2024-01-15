package app.menus;

import controllers.FeedUsuarioController;
import controllers.UsuarioController;
import models.Usuario;
import util.Validacao;
import util.formatter.ShowMenu;

public class UsuarioMenu {
    UsuarioController usuarioController = new UsuarioController();
    FeedUsuarioController feedUsuario = new FeedUsuarioController();

    LoginMenu loginMenu = new LoginMenu();

    public void inicio() {
        int opcaoSelecionada;
        do{
            ShowMenu.header("Bem-vindo de volta " + Usuario.instancia.getNome() + "!", 70);
            System.out.println("1 - Acessar Perfil");
            System.out.println("2 - Acessar Terrenos");
            System.out.println("3 - Acessar Feed");
            System.out.println("4 - Sair");
            opcaoSelecionada = Validacao.validarInt("Digite: ");
            System.out.println();

            switch (opcaoSelecionada){
                case 1:
                    menuPerfil();
                    break;
                case 2:
                    menuTerrenos();
                    break;
                case 3:
                    FeedMenu.feedMenu();
                    break;
                case 4:
                    Usuario.logout();
                    loginMenu.inicio();
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, insira um valor válido.");
                    break;
            }

        } while (opcaoSelecionada > 1 || opcaoSelecionada < 3);

    }

    private void menuTerrenos() {
        boolean running = true;

        while(running) {
            ShowMenu.header("TERRENOS", 70);
            System.out.println("1 | Mostrar Todos os Meus Terrenos");
            System.out.println("2 | Mostrar Terrenos DISPONÍVEIS");
            System.out.println("3 | Mostrar Terrenos ARRENDADOS");
            System.out.println("4 | Consultar um Terreno por Título");
            System.out.println("0 | Voltar");

            int terrenoMenu = Validacao.validarInt("Digite: ");
            System.out.println();

            switch (terrenoMenu) {
                case 1:
                    ShowMenu.header("MEUS TERRENOS", 70);
                    System.out.println(feedUsuario.mostrarTerrenosDisponveis(Usuario.instancia.getUsuarioId()));
                    break;
                case 2:
                    ShowMenu.header("MEUS TERRENOS DISPONÍVEIS", 70);
                    System.out.println(feedUsuario.mostrarTerrenosDisponveis(Usuario.instancia.getUsuarioId()));
                    break;
                case 3:
                    ShowMenu.header("MEUS TERRENOS ARRENDADOS", 70);
                    System.out.println(feedUsuario.mostrarTerrenosArrendados(Usuario.instancia.getUsuarioId()));
                    break;
                case 4:
                    ShowMenu.header("CONSULTA DE TERRENO", 70);
                    String tituloConsulta = Validacao.validarString("Título para Busca: ");
                    System.out.println(feedUsuario.mostrarTerrenosPorTitulo(tituloConsulta, Usuario.instancia.getUsuarioId()));
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Digite uma Opção Válida!");
                    break;
            }
        }
    }

    private void menuPerfil() {
        int opcaoSelecionada;

        do{
            ShowMenu.header("Menu de Perfil", 70);
            System.out.println("1 - Exibir Dados Do Usuario");
            System.out.println("2 - Editar Perfil");
            System.out.println("3 - Deletar Perfil");
            System.out.println("4 - Voltar ao menu anterior");
            opcaoSelecionada = Validacao.validarInt("Digite: ");
            System.out.println();


            switch (opcaoSelecionada){
                case 1:
                    System.out.println(Usuario.getInstancia());
                    menuPerfil();
                    break;
                case 2:
                    menuEditarPerfil();
                    break;
                case 3:
                    menuDeletarPerfil();
                    break;
                case 4:
                    inicio();
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, insira um valor válido.");
            }

        } while (opcaoSelecionada > 1 || opcaoSelecionada < 4);
    }

    private void menuEditarPerfil() {
        boolean confirm = true;

        ShowMenu.header("EDITAR PERFIL", 70);
        String nome = Validacao.validarString("Nome: ");
        String sobrenome = Validacao.validarString("Sobrenome: ");
        String email = Validacao.validarString("Email: ");
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
                    System.out.println(usuarioController.atualizarUsuario(
                            Usuario.instancia.getUsuarioId(),
                            nome,
                            sobrenome,
                            email,
                            Usuario.instancia.getCpf(),
                            Usuario.instancia.getDataNascimento(),
                            Usuario.instancia.getSexo(),
                            Usuario.instancia.getAtivo(),
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

        }
    }

    private void menuDeletarPerfil() {
        boolean confirm = true;

        ShowMenu.header("DELETAR PERFIL", 70);

        while (confirm) {
            System.out.println("Deseja comcluir a ação?");
            System.out.println("S | Sim");
            System.out.println("N | Não");
            String choice = Validacao.validarString("Digite: ").toLowerCase();
            System.out.println();

            switch (choice) {
                case "s":
                    System.out.println(usuarioController.deletarDados(Usuario.instancia.getUsuarioId()));
                    Usuario.logout();
                    loginMenu.inicio();
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

        }
    }
}
