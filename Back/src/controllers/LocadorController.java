package controllers;

import infra.exceptions.DataNotFoundException;
import infra.exceptions.EmptyDataException;
import models.Locador;
import services.LocadorService;
import util.Formatador;

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
    private LocadorService service = new LocadorService();

    /**
     * Cadastro de um novo locador dentro do sistema;
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

    /**
     * Atualização dos dados de perfil do locador. Levando em conta que os dados serão passados
     * do usuário já logado, só é necessário passar a instância do objeto vinda do main.
     *
     * @param locador Objeto com os novos dados do locador
     * @return Mensagem de Sucesso de atualização
     * @throws EmptyDataException    caso algum parâmetro esteja em branco
     * @throws DataNotFoundException caso o Usuário não seja encontrado nos dados salvos
     */
    public String atualizarPerfil(Locador locador) {
        try {
            service.atualizarPerfil(locador);
            return "Dados Atualizados com Sucesso!";

        } catch (EmptyDataException e) {
            return e.getMessage();

        } catch (DataNotFoundException e) {
            return e.getMessage();
        }
    }

    /**
     * Deletar um usuário da base de dadso
     *
     * @param id int id do usuário
     * @return Mensagem de Sucesso ao deletar
     * @throws DataNotFoundException Caso dados não sejam encontrados na base dados
     */
    public String deletarPerfil(int id) {
        try {
            service.deletarPerfil(id);
            return "Perfil deletado com Sucesso!";

        } catch (DataNotFoundException e) {
            return e.getMessage();
        }

    }

    /**
     * Arrenda o terreno para determinado locador
     *
     * @param idTerreno int key do terreno que irá ser arrendado
     * @param locador   Locador quem vai arrendar o terreno
     * @return Mensagem com arrendamento bem sucedido
     * @throws DataNotFoundException caso não seja encontrado
     */
    public String arrendarTerreno(int idTerreno, Locador locador) {
        try {
            service.arrendarTerreno(idTerreno, locador);
            return "Terreno Arrendado com Sucesso";
        } catch (DataNotFoundException e) {
            return e.getMessage();
        }

    }

    /**
     * Arrenda o terreno para determinado locador
     *
     * @param idTerreno int key do terreno/contrato que será cancelado
     * @param locador   Locador quem vai arrendar o terreno
     * @return Mensagem de cancelamento bem sucedido
     * @throws DataNotFoundException caso não seja encontrado
     */
    public String cancelarcontrato(int idTerreno, Locador locador) {
        try {
            service.cancelarcontrato(idTerreno, locador);
            return String.format("Contrato N°%d Cancelado | Locador: %s", idTerreno, locador.getNome());
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    /**
     * Retorna todos os terrenos arrendados pelo locador logado
     *
     * @param locador Locador usuário logado
     * @return String correspondente a todos os terrenos arrendados
     */
    public String resgatarTerrenosArrendados(Locador locador) {
        return Formatador.readerListTerrenos(service.resgatarTerrenosArrendados(locador));
    }
}
