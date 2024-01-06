package controllers;

import infra.exceptions.DataNotFoundException;
import infra.exceptions.EmptyDataException;
import models.Locatario;
import services.LocatarioService;
import util.Formatador;

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

    public String atualizarPerfil(Locatario locatario) {
        try {
            service.atualizarPerfil(locatario);
            return "Dados Atualizados com Sucesso!";

        } catch (EmptyDataException | DataNotFoundException e) {
            return e.getMessage();

        }
    }

    public String deletarPerfil(int id) {
        try {
            service.deletarPerfil(id);
            return "Perfil deletado com Sucesso!";

        } catch (DataNotFoundException e) {
            return e.getMessage();
        }

    }

    public String cancelarcontrato(int idTerreno, Locatario proprietario) {
        try {
            service.cancelarcontrato(idTerreno, proprietario);
            return String.format("Contrato N°%d Cancelado | Locador: %s", idTerreno, proprietario.getNome());
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String resgatarTerrenosArrendados(Locatario proprietario) {
        return Formatador.readerListTerrenos(service.resgatarTerrenosArrendados(proprietario));
    }

    public String imprimirPerfil(int id) {
        try {
            return service.imprimirPerfil(id);

        } catch (DataNotFoundException e) {
            return e.getMessage();
        }
    }
}

