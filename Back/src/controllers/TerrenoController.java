package controllers;

import infra.exceptions.DataNotFoundException;
import infra.exceptions.EmptyDataException;
import models.Locatario;
import models.Terreno;
import services.TerrenoService;

/**
 * Classe Locador Controller implementada visando o tratamento de exceptions que possam ser lançadas
 * nos serviços da aplicação.
 * <p>
 * Atribuímos o nome de Controller para clareza e semâtica no fluxo de dados, mas ela também se comportará como um
 * middleware para tratamento de erros
 *
 * @author Pedro Henrique Pereira
 */
public class TerrenoController {

    TerrenoService service = new TerrenoService();

    public String cadastrarTerreno(String titulo, String descricao, String localizacao,
                                   String tamanho, double preco, Locatario proprietario) {
        try {
            service.cadastrarTerreno(titulo, descricao, localizacao, tamanho, preco, proprietario);
            return "Terreno cadastrado com sucesso!";
        } catch (DataNotFoundException e) {
            return e.getMessage();
        }
    }

    public String atualizarDados(int idTerreno, Terreno data) {
        try {
            service.atualizarDadosTerreno(idTerreno, data);
            return "Dados do Terreno atualizados com Sucesso!";

        } catch (EmptyDataException | DataNotFoundException e) {
            return e.getMessage();

        }
    }

    public String deletarDados(int idTerreno) {
        try {
            service.deletarTerreno(idTerreno);
            return "Terreno deletado com Sucesso";

        } catch (DataNotFoundException e) {
            return e.getMessage();
        }
    }
}
