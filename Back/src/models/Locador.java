package models;

import database.BancoDeDados;

public class Locador extends Usuario{


    public Locador(
            String nomeUsuario,
            String email,
            String senha,
            String nome,
            String nascimento
    ) {
        super(BancoDeDados.novoLocadorID(), nomeUsuario, email, senha, nome, nascimento);
    }

    public Locador(int id,
            String nomeUsuario,
            String email,
            String nome,
            String nascimento
    ) {
        super(id, nomeUsuario, email, nome, nascimento);
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
