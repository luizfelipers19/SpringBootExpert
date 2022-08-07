package com.example.vendas.repository;

import com.example.vendas.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ClientesRepository extends JpaRepository<Cliente, Integer> {


    @Query(value = " select * from Clientes c where c.name like '%:name%' ", nativeQuery = true)
    List<Cliente> findByNameLike(@Param("name") String name);

    @Query(" delete from Cliente c where c.name =:name ")
    @Modifying
    void deleteByName(String name);

    boolean existsByName(String name);
}
