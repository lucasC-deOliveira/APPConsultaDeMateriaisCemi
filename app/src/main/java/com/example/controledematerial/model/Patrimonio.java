package com.example.controledematerial.model;

public class Patrimonio {
    private String UID;
    private String numero;
    private String nome;
    private String descricao;

    public Patrimonio(){}

    public Patrimonio(String UID, String numero, String nome, String descricao) {
        this.UID = UID;
        this.numero = numero;
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Patrimonio{" +
                "UID='" + UID + '\'' +
                ", numero='" + numero + '\'' +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
