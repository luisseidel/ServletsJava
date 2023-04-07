package com.seidelsoft.servlet;

import com.seidelsoft.model.AuthorList;
import com.seidelsoft.util.RegexUtil;
import com.seidelsoft.util.ServletUtil;
import com.seidelsoft.util.XmlUtil;
import com.seidelsoft.webservices.AuthorService;
import jakarta.xml.bind.JAXBException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/authors/*")
public class AuthorServlet extends HttpServlet {

    AuthorService service = new AuthorService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        Long id = RegexUtil.matchId(requestURI, "authors");

        AuthorList authorList = new AuthorList();
        authorList.setAuthors(service.getAutores());

        try {
            String xml = XmlUtil.toXml(authorList);
            ServletUtil.writeXML(resp, xml);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

}