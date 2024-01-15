package models;

public class Terreno {
    private Integer id;
    private String titulo;
    private String descricao;
    private Integer proprietarioID;
    private Integer enderecoID;
    private double preco;
    private String tamanho;
    private String disponivel;

    public Terreno() {}

    public Terreno(String titulo, String descricao, Integer proprietarioID,
                   Integer enderecoID, double preco, String tamanho) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.proprietarioID = proprietarioID;
        this.enderecoID = enderecoID;
        this.preco = preco;
        this.tamanho = tamanho;
        this.disponivel = "S";
    }
    public Terreno(String titulo, String descricao, Integer proprietarioID,
                   Integer enderecoID, double preco, String tamanho,String disponivel) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.proprietarioID = proprietarioID;
        this.enderecoID = enderecoID;
        this.preco = preco;
        this.tamanho = tamanho;
        this.disponivel = disponivel;
    }
    public Terreno(Integer id,String titulo, String descricao, Integer proprietarioID,
                   Integer enderecoID, double preco, String tamanho,String disponivel) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.proprietarioID = proprietarioID;
        this.enderecoID = enderecoID;
        this.preco = preco;
        this.tamanho = tamanho;
        this.disponivel = disponivel;
    }

    public Terreno(Integer id,String titulo, String descricao, Integer proprietarioID,
                   Integer enderecoID, double preco, String tamanho) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.proprietarioID = proprietarioID;
        this.enderecoID = enderecoID;
        this.preco = preco;
        this.tamanho = tamanho;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getProprietarioID() {
        return proprietarioID;
    }

    public void setProprietarioID(Integer proprietarioID) {
        this.proprietarioID = proprietarioID;
    }

    public Integer getEnderecoID() {
        return enderecoID;
    }

    public void setEnderecoID(Integer enderecoID) {
        this.enderecoID = enderecoID;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(String disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "Terreno{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", proprietarioID=" + proprietarioID +
                ", enderecoID=" + enderecoID +
                ", preco=" + preco +
                ", tamanho='" + tamanho + '\'' +
                ", disponivel='" + disponivel + '\'' +
                '}';
    }
}
