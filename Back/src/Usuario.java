public class Usuario {

    private static int contadorDeIdUsuario = 1;
    private int id;
    private String nomeUsuario;
    private String email;
    private String senha;
    private String nome;
    private String nascimento;

    public Usuario(String nomeUsuario, String email, String senha, String nome, String nascimento) {
        this.id = contadorDeIdUsuario++;
        this.nomeUsuario = nomeUsuario;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.nascimento = nascimento;
    }

    public int getId(){
        return this.id;
    }

    public String getNomeUsuario(){
        return this.nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario){
        this.nomeUsuario = nomeUsuario;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getSenha(){
        return this.senha;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNascimento(){
        return this.nascimento;
    }

    public void setNascimento(String nascimento){
        this.nascimento = nascimento;
    }

}
