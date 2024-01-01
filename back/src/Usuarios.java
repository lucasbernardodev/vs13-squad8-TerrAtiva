import java.util.Date;

public class Usuarios implements IAcessoConta{
    static int contador = 1;
    int id,
    tipo;
    String email,
    senha,
    nome;
    boolean logado = false;
    Date nascimento = new Date();

    public Usuarios() {
    }

    public Usuarios(String email, String senha, String nome, Date nascimento, int tipo) {
        this.id = contador;
        contador++;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.nascimento = nascimento;
    }

    @Override
    public void cadastrar() {

    }

    @Override
    public void logar() {

    }

    @Override
    public void recuperarSenha() {

    }
    public int getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
