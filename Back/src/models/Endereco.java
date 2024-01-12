package models;

import java.time.Instant;

public class Endereco {
    private Integer id;
    private Integer usuarioID;
    private String logradouro;
    private Integer numero;
    private String complemento;
    private String Bairro;
    private Integer codigoMunicipioIBGE;
    private String cep;
    private String localizacao;
    private Instant criado;
    private Instant editado;

    public Endereco() {}

    public Endereco(String logradouro,
                    Integer numero, String complemento,
                    String bairro, Integer codigoMunicipioIBGE,
                    String cep, String localizacao) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        Bairro = bairro;
        this.codigoMunicipioIBGE = codigoMunicipioIBGE;
        this.cep = cep;
        this.localizacao = localizacao;
        this.criado = Instant.now();
        this.editado = Instant.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(Integer usuarioID) {
        this.usuarioID = usuarioID;
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

    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String bairro) {
        Bairro = bairro;
    }

    public Integer getCodigoMunicipioIBGE() {
        return codigoMunicipioIBGE;
    }

    public void setCodigoMunicipioIBGE(Integer codigoMunicipioIBGE) {
        this.codigoMunicipioIBGE = codigoMunicipioIBGE;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public Instant getCriado() {
        return criado;
    }
    public void setCriado(Instant criado) {
        this.criado = criado;
    }

    public Instant getEditado() {
        return editado;
    }

    public void setEditado(Instant editado) {
        this.editado = editado;
    }
}
