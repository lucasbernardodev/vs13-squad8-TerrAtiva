package services;

import database.BancoDeDados;
import infra.exceptions.EmptyDataException;
import interfaces.CrudConta;
import models.Locatario;
import models.Terreno;
import util.Validacao;

import java.util.List;

public class LocatarioService implements CrudConta<Locatario> {
    //TODO: Lógica dos métodos (faltava o banco de dados no momento pra fazer)
    //TODO: Jogar em pasta de services
    /**
     * Cadastra um novo locatário para o banco de dados
     *
     * @param nomeUsuario Nome de usuário do novo locatário
     * @param email       Email do usuário do novo locatário
     * @param senha       Senha do usuário do novo locatário
     * @param nome        Nome usuário do novo locatário (a depender do usuário)
     * @param nascimento  Data de usuário do novo locatário no padrão DD/mm/AAAA
     */
    TerrenoService terrenoService = new TerrenoService();

    public void cadastrar(String nomeUsuario,
                          String email,
                          String senha,
                          String nome,
                          String nascimento) {

        Validacao.validarInfoUsuario(nomeUsuario, email, senha, nome, nascimento);

        BancoDeDados.locatariosDataBase.add(new Locatario(nomeUsuario,
                email, senha, nome, nascimento));

    }
    /***
     * Atualiza perfil do locatario a depender do Id recebido
     * Utiliza o método "resgatarLocatario" passando o id como parameter e retornando locatário referente ao ID
     * @param locatario Objeto Locatario com o perfil atualizado do usuário
     */
    @Override
    public final void atualizarPerfil(Locatario locatario) {
        Validacao.validarInfoUsuario(locatario.getNomeUsuario(), locatario.getEmail(), locatario.getSenha(), locatario.getNome(), locatario.getNascimento());

        Locatario perfilAtual = resgatarLocatarios(locatario.getId());
        perfilAtual.setNomeUsuario(locatario.getNomeUsuario());
        perfilAtual.setEmail(locatario.getEmail());
        perfilAtual.setNome(locatario.getNome());
        perfilAtual.setNascimento(locatario.getNascimento());

    }
    /**
     * Exclui o perfil do Locador do banco de dados.
     * Utiliza o método "resgatarLocatario" passando o id como parameter e retornando locatário referente ao ID
     *
     * @param id Id do locatário do perfil que deseja excluir
     */
    public final void deletarPerfil(int id) {

        BancoDeDados.locatariosDataBase.remove(resgatarLocatarios(id));
    }

    /***
     * Imprime perfil de Locatário referente ao ID
     *
     * @param id ID do locatário para busca
     * @return String formatada para apresentar os dados do perfil Locatário
     */
    public final String imprimirPerfil(int id) {
        Locatario locatarioAtual = resgatarLocatarios(id);
        return locatarioAtual.toString();

    }
    /**
     * Busca pelo locatário referente ao Id fornecido
     *
     * @param id Id do Locatario para ser buscado
     * @return Objeto locatário correspondente a ID passado por parâmetro
     */
    public final Locatario resgatarLocatarios(int id) {

        return BancoDeDados.locatariosDataBase
                .stream()
                .filter(locatario -> locatario.getId() == id)
                .findFirst().get();

    }
    /***
     * Busca um locatario pelo email fornecido
     * @param email email do locatário para ser recuperado
     * @return Objeto Locatario referente ao email informado
     * @throws Exception Lança erro caso email fornecido não constar a nenhum locatário
     */
    public final Locatario resgatarLocatarios(String email) {

        try {
            return BancoDeDados.locatariosDataBase
                    .stream()
                    .filter(locatario -> locatario.getEmail().equals(email))
                    .findFirst().get();
        } catch (Exception e) {
            System.err.println("Email não existente!");
        }
        return null;
    }

    //    TODO: Definir forma de login e atrelar usuario logado atomaticamente ao inves de receber proprietario por parametro.
    /***
     * Cria um anúncio no sistema.
     * Utiliza o metodo "cadastrarTerreno" para da cadastrar um terreno com as informações passadas como parâmetro para o anúncio.
     *
     * @param titulo Titulo para o terreno que vai ser usado no anuncio
     * @param descricao Descricao para o terreno que vai ser usado no anuncio
     * @param localizacao   Localização para o terreno que vai ser usado no anúncio
     * @param tamanho   Tamanho para o terreno que vai ser usado no anúncio
     * @param preco     Preço para o terreno que vai ser usado no anúncio
     * @param proprietario Locatário que será o proprietário do terreno
     * @return True quando anuncio for criado com sucesso
     */
    public final boolean criarAnuncio(
            String titulo,
            String descricao,
            String localizacao,
            String tamanho,
            double preco,
            Locatario proprietario
    ) {
        terrenoService.cadastrarTerreno(titulo, descricao, localizacao, tamanho, preco, proprietario);
        return true;
    }
    /**
     * Remove um anuncio do sistema.
     *
     * Utiliza o metodo "buscarTerreno" passando o Id fornecido pelo parâmetro para retornar o Terreno referente ao ID
     * @param id id do terreno a ser removido
     * @return True caso terreno esteja disponível para arrendamento || False caso não seja removido, e imprimi uma mensagem
     */
    public final boolean removerAnuncio(int id) {
        Terreno anuncio = terrenoService.buscarTerreno(id);

        if (anuncio.isDisponivel()) {
            terrenoService.deletarTerreno(id);
            return true;
        }

        System.out.println("Terrenos arrendados não podem ser removidos, " +
                "por favor realizar cancelamento do contrato para seguir com a ação");
        return false;

    }
    /**
     * Cancela o contrato de arrendamento de terreno e o deixa disponivel.
     * Utiliza o metodo "buscarTerreno" passando o Id fornecido pelo parâmetro para retornar o Terreno referente ao ID
     * @param idTerreno Id do terreno que será cancelado
     * @param proprietario  Locatário proprietário do Terreno;
     * @return True se o cancelamento for concluido com sucesso || False caso condição não encontrar o terreno referente ao id e nao encontrar o proprietário;
     */
    public final boolean cancelarcontrato(int idTerreno, Locatario proprietario) {
        Terreno contratoAtual = terrenoService.buscarTerreno(idTerreno);
        if (contratoAtual != null && contratoAtual.getProprietario() == proprietario) {
            contratoAtual.setLocador(null);
            contratoAtual.setDisponivel(true);
            return true;
        } else {
            return false;
        }
    }
    /***
     *  Busca a lista de todos os terrenos arrendados por um locatário
     * @param proprietario Locatário na qual deseja encontrar os terrenos
     * @return Lista de terrenos arredados pelo locatário
     */
    //    TODO: Definir forma de login e atrelar usuario logado atomaticamente ao inves de receber proprietario por parametro.
    public final List<Terreno> resgatarTerrenosArrendados(Locatario proprietario) {
        return terrenoService.buscarTerrenoArrendado(proprietario);
    }
}
