package com.example.vendas.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 100)
    private String name;

    @OneToMany(mappedBy = "cliente")
    private Set<Pedidos> pedidos;

    public Set<Pedidos> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<Pedidos> pedidos) {
        this.pedidos = pedidos;
    }

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
