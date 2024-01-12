package models;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

public class Usuario {

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
    private Instant criado;
    private Instant editado;

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
        this.criado = Instant.now();
        this.editado = Instant.now();
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
