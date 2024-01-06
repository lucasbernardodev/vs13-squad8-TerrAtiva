package models;

import database.BancoDeDados;

public class Locatario extends Usuario {

    public Locatario(
            String nomeUsuario,
            String email,
            String senha,
            String nome,
            String nascimento
    ) {
        super(BancoDeDados.novoLocatarioID(), nomeUsuario, email, senha, nome, nascimento);
    }

    @Override
    public String toString() {
        return "\n" + " Locatario{" + "\n" +
                "   id: " + this.getId() + "," + "\n" +
                "   nomeUsuario: " + this.getNomeUsuario() + "," + "\n" +
                "   email: " + this.getEmail() + "," + "\n" +
                "   nome: " + this.getNome() + "," + "\n" +
                "   dataDeNascimento: "  + this.getNascimento() + "\n" +
                " };" + "\n";
    }
}
