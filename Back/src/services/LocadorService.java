package services;

import database.BancoDeDados;
import infra.exceptions.DataNotFoundException;
import infra.exceptions.EmptyDataException;
import interfaces.CrudConta;
import models.Locador;
import models.Terreno;
import util.Validacao;

import java.util.List;

public class LocadorService implements CrudConta<Locador> {
    //TODO: Lógica dos métodos (faltava o banco de dados no momento pra fazer)
    //TODO: editarSenha()

    TerrenoService terrenoService = new TerrenoService();
    /**
     * Cadastro de um novo Locador no banco de dados
     *
     * @param nomeUsuario Nome do usuário do novo Locador
     * @param email       Endereco de email do novo Locador
     * @param senha       Senha do novo Locador
     * @param nome        Nome do novo Locador (A desejo do usuário)
     * @param nascimento  Data de nascimento do usuário seguindo padrão DD/mm/AAAA
     * @throws EmptyDataException Lança erro caso valor do parâmetro estiver vazio
     */
    public void cadastrar(String nomeUsuario,
                          String email,
                          String senha,
                          String nome,
                          String nascimento) {

        Validacao.validarInfoUsuario(nomeUsuario, email, senha, nome, nascimento);

        BancoDeDados.locadoresDataBase.add(new Locador(nomeUsuario,
                email, senha, nome, nascimento));

    }
    /**
     * Atualiza Perfil do Locador no banco de dados
     * <p>
     * Locador para atualizar é encontrado a partir do método "resgatarLocador" passando o Id do Locador vindo do parametro
     *
     * @param locador Objeto Locador com os dados atualizados
     * @throws EmptyDataException Lança erro caso valor do atributo do objeto estiver vazio
     */
    @Override
    public final void atualizarPerfil(Locador locador) {

        Validacao.validarInfoUsuario(locador.getNomeUsuario(), locador.getEmail(), locador.getSenha(), locador.getNome(), locador.getNascimento());

        Locador perfilAtual = resgatarLocador(locador.getId());
        perfilAtual.setNomeUsuario(locador.getNomeUsuario());
        perfilAtual.setEmail(locador.getEmail());
        perfilAtual.setNome(locador.getNome());
        perfilAtual.setNascimento(locador.getNascimento());
    }
    /**
     * Deleta perfil do Locador
     * <p>
     * Locador para deletar é encontrado a partir do método "resgatarLocador" passando o Id do Locador vindo do parametro
     *
     * @param id Id do Locador que perfil queira excluir
     */
    public final void deletarPerfil(int id) {
        BancoDeDados.locadoresDataBase.remove(resgatarLocador(id));
    }
    /**
     * Imprime o perfil do Locador com base no Id do parâmetro
     * <p>
     * Locador para imprimir é encontrado a partir do método "resgatarLocador" passando o Id do Locador vindo do parametro
     *
     * @param id Id do Locador que perfil queira que seja impresso
     */
    public final String imprimirPerfil(int id) {
        Locador locadorAtual = resgatarLocador(id);
        return locadorAtual.toString();
    }
    /**
     * Retorna objeto Locador do email passado por parâmetro
     *
     * @param email Email do Locador que deseja ser retornado
     * @return Objeto locador que email corresponde ao email passado por parâmetro
     */


    public final Locador resgatarLocadores(String email) {

        try {
            return BancoDeDados.locadoresDataBase
                    .stream()
                    .filter(locador -> locador.getEmail().equals(email))
                    .findFirst().get();
        }
        catch (Exception e) {
            System.err.println("Email não existente!");
        }
        return null;

    }

    // TODO: Definir forma de login e atrelar usuario logado atomaticamente ao inves de receber locador por parametro.
    /**
     * Arreda terreno para Locador
     * <p>
     * Método busca Terreno cujo id for igual o passado por parâmetro e correlaciona o terreno ao Objeto Locador. Atualiza o valor de disponível do terreno para falso.
     * <p>
     *
     * @param idTerreno Id do terreno que sera arredado
     * @param locador   Objeto Locador que arredara o terreno
     */
    public final void arrendarTerreno(int idTerreno, Locador locador) {
        Terreno novoContrato = terrenoService.buscarTerreno(idTerreno);
        novoContrato.setLocador(locador);
        novoContrato.setDisponivel(false);
    }

    // TODO: Definir forma de login e atrelar usuario logado atomaticamente ao inves de receber locador por parametro.
    /**
     * Cancela o contrato de arrendamento da terra <p>
     * Busca o terreno com base no Id recebido por parâmetro e remove o relacionamento de arrendamento com o locador recebido por parametro.
     * Marca a disponíbilidade do terreno como Verdadeiro
     *
     * @param idTerreno Id do terreno que arrendamento sera cancelado
     * @param locador   Locador que ter o terreno retirado de sua associação
     */
    public final void cancelarcontrato(int idTerreno, Locador locador) {
        Terreno contratoAtual = terrenoService.buscarTerreno(idTerreno);
        contratoAtual.setLocador(null);
        contratoAtual.setDisponivel(true);
    }

    // TODO: Definir forma de login e atrelar usuario logado atomaticamente ao inves de receber locador por parametro.
    /**
     * Busca todos os terrenos arredados de um locador.
     * Usa o metodo "buscarTerreno" para retornar uma lista dos terrenos do locador
     *
     * @param locador Objeto Locador que terrenos serão buscados
     * @return Lista com objetos terrenos referentes ao locador
     */
    public final List<Terreno> resgatarTerrenosArrendados(Locador locador) {
        return terrenoService.buscarTerreno(locador);
    }

    // MÉTODOS PRIVADOS
    /**
     * Retorna um objeto Locador refente ao ID passado por parâmetro
     *
     * @param id Id do Locador para ser recuperado
     * @return Objeto Locador correspondente ao Id
     * @throws DataNotFoundException Caso não encontrado o id do parâmetro será lançado o erro com mensagem
     */
    public final Locador resgatarLocador(int id) {

        return BancoDeDados.locadoresDataBase
                .stream()
                .filter(locador -> locador.getId() == id)
                .findFirst()
                .orElseThrow(() -> new DataNotFoundException("Dados de Locador não Encontrado"));
    }

}
