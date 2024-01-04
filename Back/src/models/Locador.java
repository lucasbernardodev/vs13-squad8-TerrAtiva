package models;

public class Locador extends Usuario{


    public Locador(
            String nomeUsuario,
            String email,
            String senha,
            String nome,
            String nascimento
    ) {
        super(nomeUsuario, email, senha, nome, nascimento);
    }

    @Override
    public String toString() {
        return "\n" + " Locador{" + "\n" +
                "   id: " + this.getId() + "," + "\n" +
                "   nomeUsuario: " + this.getNomeUsuario() + "," + "\n" +
                "   email: " + this.getEmail() + "," + "\n" +
                "   nome: " + this.getNome() + "," + "\n" +
                "   dataDeNascimento: "  + this.getNascimento() + "\n" +
                " };" + "\n";
    }
}
