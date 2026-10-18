package com.acadsrvfilatransplante.model;

public class FilaTransplante {
    private String id;
    private String nome;
    private String cpf;
    private String telefone;
    private String orgao;
    private String data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getOrgao() {
        return orgao;
    }

    public void setOrgao(String orgao) {
        this.orgao = orgao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
