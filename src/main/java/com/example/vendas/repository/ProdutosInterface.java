package com.example.vendas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.vendas.model.Produtos;

public interface ProdutosInterface extends JpaRepository<Produtos, Integer> {
}
