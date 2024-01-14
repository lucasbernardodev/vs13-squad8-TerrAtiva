package models;

public class Feed {
   private int terrenoId;
   private String titulo;
   private String descricao;
   private double preco;
   private int tamanho;
   private String estado;
   private String cidade;

   @Override
   public String toString() {
       return String.format("""
                ID: %d | Título: %s
                Descrição: %s
                Preço: R$%.2f | Tamanho: %d ha
                %s - %s
                """,
               this.terrenoId, this.titulo, this.descricao,
               this.preco, this.tamanho / 10000, this.estado, this.cidade);
   }

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
}
