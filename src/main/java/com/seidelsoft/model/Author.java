package com.seidelsoft.model;

import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@XmlRootElement(name="author")
public class Author implements Serializable {

    private Long id;
    private String nome;
    private String editora;

    public Author() {}

    public Author(Long id) {
        this.id = id;
    }

    public Author(Long id, String nome, String editora) {
        this.id = id;
        this.nome = nome;
        this.editora = editora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", editora='" + editora + '\'' +
                '}';
    }
}
