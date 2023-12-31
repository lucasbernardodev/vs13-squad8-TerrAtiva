public class detalhesPropriedade {
    int id;
    static int contador = 1;
    String titulo,
            descricao,
    localizacao,
    tamanho;
    double preco;
    Locatario locatario = new Locatario();
    Locador locador = new Locador();
    boolean anuncioAtivo = true,
    arrentado = false;

    public detalhesPropriedade() {
    }

    public detalhesPropriedade(String titulo, String descricao, String localizacao, String tamanho, double preco, Locatario locatario, Locador locador) {
        this.id = contador;
        contador++;
        this.titulo = titulo;
        this.descricao = descricao;
        this.localizacao = localizacao;
        this.tamanho = tamanho;
        this.preco = preco;
        this.locatario = locatario;
        this.locador = locador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Locatario getLocatario() {
        return locatario;
    }

    public void setLocatario(Locatario locatario) {
        this.locatario = locatario;
    }

    public Locador getLocador() {
        return locador;
    }

    public void setLocador(Locador locador) {
        this.locador = locador;
    }

    public boolean isAnuncioAtivo() {
        return anuncioAtivo;
    }

    public void setAnuncioAtivo(boolean anuncioAtivo) {
        this.anuncioAtivo = anuncioAtivo;
    }

    public boolean isArrentado() {
        return arrentado;
    }

    public void setArrentado(boolean arrentado) {
        this.arrentado = arrentado;
    }
}
