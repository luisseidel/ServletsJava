package com.seidelsoft.dao;

import com.seidelsoft.model.Author;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AuthorsDAO extends BaseDAO<Author> {

    public AuthorsDAO(String tableName) {
        super(tableName);
    }

    @Override
    public Author getById(Long id) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(selectById(id));
            ResultSet result = executeQuery(stmt);

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
            ResultSet result = executeQuery(stmt);

            return prepareListOf(result);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public Author save(Author a) {
        try {
            Map<String, String> colVals = new HashMap<>();
            colVals.put("nome", a.getNome());
            colVals.put("editora", a.getEditora());
            PreparedStatement stmt = getConnection().prepareStatement(insertQuery(colVals));
            ResultSet result = executeQuery(stmt);

            return createAuthor(result);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(deleteById(id));
            executeQuery(stmt);
        } catch (SQLException e) {
            e.printStackTrace();
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
