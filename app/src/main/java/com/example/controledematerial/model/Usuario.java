package com.example.controledematerial.model;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String UID;
    private String nome;
    private String CPF;
    private String Senha;
    private String NumeroCartao;
    private String endereco;
    private String email;
    private String usuario;

    public Usuario(String UID, String nome, String CPF, String senha, String numeroCartao, String endereco, String email, String usuario) {
        this.UID = UID;
        this.nome = nome;
        this.CPF = CPF;
        Senha = senha;
        NumeroCartao = numeroCartao;
        this.endereco = endereco;
        this.email = email;
        this.usuario = usuario;
    }

    public Usuario(){}

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }

    public String getNumeroCartao() {
        return NumeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        NumeroCartao = numeroCartao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "UID='" + UID + '\'' +
                ", nome='" + nome + '\'' +
                ", CPF='" + CPF + '\'' +
                ", Senha='" + Senha + '\'' +
                ", NumeroCartao='" + NumeroCartao + '\'' +
                ", endereco='" + endereco + '\'' +
                ", email='" + email + '\'' +
                ", usuario='" + usuario + '\'' +
                '}';
    }
}
