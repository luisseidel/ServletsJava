package com.seidelsoft.model;

import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@XmlRootElement(name="author")
public class Author implements Serializable {

    private Long id;
    private String nome;
    private String editora;
    private PhoneNumber phoneNumber;
    private PhoneNumber otherPhoneNumber;

    public Author() {}

    public Author(Long id) {
        this.id = id;
    }

    public Author(Long id, String nome, String editora, PhoneNumber phoneNumber, PhoneNumber otherPhoneNumber) {
        this.id = id;
        this.nome = nome;
        this.editora = editora;
        this.phoneNumber = phoneNumber;
        this.otherPhoneNumber = otherPhoneNumber;
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

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public PhoneNumber getOtherPhoneNumber() {
        return otherPhoneNumber;
    }

    public void setOtherPhoneNumber(PhoneNumber otherPhoneNumber) {
        this.otherPhoneNumber = otherPhoneNumber;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", editora='" + editora + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", otherPhoneNumber=" + otherPhoneNumber +
                '}';
    }
}
