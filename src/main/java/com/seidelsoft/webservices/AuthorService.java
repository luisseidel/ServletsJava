package com.seidelsoft.webservices;

import com.google.gson.JsonObject;
import com.seidelsoft.dao.AuthorsDAO;
import com.seidelsoft.model.Author;
import com.seidelsoft.util.JsonUtil;

import java.sql.SQLException;
import java.util.List;

public class AuthorService {

    private final AuthorsDAO dao;

    public AuthorService() {
        this.dao = new AuthorsDAO("authors");
    }

    public Author getById(Long id) {
        return getDao().getById(id);
    }

    public Author save(Author obj) throws SQLException {
        return getDao().save(obj);
    }

    public List<Author> getList() {
        return getDao().getList();
    }

    public Author createAuthor(Long id, String body) {
        Author author = null;
        if (id != null) {
            author = getById(id);
        }
        if (author == null) {
            author = new Author(id);
        }

        if (body != null) {
            JsonObject jsonObject = JsonUtil.toJsonObject(body, JsonObject.class);
            String nome = jsonObject.get("nome").toString().replace("\"", "");
            String editora = jsonObject.get("editora").toString().replace("\"", "");

            author.setNome(nome);
            author.setEditora(editora);
        }

        return author;
    }

    public void delete(Long id) throws SQLException {
        getDao().delete(id);
    }

    public AuthorsDAO getDao() {
        return dao;
    }
}
