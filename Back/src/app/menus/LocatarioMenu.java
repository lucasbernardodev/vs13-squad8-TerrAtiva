package app.menus;
import controllers.FeedController;
import controllers.LocatarioController;
import controllers.LoginController;
import controllers.TerrenoController;
import infra.exceptions.EmptyDataException;
import models.Locatario;
import services.LocatarioService;
import util.Validacao;

import static app.menus.FeedMenu.feedMenu;

public class LocatarioMenu {

        public static Locatario locatarioLogado = new Locatario("", "", "", "", "");

        static LocatarioController locatarioController = new LocatarioController();
        static LocatarioService locatarioService = new LocatarioService();
        static TerrenoController terrenoController = new TerrenoController();


        public static void menuInicial(){
            int menuAreaLogada;
                do {
                    System.out.println("Bem-vindo a área logada");
                    System.out.println("O que você deseja fazer");
                    System.out.println("1 - Imprimir Perfil");
                    System.out.println("2 - Atualizar Perfil");
                    System.out.println("3 - Gerenciar terrenos");
                    System.out.println("4 - Deletar Perfil");
                    System.out.println("5 - Acessar Feed");
                    System.out.println("0 - Sair");

                    menuAreaLogada = Validacao.validarInt();
                    switch (menuAreaLogada) {
                        case 1:
                            imprimirUsuario();
                            menuAreaLogada = 0;
                            break;
                        case 2:
                            menuAtualizarPerfil();
                            menuAreaLogada = 0;
                            break;
                        case 3:
                            menuTerreno();
                            menuAreaLogada = 0;
                            break;
                        case 4:
                            menuDeletar();
                            menuAreaLogada = 0;
                        case 5:
                            feedMenu();
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("Opção inválida");
                            break;
                    }
                } while (menuAreaLogada != 0);

        }

        private static void imprimirUsuario (){
            String usuario = locatarioService.imprimirPerfil(LoginController.getLocatarioLogado().getId());
            System.out.println(usuario);
            menuInicial();
        }

        private static void menuAtualizarPerfil (){
            System.out.println("Vamos atualizar seu perfil:");
            String nomeUsuarioAtualizar = Validacao.validarString("Usuário novo:");
            String emailAtualizar = Validacao.validarString("E-mail novo:");
            String nomeAtualizar = Validacao.validarString("Nome novo:");
            String dataAtualizar = Validacao.validarString("Data de nascimento:");
            String atualizandoPerfil = locatarioController.atualizarPerfil(
                    new Locatario(
                            LoginController.getLocatarioLogado().getId(),
                            nomeUsuarioAtualizar,
                            emailAtualizar,
                            LoginController.getLocatarioLogado().getSenha(),
                            nomeAtualizar,
                            dataAtualizar)
            );
            System.out.println(atualizandoPerfil);
            LocatarioMenu.menuInicial();
        }

        private static void menuTerreno (){
            int menuTerrenos;
            do {
                System.out.println("Bem-vindo ao menu dos terrenos do locatário");
                System.out.println("O que você deseja fazer!");
                System.out.println("1 - Vizualizar terrenos arrendados");
                System.out.println("2 - Cancelar Contratos");
                System.out.println("3 - Criar anúncio de terrenos");
                System.out.println("4 - Deletar anúncio");
                System.out.println("5 - Listar meus terrenos");
                System.out.println("0 - Voltar ao menu Anterior");
                System.out.println("Digite a opção desejada: ");
                menuTerrenos = Validacao.validarInt();
                switch (menuTerrenos) {
                    case 1:
                        System.out.println(locatarioController.resgatarTerrenosArrendados(LoginController.getLocatarioLogado()));
                        break;
                    case 2:
                        System.out.println(locatarioController.resgatarTerrenosArrendados(LoginController.getLocatarioLogado()));
                        System.out.println("Digite o número do id do contrato que deseja cancelar: ");
                        int cadastro = Validacao.validarInt();
                        System.out.println(locatarioController.cancelarcontrato(cadastro, LoginController.getLocatarioLogado()));
                        break;
                    case 3:
                        String tituloAnuncio = Validacao.validarString("Digite o título do anúncio: ");
                        String descricaoAnuncio = Validacao.validarString("Digite a descrição do anúncio: ");
                        String localizacaoAnuncio = Validacao.validarString("Digite a localização do anúncio: ");
                        String tamanhoAnuncio = Validacao.validarString("Digite o tamanho da área: ");
                        System.out.println("Digite o valor: ");
                        double precoAnuncio = Validacao.validarInt();
                        try {
                            Validacao.ValidarInfoTerreno(tituloAnuncio, descricaoAnuncio, localizacaoAnuncio, tamanhoAnuncio, precoAnuncio, LoginController.getLocatarioLogado());
                            boolean criarAnuncio = locatarioService.criarAnuncio(tituloAnuncio, descricaoAnuncio, localizacaoAnuncio, tamanhoAnuncio, precoAnuncio, LoginController.getLocatarioLogado());
                            if (criarAnuncio){
                                System.out.println("Cadastro realizado com sucesso");
                            } else {
                                System.out.println("Cadastro não realizado");
                            }
                        } catch (EmptyDataException e){
                            System.err.println(e.getMessage());
                        }
                        break;
                    case 4:
                        System.out.println(locatarioController.resgatarTerrenosArrendados(LoginController.getLocatarioLogado()));
                        System.out.println("Digite o número do id do anúncio que deseja deletar: ");
                        int deletar = Validacao.validarInt();
                        terrenoController.deletarDados(deletar);
                        break;
                    case 5:
                        System.out.println(FeedController.mostrarTerrenosPorLocatario(LoginController.getLocatarioLogado().getNome()));
                        break;
                    case 0:
                        LocatarioMenu.menuInicial();
                        break;
                }
            } while (menuTerrenos != 0);
        }

        private static void menuDeletar (){
            System.out.println("Tem certeza que deseja deletar seu perfil?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");
            int i = Validacao.validarInt();
            if (i == 1) {
                locatarioController.deletarPerfil(LoginController.getLocatarioLogado().getId());
                System.out.println("Perfil deletado");
                SessaoMenu.menuInicial();
            } else {
                menuInicial();
            }
            int menuAreaLogada = 0;
        }


}

