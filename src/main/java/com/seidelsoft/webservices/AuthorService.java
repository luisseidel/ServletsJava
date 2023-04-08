package com.seidelsoft.webservices;

import com.seidelsoft.model.Author;
import com.seidelsoft.model.PhoneNumber;

import java.util.Arrays;
import java.util.List;

public class AuthorService {

    public List<Author> getAutores() {

        Author a = new Author();
        a.setId(1L);
        a.setNome("Luis Seidel");
        a.setEditora("Teste");
        a.setPhoneNumber(new PhoneNumber(1L, "(55) 9 8800-1122"));
        a.setOtherPhoneNumber(new PhoneNumber(2L, "(55) 0202-3311"));

        Author a2 = new Author();
        a2.setId(2L);
        a2.setNome("2222");
        a2.setEditora("2222");
        a2.setPhoneNumber(new PhoneNumber(1L, "(55) 9 8800-1122"));
        a2.setOtherPhoneNumber(new PhoneNumber(2L, "(55) 0202-3311"));

        Author a3 = new Author();
        a3.setId(3L);
        a3.setNome("3333");
        a3.setEditora("3333");
        a3.setPhoneNumber(new PhoneNumber(1L, "(55) 9 8800-1122"));
        a3.setOtherPhoneNumber(new PhoneNumber(2L, "(55) 0202-3311"));

        Author a4 = new Author();
        a4.setId(4L);
        a4.setNome("44444");
        a4.setEditora("4444");
        a4.setPhoneNumber(new PhoneNumber(1L, "(55) 9 8800-1122"));
        a4.setOtherPhoneNumber(new PhoneNumber(2L, "(55) 0202-3311"));

        return Arrays.asList(a, a2, a3, a4);
    }

    public Author getById(Long id) {
        Author authorReturn = null;
        for (Author a : getAutores()) {
            if (a.getId().equals(id)) {
                authorReturn = a;
            }
        }
        return authorReturn;
    }

    public Object save(Object obj) {

        return new Object();
    }

    public void delete(Long id) {

    }
}
