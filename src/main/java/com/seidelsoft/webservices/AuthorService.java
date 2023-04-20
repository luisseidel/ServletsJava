package com.seidelsoft.webservices;

import com.seidelsoft.dao.AuthorsDAO;
import com.seidelsoft.model.Author;
import com.seidelsoft.model.PhoneNumber;

import java.util.Arrays;
import java.util.List;

public class AuthorService {

    private final AuthorsDAO dao = new AuthorsDAO();

    public List<Author> getAutores() {
        return dao.getAll();
    }

    public Author getById(Long id) {
        return dao.getById(id);
    }

    public Author save(Author obj) {
        return dao.save(obj);
    }

    public void delete(Long id) {
        dao.delete(id);
    }
}
