package com.example.vendas.repository;

import com.example.vendas.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ClientesRepository extends JpaRepository<Cliente, Integer> {


    List<Cliente> findByNameLike(String nome);
}
