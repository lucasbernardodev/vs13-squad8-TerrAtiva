package models;

import database.BancoDeDados;

public class Locatario extends Usuario {

    public Locatario(String nomeUsuario, String email, String senha, String nome, String nascimento) {
        super(BancoDeDados.novoLocatarioID(), nomeUsuario, email, senha, nome, nascimento);
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
