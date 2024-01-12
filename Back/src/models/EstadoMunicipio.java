package models;

import java.time.Instant;

public class EstadoMunicipio {
    private String nomeEstado;
    private Integer codigoEstado;
    private String nomeMunicipio;
    private Integer codigoMunicipio;
    private Instant criado;
    private Instant editado;

    public EstadoMunicipio() {}

    public EstadoMunicipio(String nomeEstado, Integer codigoEstado, String nomeMunicipio,Integer codigoMunicipio) {
        this.nomeEstado = nomeEstado;
        this.codigoEstado = codigoEstado;
        this.nomeMunicipio = nomeMunicipio;
        this.codigoMunicipio = codigoMunicipio;
        this.criado = Instant.now();
        this.editado = Instant.now();
    }

    public String getNomeEstado() {
        return nomeEstado;
    }

    public void setNomeEstado(String nomeEstado) {
        this.nomeEstado = nomeEstado;
    }

    public Integer getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(Integer codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public String getNomeMunicipio() {
        return nomeMunicipio;
    }

    public void setNomeMunicipio(String nomeMunicipio) {
        this.nomeMunicipio = nomeMunicipio;
    }

    public Integer getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Integer codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
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
