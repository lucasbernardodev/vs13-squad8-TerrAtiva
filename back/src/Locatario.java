import java.util.ArrayList;
import java.util.List;

public class Locatario extends Usuarios implements IGerenciarAnuncio{

    List<Usuarios> locatarios = new ArrayList<Usuarios>();
    List<detalhesPropriedade> propriedades = new ArrayList<detalhesPropriedade>();

    @Override
    public void criarAnuncio() {

    }

    @Override
    public void editarAnuncio() {

    }

    @Override
    public void deletarAnuncio() {

    }
}
