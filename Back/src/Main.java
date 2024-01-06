import app.Feed;
import database.BancoDeDados;
import database.DadosMocados;
import services.LocadorService;
import services.LocatarioService;
import services.TerrenoService;
import util.ConferenciaDeUsuario;
import util.RetornaId;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        LocadorService locadorService = new LocadorService();
        LocatarioService locatarioService = new LocatarioService();
        TerrenoService terrenoService = new TerrenoService();
        ConferenciaDeUsuario conferenciaDeUsuario = new ConferenciaDeUsuario();
        RetornaId retornaId = new RetornaId();
        Feed app = new Feed();
        DadosMocados.databaseOn();


        locadorService.cadastrar("Leonardo", "dasdsad", "12345", "lkfkj", "040158");
        locadorService.cadastrar("Mariana", "dasdsad", "123", "lkfkj", "040158");
        locatarioService.cadastrar("Mariana", "dfdf", "12345", "dff", "fsdfdsf");


        System.out.println("Bem vindo a TerrAtiva!\nEsta platforma facilita o arrendamento de terras, conectando pessoas!");

        int cadastro;
        do{
            System.out.println("Você ja é cadastrado em nossa plataforma? ");
            System.out.println("1 - Digite um se você é cadastrado!\n2 - Caso deseja se cadastrar!");
            cadastro = scanner.nextInt();
            scanner.nextLine();
            switch (cadastro){
                case 1:
                    break;
                case 2:
                    System.out.println("Bem vindo a área de cadastro!");
                    System.out.println("1 - Digite um caso deseja colocar uma terra para locação.\n2 - Digite dois caso deseja locar uma terra para você.\n0 - Digite zero para retornar a etapa anterior.");
                    int locadorOuLocatario = scanner.nextInt();
                    scanner.nextLine();
                    switch (locadorOuLocatario){
                        case 1:
                            System.out.println("Vamos precisar de alguns dados para seu cadastro");
                            System.out.println("Qual sera seu nome de usuário:");
                            String nomeDeUsuarioLocador = scanner.nextLine();
                            System.out.println("Qual seu E-mail:");
                            String emailDeUusarioLocador = scanner.nextLine();
                            System.out.println("Digite sua senha:");
                            String senhaDeUsuarioLocador = scanner.nextLine();
                            System.out.println("Digite eu nome:");
                            String nomeLocador = scanner.nextLine();
                            System.out.println("Digite sua data de nascimento");
                            String nascimentoLocador = scanner.nextLine();
                            locadorService.cadastrar(nomeDeUsuarioLocador,emailDeUusarioLocador,senhaDeUsuarioLocador,nomeLocador,nascimentoLocador);
                            System.out.println("Cadastro realizado");
                            cadastro = 1;
                            break;
                        case 2:
                            System.out.println("Vamos precisar de alguns dados para seu cadastro");
                            System.out.println("Qual sera seu nome de usuário:");
                            String nomeDeUsuarioLocatario = scanner.nextLine();
                            System.out.println("Qual seu E-mail:");
                            String emailDeUusarioLocatario = scanner.nextLine();
                            System.out.println("Digite sua senha:");
                            String senhaDeUsuarioLocatario = scanner.nextLine();
                            System.out.println("Digite eu nome:");
                            String nomeLocatario = scanner.nextLine();
                            System.out.println("Digite sua data de nascimento");
                            String nascimentoLocatario = scanner.nextLine();
                            locatarioService.cadastrar(nomeDeUsuarioLocatario, emailDeUusarioLocatario,senhaDeUsuarioLocatario,nomeLocatario,nascimentoLocatario);
                            System.out.println("Cadastro realizado");
                            cadastro = 1;
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("Valor digitado inválido, vamos recomeçar");
                    }

            }
        } while (cadastro !=1);

        boolean retorno;
        int area;
        do {
            System.out.println("Área de Login");
            System.out.println("1 - Digite um se você é locador.\n2 - Digite dois se você é locatário.");
            area = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Digite seu nome de usuário:");
            String nomeLocador = scanner.nextLine();
            System.out.println("Digite sua senha:");
            String senhaLocador = scanner.nextLine();
            retorno = conferenciaDeUsuario.conferencia(area,nomeLocador,senhaLocador);
            if (!retorno){
                System.out.println("Usuário errado, Digite novamente");
            } else{
                int idNumero = + retornaId.retornaId(area, nomeLocador, senhaLocador);
                System.err.println("Seu id para fazer as transações é: " + idNumero);
            }
        } while (!retorno);


        int menuAreaLogada;
        if (area == 1){
            do {
                System.out.println("Bem-vindo a área logada");
                System.out.println("O que você deseja fazer");
                System.out.println("1 - Imprimir Perfil");
                System.out.println("2 - Atualizar Perfil");
                System.out.println("3 - Entrar no menu de terrenos");
                System.out.println("4 - Deletar Perfil");
                System.out.println("0 - Sair");
                menuAreaLogada = scanner.nextInt();
                scanner.nextLine();
                switch (menuAreaLogada){
                    case 1:
                        System.out.println("Digite seu ID:");
                        int idImprimir = scanner.nextInt();
                        scanner.nextLine();
                        locadorService.imprimirPerfil(idImprimir);
                        break;
                    case 2:
                        System.out.println("Vamos atualizar seu perfil:");
                        System.out.println("Digite seu ID:");
                        int idAtualizar = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Digite seu nome de usuario novo");
                        String nomeUsuarioAtualizar = scanner.nextLine();
                        System.out.println("Digite seu novo E-mail");
                        String emailAtualizar = scanner.nextLine();
                        System.out.println("Digite sua nova senha");
                        String senhaAtualizar = scanner.nextLine();
                        System.out.println("Digite seu novo nome");
                        String nomeAtualizar = scanner.nextLine();
                        System.out.println("Digite sua data de nascimento");
                        String dataAtualizar = scanner.nextLine();
                        locadorService.atualizarPerfil(idAtualizar, nomeUsuarioAtualizar, emailAtualizar, senhaAtualizar, nomeAtualizar, dataAtualizar);
                        break;
                    case 3:
                        int menuTerrenos;
                        do {
                            System.out.println("Bem-vindo ao menu dos terrenos do locador");
                            System.out.println("O que você deseja fazer!");
                            System.out.println("1 - Cadastrar terreno");
                            System.out.println("2 - Atualizar dados dos terrenos");
                            System.out.println("3 - Deletar terreno");
                            System.out.println("0 - Sair");
                            menuTerrenos = scanner.nextInt();
                            scanner.nextLine();

                            //Precisa Continuar
                        } while (menuTerrenos == 0);
                    case 4:
                        System.out.println("Tem certeza que seja deletar seu perfil?");
                        System.out.println("Digite seu ID para deletar");
                        int idDeletar = scanner.nextInt();
                        locadorService.deletarPerfil(idDeletar);
                        System.out.println("Perfil deletado");
                        break;
                }
            } while (menuAreaLogada != 0);



        }
        if (area == 2){
            System.out.println("Bem-vindo a área do locatario");

        }



    }
}