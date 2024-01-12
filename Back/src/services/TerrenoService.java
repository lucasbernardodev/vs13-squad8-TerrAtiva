package services;

import database.BancoDeDados;
import infra.exceptions.DataNotFoundException;
import models.Terreno;
import util.Validacao;

import java.util.List;
import java.util.Objects;

public class TerrenoService {
    /***
     * Cadastra novo terreno no banco de dados.
     * Cria um novo terreno e adiciona ao banco de dados de terrenos.
     * @param titulo  Título do terreno
     * @param descricao Descrição do terreno
     * @param localizacao Localização do terreno
     * @param tamanho Tamanho do terreno
     * @param preco Preço do terreno
     * @param proprietario Objeto locatário, o proprietário do terreno
     */
    public void cadastrarTerreno(String titulo, String descricao, String localizacao,
                                 String tamanho, double preco, Locatario proprietario) {

        BancoDeDados.terrenosDataBase.add(new Terreno(titulo, descricao, localizacao,
                tamanho, preco, proprietario));

    }

    /***
     * Atualiza informações do Terreno do Id referente
     * Busca o id de terreno passado por parametro e atualiza o terreno de mesmo Id
     * @param idTerreno Id de terreno para ser atualizado
     * @param terrenoAtualizado Objeto terrono com os dados atualizados.
     * @return True se Terreno for atualizado com sucesso || False caso idTerreno não for encontrado no banco de dados;
     * @throws DataNotFoundException Lança erro caso Idterreno não for encontrado
     */
    public void atualizarDadosTerreno(int idTerreno, Terreno terrenoAtualizado) {
        Validacao.ValidarInfoTerreno(terrenoAtualizado.getTitulo(),
                terrenoAtualizado.getDescricao(),
                terrenoAtualizado.getLocalizacao(),
                terrenoAtualizado.getTitulo(),
                terrenoAtualizado.getPreco(),
                terrenoAtualizado.getProprietario()
        );

        BancoDeDados.terrenosDataBase
                .stream()
                .filter(terreno -> terreno.getId() == idTerreno)
                .findFirst()
                .ifPresentOrElse(terreno -> {

                    terreno.setTitulo(terrenoAtualizado.getTitulo());
                    terreno.setDescricao(terrenoAtualizado.getDescricao());
                    terreno.setLocalizacao(terrenoAtualizado.getLocalizacao());
                    terreno.setPreco(terrenoAtualizado.getPreco());
                    terreno.setLocador(terrenoAtualizado.getLocador());
                    terreno.setDisponivel(terrenoAtualizado.isDisponivel());

                }, () -> new DataNotFoundException("Terreno não encontrado!"));

    }

    /***
     * Busca Terreno refente ao ID fornecido
     * @param idTerreno Id para ser procurado
     * @return Objeto Terreno do id fornecido
     * @throws DataNotFoundException Lança erro caso Id de terreno não for encontrado no banco de dados
     */
    public Terreno buscarTerreno(int idTerreno) {

        return BancoDeDados.terrenosDataBase
                .stream()
                .filter(terreno -> terreno.getId() == idTerreno)
                .findFirst()
                .orElseThrow(() -> new DataNotFoundException("Terreno não Encontrado na base de dados"));
    }

    /***
     * Busca Terreno do locador referente
     *
     * @param locador Locador que terá seus terrenos buscados
     * @return Lista com os terrenos associados ao locador
     */
    public List<Terreno> buscarTerreno(Locador locador) {
        return BancoDeDados.terrenosDataBase
                .stream()
                .filter(terreno -> terreno.getLocador() == locador)
                .toList();
    }

    /***
     * Busca um terreno associado a um locatário (proprietário do terreno)
     *
     * @param proprietario Objeto locatário que sera buscado seus terrenos
     * @return Lista de terrenos associados ao proprietário(locatário)
     */
    public List<Terreno> buscarTerreno(Locatario proprietario) {

        return BancoDeDados.terrenosDataBase
                .stream()
                .filter(terreno -> terreno.getProprietario() == proprietario)
                .toList();

    }

    public List<Terreno> buscarTerrenoArrendado(Locatario proprietario) {

        return BancoDeDados.terrenosDataBase
                .stream()
                .filter(terreno -> Objects.equals(terreno.getProprietario().getNome(), proprietario.getNome()))
                .filter(terreno -> !terreno.isDisponivel())
                .toList();

    }

    /***
     * Deleta Terreno associado ao ID
     * Busca no banco de dados pelo terreno de ID especifíco e o remove.
     *
     * @param idTerreno Id do terreno para ser deletado
     * @return True se Terreno for removido com sucesso || False caso idTerreno não existir no Terreno banco de dados
     */
    public void deletarTerreno(int idTerreno) {

        BancoDeDados.terrenosDataBase
                .stream()
                .filter(terreno -> terreno.getId() == idTerreno)
                .findFirst()
                .ifPresentOrElse(terreno -> {
                    BancoDeDados.terrenosDataBase.remove(terreno);
                }, () -> new DataNotFoundException("Terreno não encontrado"));
    }
}
