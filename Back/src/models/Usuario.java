package models;

import java.time.LocalDate;

public class Usuario {
    public static Usuario instancia;
    private Integer usuarioId;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private String cpf;
    private LocalDate dataNascimento;
    private String sexo;
    private String ativo;
    private String celular;
    private String telefoneFixo;
    private String criado;
    private String editado;
    private boolean estaLogado;

    public Usuario(){
    }
    public Usuario(String nome, String sobrenome, String email, String senha, String cpf, LocalDate dataNascimento, String sexo, String ativo, String celular, String telefoneFixo) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.ativo = ativo;
        this.celular = celular;
        this.telefoneFixo = telefoneFixo;
    }

    public static Usuario login(
            Integer usuarioId,
            String nome,
            String sobrenome,
            String email,
            String senha,
            String cpf,
            LocalDate dataNascimento,
            String sexo,
            String ativo,
            String celular,
            String telefoneFixo
    ) {
        if (instancia == null) {
            instancia = new Usuario(
                    nome,
                    sobrenome,
                    email,
                    senha,
                    cpf,
                    dataNascimento,
                    sexo,
                    ativo,
                    celular,
                    telefoneFixo
            );
            instancia.setEstaLogado(true);
            instancia.setUsuarioId(usuarioId);
            return  instancia;
        } else {
            throw new RuntimeException("O usuário já está autenticado.");
        }
    }

    public boolean estaLogado() {
        return estaLogado;
    }

    public void setEstaLogado(boolean estaLogado) {
        this.estaLogado = estaLogado;
    }

    public void logout() {
        this.estaLogado = false;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return  dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefoneFixo() {
        return telefoneFixo;
    }

    public void setTelefoneFixo(String telefoneFixo) {
        this.telefoneFixo = telefoneFixo;
    }

    public String getCriado() {
        return criado;
    }

    public void setCriado(String criado) {
        this.criado = criado;
    }

    public String getEditado() {
        return editado;
    }

    public void setEditado(String editado) {
        this.editado = editado;
    }

    public static synchronized Usuario getInstancia(
            String nome,
            String sobrenome,
            String email,
            String senha,
            String cpf,
            LocalDate dataNascimento,
            String sexo,
            String ativo,
            String celular,
            String telefoneFixo
    ) {
        if (instancia == null) {
            instancia = new Usuario(
                    nome,
                    sobrenome,
                    email,
                    senha,
                    cpf,
                    dataNascimento,
                    sexo,
                    ativo,
                    celular,
                    telefoneFixo
            );
        }
        return instancia;
    }

    public static Usuario getInstancia() {
        return instancia;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome: " + nome +
                ", sobrenome: " + sobrenome +
                ", email: " + email +
                ", cpf: " + cpf +
                ", dataNascimento: " + dataNascimento +
                ", sexo: " + sexo +
                ", celular: " + celular +
                ", telefoneFixo: " + telefoneFixo +
                '}';
    }
}
