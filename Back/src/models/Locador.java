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
        return String.format("""
                ### PERFIL DE LOCATÁRIO ###;
                    Usuário: %s
                    Nome: %s
                    Email: %s;
                    Nascimento: %s
                """, super.getNomeUsuario(), super.getNome(), super.getEmail(), super.getNascimento());
    }
}
