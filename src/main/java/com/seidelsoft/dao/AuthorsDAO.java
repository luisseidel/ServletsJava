package com.seidelsoft.dao;

import com.seidelsoft.model.Author;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthorsDAO extends BaseDAO<Author> {

    public AuthorsDAO(String tableName) {
        super(tableName);
    }

    @Override
    public Author getById(Long id) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(selectById(id));
            ResultSet result = executeQueryForGet(stmt);

            return createAuthor(result);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Author> getList() {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(getListQuery());
            ResultSet result = executeQueryForGet(stmt);

            return prepareListOf(result);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public Author save(Author a) throws SQLException {
        try {
            Map<String, String> colVals = new HashMap<>();
            colVals.put("nome", a.getNome());
            colVals.put("editora", a.getEditora());

            ResultSet exists = verifyBeforeInsert(colVals);
            if (exists != null && exists.next()) {
                throw new SQLException("Usuário já existe no banco de dados!");
            }

            if (exists != null && !exists.next()) {
                executeInsert(colVals);
                ResultSet result = executeQueryForGet(getConnection().prepareStatement(getMaxId()));
                while (result.next()) {
                    Long id = result.getLong("id");
                    return getById(id);
                }
            }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }

        return null;
    }

    @Override
    public void delete(Long id) throws SQLException {
        try {
            if (getById(id) == null)
                throw new SQLException("Usuário não existe na base de dados!");
            executeDelete(id);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    protected List<Author> prepareListOf(ResultSet result) throws SQLException {
        List<Author> list = new ArrayList<>();

        while (result.next()) {
            Long id = result.getLong("id");
            String nome = result.getString("nome");
            String editora = result.getString("editora");

            list.add(new Author(id, nome, editora));
        }

        return list;
    }

    private Author createAuthor(ResultSet result) throws SQLException {
        while (result.next()) {
            Long id = result.getLong("id");
            String nome = result.getString("nome");
            String editora = result.getString("editora");

            return new Author(id, nome, editora);
        }

        return null;
    }
}
