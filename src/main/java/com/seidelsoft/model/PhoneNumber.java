package com.seidelsoft.model;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="PhoneNumber")
public class PhoneNumber {

    private Long id;
    private String number;

    public PhoneNumber() {
    }

    public PhoneNumber(Long id) {
        this.id = id;
    }

    public PhoneNumber(Long id, String number) {
        this.id = id;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
