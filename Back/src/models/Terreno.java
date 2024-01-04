package models;

public class Terreno {
    private static int contadorDeId = 1;
    private int id;
    private String titulo;
    private String descricao;
    private String localizacao;
    private String tamanho;
    private double preco;
    private Locatario proprietario;
    private Locador locador;
    private boolean disponivel;

    public Terreno(String titulo, String descricao, String localizacao,
                   String tamanho, double preco, Locatario proprietario,
                   Locador locador, boolean disponivel) {

        this.id = contadorDeId++;
        this.titulo = titulo;
        this.descricao = descricao;
        this.localizacao = localizacao;
        this.tamanho = tamanho;
        this.preco = preco;
        this.proprietario = proprietario;
        this.locador = locador;
        this.disponivel = disponivel;
    }

   public int getId(){
       return id;
   }
   public String getTitulo(){
       return titulo;
   }
   public void setTitulo(String titulo){
       this.titulo = titulo;
   }
   public String getDescricao(){
       return descricao;
   }
   public void setDescricao( String descricao){
       this.descricao = descricao;
   }
   public String getLocalizacao(){
       return localizacao;
   }
    public void setLocalizacao(String localizacao){
       this.localizacao = localizacao;
    }
    public String getTamanho(){
       return tamanho;
    }
    public void setTamanho(String tamanho){
       this.tamanho = tamanho;
    }
    public double getPreco(){
       return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Locatario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Locatario proprietario) {
        this.proprietario = proprietario;
    }

    public Locador getLocador() {
        return locador;
    }

    public void setLocador(Locador locador) {
        this.locador = locador;
    }

    public boolean isDisponivel(){
       return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
   }
    public Terreno() {}

}
