package br.com.dbc.vemser.terrativa.entity;

public class EnderecoTerrenos {
    private Integer id;
    private String logradouro;
    private Integer numero;
    private String complemento;
    private String bairro;
    private Integer codigoMunicipioIBGE;
    private Integer cep;
    private String localizacao;

    public EnderecoTerrenos() {}

    public EnderecoTerrenos(String logradouro,
                            Integer numero, String complemento,
                            String bairro, Integer codigoMunicipioIBGE,
                            Integer cep, String localizacao) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.codigoMunicipioIBGE = codigoMunicipioIBGE;
        this.cep = cep;
        this.localizacao = localizacao;
    }

    @Override
    public String toString() {
        return String.format("""
                Logradouro: %s | Número: %s
                Complemento: %s
                Bairro: %s
                Cep: %s
                Localização: %s
                """, this.logradouro, this.numero, this.complemento, this.bairro, this.cep, this.localizacao);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Integer getCodigoMunicipioIBGE() {
        return codigoMunicipioIBGE;
    }

    public void setCodigoMunicipioIBGE(Integer codigoMunicipioIBGE) {
        this.codigoMunicipioIBGE = codigoMunicipioIBGE;
    }

    public Integer getCep() {
        return cep;
    }

    public void setCep(Integer cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
}
