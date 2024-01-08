package app.menus;
import controllers.FeedController;
import controllers.LocatarioController;
import controllers.TerrenoController;
import infra.exceptions.EmptyDataException;
import models.Locador;
import models.Locatario;
import services.LocatarioService;
import util.ConferenciaDeUsuario;
import util.RetornaId;
import util.Validacao;

import java.util.Scanner;

import static app.menus.SessaoMenu.locadorLogado;
import static app.menus.SessaoMenu.menuInicial;

public class LocatarioMenu {

        public static Locatario locatarioLogado = new Locatario("", "", "", "", "");

        static Scanner scanner = new Scanner(System.in);
        static LocatarioController locatarioController = new LocatarioController();
        static LocatarioService locatarioService = new LocatarioService();
        static TerrenoController terrenoController = new TerrenoController();
        static ConferenciaDeUsuario conferenciaDeUsuario = new ConferenciaDeUsuario();
        static RetornaId retornaId = new RetornaId();
        static int cadastro;
        private static int area;


        public static void menuIncial (Locatario usuarioLogado){
            locatarioLogado = usuarioLogado;
            int menuAreaLogada;
                do {
                    System.out.println("Bem-vindo a área logada");
                    System.out.println("O que você deseja fazer");
                    System.out.println("1 - Imprimir Perfil");
                    System.out.println("2 - Atualizar Perfil");
                    System.out.println("3 - Acessar meus terreno");
                    System.out.println("4 - Deletar Perfil");
                    System.out.println("0 - Sair");

                    menuAreaLogada = Validacao.validarInt();
                    switch (menuAreaLogada) {
                        case 1:
                            imprimirUsuario(usuarioLogado);
                            menuAreaLogada = 0;
                            break;
                        case 2:
                            menuAtualizarPerfil(locatarioLogado);
                            menuAreaLogada = 0;
                            break;
                        case 3:
                            menuTerreno(locatarioLogado);
                            menuAreaLogada = 0;
                            break;
                        case 4:
                            menuDeletar(locatarioLogado);
                            menuAreaLogada = 0;
                        case 0:
                            System.out.println("Saindo...");
                            break;
                        default:
                            System.out.println("Opção inválida");
                    }
                } while (menuAreaLogada != 0);

        }

        private static void imprimirUsuario (Locatario locatarioLogado){
            String usuario = locatarioService.imprimirPerfil(locatarioLogado.getId());
            System.out.println(usuario);
            menuIncial(locatarioLogado);
        }

        private static void menuAtualizarPerfil (Locatario usuarioLogado){
            System.out.println("Vamos atualizar seu perfil:");
            String nomeUsuarioAtualizar = Validacao.validarString("Usuário novo:");
            String emailAtualizar = Validacao.validarString("E-mail novo:");
            String nomeAtualizar = Validacao.validarString("Nome novo:");
            String dataAtualizar = Validacao.validarString("Data de nascimento:");
            String atualizandoPerfil = locatarioController.atualizarPerfil(
                    new Locatario(
                            usuarioLogado.getId(),
                            nomeUsuarioAtualizar,
                            emailAtualizar,
                            usuarioLogado.getSenha(),
                            nomeAtualizar,
                            dataAtualizar)
            );
            System.out.println(atualizandoPerfil);
            LocatarioMenu.menuIncial(locatarioLogado);
        }

        private static void menuTerreno (Locatario usuarioLogado){
            int menuTerrenos;
            do {
                System.out.println("Bem-vindo ao menu dos terrenos do locatário");
                System.out.println("O que você deseja fazer!");
                System.out.println("1 - Vizualizar terrenos arrendados");
                System.out.println("2 - Cancelar Contratos");
                System.out.println("3 - Criar anúncio de terrenos");
                System.out.println("4 - Deletar anúncio");
                System.out.println("0 - Voltar ao menu Anterior");
                System.out.println("Digite a opção desejada: ");
                menuTerrenos = Validacao.validarInt();
                switch (menuTerrenos) {
                    case 1:
                        System.out.println(locatarioController.resgatarTerrenosArrendados(usuarioLogado));
                        break;
                    case 2:
                        System.out.println(locatarioController.resgatarTerrenosArrendados(usuarioLogado));
                        System.out.println("Digite o número do id do contrato que deseja cancelar: ");
                        int cadastro = Validacao.validarInt();
                        System.out.println(locatarioController.cancelarcontrato(cadastro, usuarioLogado));
                        break;
                    case 3:
                        String tituloAnuncio = Validacao.validarString("Digite o título do anúncio: ");
                        String descricaoAnuncio = Validacao.validarString("Digite a descrição do anúncio: ");
                        String localizacaoAnuncio = Validacao.validarString("Digite a localização do anúncio: ");
                        String tamanhoAnuncio = Validacao.validarString("Digite o tamanho da área: ");
                        System.out.println("Digite o valor: ");
                        double precoAnuncio = Validacao.validarInt();
                        try {
                            Validacao.ValidarInfoTerreno(tituloAnuncio, descricaoAnuncio, localizacaoAnuncio, tamanhoAnuncio, precoAnuncio, usuarioLogado);
                            boolean criarAnuncio = locatarioService.criarAnuncio(tituloAnuncio, descricaoAnuncio, localizacaoAnuncio, tamanhoAnuncio, precoAnuncio, usuarioLogado);
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
                        System.out.println(locatarioController.resgatarTerrenosArrendados(usuarioLogado));
                        System.out.println("Digite o número do id do anúncio que deseja deletar: ");
                        int deletar = Validacao.validarInt();
                        terrenoController.deletarDados(deletar);
                        break;
                    case 0:
                        LocatarioMenu.menuIncial(locatarioLogado);
                        break;
                }
            } while (menuTerrenos != 0);
        }

        private static void menuDeletar (Locatario locatarioLogado){
            System.out.println("Tem certeza que deseja deletar seu perfil?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");
            int i = Validacao.validarInt();
            if (i == 1) {
                locatarioController.deletarPerfil(locatarioLogado.getId());
                System.out.println("Perfil deletado");
                menuInicial();
            } else {
                menuIncial(locatarioLogado);
            }
            int menuAreaLogada = 0;
        }
    }

