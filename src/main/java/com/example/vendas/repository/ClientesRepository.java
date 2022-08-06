package com.example.vendas.repository;

import com.example.vendas.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClientesRepository {

    // private static String INSERT = "INSERT INTO CLIENTES (NOME) VALUES (?)";
    private static String UPDATE = "UPDATE CLIENTES SET NOME = ? WHERE ID = ?";
    private static String SELECT_ALL = "SELECT * FROM CLIENTES";
    private static String DELETE = "DELETE FROM CLIENTES WHERE ID = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Cliente salvar(Cliente cliente){
        entityManager.persist(cliente);
        return cliente;
    }

    @Transactional
    public Cliente atualizar(Cliente cliente){
        entityManager.merge(cliente);
        return cliente;
    }


    public List<Cliente> obterTodos(){
        return entityManager.createQuery(" from Cliente", Cliente.class).getResultList();
    }


    @Transactional
    public void deletar(Cliente cliente){
        if (!entityManager.contains(cliente)){
            entityManager.merge(cliente);
        }
        entityManager.remove(cliente);
    }
    @Transactional
    public void deletar(Integer id){
        Cliente cliente = entityManager.find(Cliente.class, id);
        deletar(cliente);
    }

    @Transactional(readOnly = true)
    public List<Cliente> buscarPorNome(String nome){
        String jpql = "SELECT C FROM CLIENTE C WHERE C.NOME LIKE :NOME";
        TypedQuery<Cliente> query = entityManager.createQuery(jpql,Cliente.class);
        query.setParameter("nome","%" + nome + "%");
        return query.getResultList();
    }

}
