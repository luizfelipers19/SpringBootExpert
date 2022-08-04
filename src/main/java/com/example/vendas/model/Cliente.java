package com.example.vendas.model;

public class Cliente {

    private Integer id;
    private String name;

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.name = nome;
    }

    public Integer getId() {
        return id;
    }

    public Cliente() {
    }

    public Cliente(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
