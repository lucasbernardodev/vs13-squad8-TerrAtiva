package controllers;

import infra.exceptions.DataNotFoundException;
import infra.exceptions.EmptyDataException;
import models.Locador;
import models.Locatario;
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

        } catch (EmptyDataException e) {
            return e.getMessage();

        } catch (DataNotFoundException e) {
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

    public List<Terreno> resgatarTerrenosArrendados() {
        try {

        } catch (Exception e) {

        }
        return null;
    }
}

