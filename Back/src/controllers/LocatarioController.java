package controllers;

import infra.exceptions.EmptyDataException;
import models.Terreno;
import services.LocadorService;
import services.LocatarioService;

import java.util.List;

/**
 * Classe Locatario Controller implementada visando o tratamento de exceptions que possam ser lançadas
 * nos serviços da aplicação.
 * <p>
 * Atribuímos o nome de Controller para clareza e semâtica no fluxo de dados, mas ela também se comportará como um
 * middleware para tratamento de erros
 *
 * @author Pedro Henrique Pereira
 */
public class LocatarioController {
    private LocatarioService service = new LocatarioService();

    /**
     * Cadastro de um novo locatario dentro do sistema;
     *
     * @param nomeUsuario String username
     * @param email       String email do usuário
     * @param senha       String senha do usuário
     * @param nome        String nome do usuário
     * @param nascimento  String data de nascimento do usuário
     * @return Mensagem de Sucesso de cadastro
     * @throws EmptyDataException caso algum parâmetro esteja em branco
     */
    public String cadastrar(String nomeUsuario, String email, String senha, String nome, String nascimento) {
        try {
            service.cadastrar(nomeUsuario, email, senha, nome, nascimento);
            return "Usuário Cadastrado com Sucesso!";

        } catch (EmptyDataException e) {
            return e.getMessage();
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

