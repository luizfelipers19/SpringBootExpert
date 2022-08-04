package com.example.vendas.repository;

import com.example.vendas.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClientesRepository {

    private static String INSERT = "INSERT INTO CLIENTES (NOME) VALUES (?)";
    private static String UPDATE = "UPDATE CLIENTES SET NOME = ? WHERE ID = ?";
    private static String SELECT_ALL = "SELECT * FROM CLIENTES";
    private static String DELETE = "DELETE FROM CLIENTES WHERE ID = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Cliente salvar(Cliente cliente){
        jdbcTemplate.update(INSERT, new Object[]{cliente.getName()});
        return cliente;
    }

    public Cliente atualizar(Cliente cliente){
        jdbcTemplate.update(UPDATE, new Object[]{
                cliente.getName(),
                cliente.getId()
        });
        return cliente;
    }


    public List<Cliente> obterTodos(){
        return jdbcTemplate.query(SELECT_ALL, obterClienteMapper());
    }

    private RowMapper<Cliente> obterClienteMapper() {
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet rs, int i) throws SQLException {
                Integer id = rs.getInt("id");
                String nome = rs.getString("nome");
                return new Cliente(id, nome);
            }
        };
    }

    public void deletar(Cliente cliente){
        deletar(cliente.getId());
    }
    public void deletar(Integer id){
        jdbcTemplate.update(DELETE, new Object[]{id});
    }

    public List<Cliente> buscarPorNome(String nome){
        return jdbcTemplate.query(
                SELECT_ALL.concat(" WHERE NOME LIKE ? "),
                new Object[]{"%" + nome + "%"},
                obterClienteMapper());
    }

}
