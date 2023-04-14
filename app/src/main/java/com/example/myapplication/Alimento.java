package com.example.myapplication;

public class Alimento {

    int id;
    String nome;
    String medida;
    Double quantidade;
    Double carboidratos;
    Double proteinas;
    Double gorduras;

    public Alimento() {
    }

    public Alimento(int id, String nome, String medida, Double quantidade, Double carboidratos, Double proteinas, Double gorduras) {
        this.id = id;
        this.nome = nome;
        this.medida = medida;
        this.quantidade = quantidade;
        this.carboidratos = carboidratos;
        this.proteinas = proteinas;
        this.gorduras = gorduras;
    }

    public int getId() { return id; }

    public String getNome() {
        return nome;
    }

    public String getMedida() {
        return medida;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public Double getCarboidratos() {
        return carboidratos;
    }

    public Double getProteinas() {
        return proteinas;
    }

    public Double getGorduras() {
        return gorduras;
    }
}
