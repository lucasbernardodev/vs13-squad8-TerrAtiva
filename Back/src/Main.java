import models.Locador;
import services.LocadorService;
import services.LocatarioService;
import database.BancoDeDados;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        //TESTES DO CRUD DE LOCADOR, NÃO DELETAR, SÓ COMENTAR SE NÃO FOR USAR
        LocadorService locadorService = new LocadorService();
        locadorService.cadastrar("davidk", "david@gmail.com",
                "123456", "David", "02/10/97");
        locadorService.cadastrar("diuli", "diuli@gmail.com",
                "qwe123", "Diuliano", "01/05/98");
        locadorService.atualizarPerfil(1, "davidtesteee", "david@gmail.com", "novaSenha",
                "David", "02/10/97");
        System.out.println(BancoDeDados.locadoresDataBase.get(0));
        System.out.println(BancoDeDados.locadoresDataBase.get(1));
        locadorService.deletarPerfil(1);
        System.out.println(BancoDeDados.locadoresDataBase.get(0));
        locadorService.imprimirPerfil(2);

        //TESTES DO CRUD DE LOCATARIO, NÃO DELETAR, SÓ COMENTAR SE NÃO FOR USAR
        LocatarioService locatarioService = new LocatarioService();
        locatarioService.cadastrar("lucas", "lucas@gmail.com",
                "qwerty", "Lucas Silva", "11/22/97");
        locatarioService.cadastrar("maria", "maria@gmail.com",
                "abc123", "maria madalena", "01/05/91");
        locatarioService.atualizarPerfil(1, "lucasTeste", "luquinhas@gmail.com", "novaSenha",
                "Lucas", "01/01/01");
        System.out.println(BancoDeDados.locatariosDataBase.get(0));
        System.out.println(BancoDeDados.locatariosDataBase.get(1));
        locatarioService.deletarPerfil(1);
        System.out.println(BancoDeDados.locatariosDataBase.get(0));
        locatarioService.imprimirPerfil(2);
    }
}