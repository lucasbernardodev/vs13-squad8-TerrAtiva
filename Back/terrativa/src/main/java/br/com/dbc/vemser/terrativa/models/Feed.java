package br.com.dbc.vemser.terrativa.models;

public class Feed {
   private int terrenoId;
   private String titulo;
   private String descricao;
   private double preco;
   private int tamanho;
   private String estado;
   private String cidade;
   private String cod_estado;
   private String quantidade;

    public Feed() {}
    public Feed(int terrenoId, String titulo, String descricao, int preco, int tamanho, String estado, String cidade) {
        this.terrenoId = terrenoId;
        this.titulo = titulo;
        this.descricao = descricao;
        this.preco = preco;
        this.tamanho = tamanho;
        this.estado = estado;
        this.cidade = cidade;
    }

    public int getTerrenoId() {
        return terrenoId;
    }

    public void setTerrenoId(int terrenoId) {
        this.terrenoId = terrenoId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCod_estado() {
        return cod_estado;
    }

    public void setCod_estado(String cod_estado) {
        this.cod_estado = cod_estado;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }
}
