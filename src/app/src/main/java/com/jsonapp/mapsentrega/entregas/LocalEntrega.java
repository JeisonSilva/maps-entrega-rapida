package com.jsonapp.mapsentrega.entregas;

public class LocalEntrega {
    String logradouro;
    String numero;
    String bairro;
    String cidade;

    public LocalEntrega(String logradouro, String numero, String bairro, String cidade) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    @Override
    public String toString() {
        return String.format("%s+%s+%s+%s", getLogradouro(), getNumero(), getBairro(), getCidade());
    }
}
