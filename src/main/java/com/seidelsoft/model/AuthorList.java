package com.seidelsoft.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name="autores")
public class AuthorList implements Serializable {
    private List<Author> authors;

    @XmlElement(name = "autor")
    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "AuthorList{" +
                "autores=" + authors +
                '}';
    }
}
