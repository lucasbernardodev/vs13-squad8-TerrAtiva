package interfaces;

public interface Validacoes {

    void ENDERECO(Integer usuarioID, String logradouro,
                  Integer numero, String complemento,
                  String bairro, Integer codigoMunicipioIBGE,
                  Integer cep);

    void ENDERECO_TERRENOS(String logradouro,
                           Integer numero, String complemento,
                           String bairro, Integer codigoMunicipioIBGE,
                           Integer cep, String localizacao);

    public void ALUGUEL_PAGAMENTOS();

    public void CONTRATOS();

    public void MENSALIDADES();

    public void TERRENOS();

    public void USUARIOS();


}
