package controllers;

import models.Locador;
import models.Terreno;

import java.util.List;

/**
 * Classe Locador Controller implementada visando o tratamento de exceptions que possam ser lançadas
 * nos serviços da aplicação.
 * <p>
 * Atribuímos o nome de Controller para clareza e semâtica no fluxo de dados, mas ela também se comportará como um
 * middleware para tratamento de erros
 *
 * @author Pedro Henrique Pereira
 */
public class LocadorController {
    public void cadastrar(Locador novoUsuario) {
        try {

        } catch (Exception e) {

        }
    }

    public boolean atualizarPerfil(String nomeUsuario, String email,
                                         String senha, String nome, String nascimento) {
        try {

        } catch (Exception e) {

        }
        return true;
    }

    public void deletarPerfil(int id) {
        try {

        } catch (Exception e) {

        }

    }

    public void arrendarTerreno(int idTerreno) {
        try {

        } catch (Exception e) {

        }

    }

    public void cancelarcontrato(int idTerreno) {
        try {

        } catch (Exception e) {

        }

    }

    public List<Terreno> resgatarTerrenosArrendados() {
        try {

        } catch (Exception e) {

        }
        return null;
    }
}
