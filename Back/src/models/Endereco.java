package models;

import java.time.Instant;

public class Endereco {
    private Integer id;
    private Integer usuarioID;
    private String logradouro;
    private Integer numero;
    private String complemento;
    private String bairro;
    private Integer codigoMunicipioIBGE;
    private Integer cep;
    private Instant criado;
    private Instant editado;

    public Endereco() {}

    public Endereco(Integer usuarioID, String logradouro,
                    Integer numero, String complemento,
                    String bairro, Integer codigoMunicipioIBGE,
                    Integer cep) {
        this.usuarioID = usuarioID;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.codigoMunicipioIBGE = codigoMunicipioIBGE;
        this.cep = cep;
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
