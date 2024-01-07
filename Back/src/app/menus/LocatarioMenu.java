package app.menus;
import controllers.FeedController;
import controllers.LocatarioController;
import models.Locador;
import models.Locatario;
import services.LocatarioService;
import util.ConferenciaDeUsuario;
import util.RetornaId;
import util.Validacao;

import java.util.Scanner;

import static app.menus.TempMenu.locadorService;

public class LocatarioMenu {
    static Scanner scanner = new Scanner(System.in);
    static LocatarioController locatarioController = new LocatarioController();
    static LocatarioService locatarioService = new LocatarioService();
    static ConferenciaDeUsuario conferenciaDeUsuario = new ConferenciaDeUsuario();
    static RetornaId retornaId = new RetornaId();
    static int cadastro;
    private static int area;

    static Locatario usuarioLogado = new Locatario("", "","","","");

    public static void menuIncial(Locatario usuarioLogado) {
        int menuAreaLogada;
        if (area == 2) {
            do {
                System.out.println("Bem-vindo a área logada");
                System.out.println("O que você deseja fazer");
                System.out.println("1 - Imprimir Perfil");
                System.out.println("2 - Atualizar Perfil");
                System.out.println("3 - Acessar meus terreno");
                System.out.println("4 - Acessar feed");
                System.out.println("5 - Deletar Perfil");
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
                    // Adicione aqui mais opções específicas para o Locatário
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida");
                }
            } while (menuAreaLogada != 0);
        }
    }

    private void imprimirUsuario(Locatario usuarioLogado){
        String usuario = locatarioService.imprimirPerfil(usuarioLogado.getId());
        System.out.println(usuario);
    }

    private void menuAtualizarPerfil(Locatario usuarioLogado){
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
    }

    private void menuTerreno(Locatario usuarioLogado){
        int menuTerrenos;
        do {
            System.out.println("Bem-vindo ao menu dos terrenos do locador");
            System.out.println("O que você deseja fazer!");
            System.out.println("1 - Vizualizar terrenos arrendados");
            System.out.println("2 - Cancelar Contratos");
            System.out.println("3 - Criar anúncio de terrenos");
            System.out.println("0 - Voltar ao menu Anterior");
            System.out.println("Digite a opção desejada: ");
            menuTerrenos = Validacao.validarInt();
            switch (menuTerrenos) {
                case 1:
                    locatarioController.resgatarTerrenosArrendados(usuarioLogado);
                    menuTerrenos = 0;
                    break;
                case 2:
                    System.out.println(locatarioController.resgatarTerrenosArrendados(usuarioLogado));
                    System.out.println("Digite o número do id do contrato que deseja cancelar: ");
                    int cadastro = Validacao.validarInt();
                    System.out.println(locatarioController.cancelarcontrato(cadastro, usuarioLogado));
                case 3:
                    String tituloAnuncio = Validacao.validarString("Digite o título do anúncio");
                    String descricaoAnuncio = Validacao.validarString("Digite a descrição do anúncio");
                    String localizacaoAnuncio = Validacao.validarString("Digite a localização do anúncio");
                    String tamanhoAnuncio = Validacao.validarString("Digite o tamanho da área:");
                    double precoAnuncio = Validacao.validarInt();
                    Locatario locatarioAnuncio = usuarioLogado;
                    Validacao.ValidarInfoTerreno(tituloAnuncio, descricaoAnuncio, localizacaoAnuncio, tamanhoAnuncio, precoAnuncio, locatarioAnuncio);
                    // Pedir Ajuda
                case 4:


            }
