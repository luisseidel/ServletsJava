package com.seidelsoft.servlet;

import com.seidelsoft.model.Author;
import com.seidelsoft.model.AuthorList;
import com.seidelsoft.util.RegexUtil;
import com.seidelsoft.util.ServletUtil;
import com.seidelsoft.util.XmlUtil;
import com.seidelsoft.webservices.AuthorService;
import jakarta.xml.bind.JAXBException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.stream.Collectors;

@WebServlet("/authors/xml/*")
public class AuthorXMLServlet extends HttpServlet {

    AuthorService service = new AuthorService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = RegexUtil.matchId(req, "authors/xml");
        String xml = "";

        try {
            if (id != null) {
                xml = XmlUtil.toXml(service.getById(id));
            } else {
                AuthorList authorList = new AuthorList();
                authorList.setAuthors(service.getList());
                xml = XmlUtil.toXml(authorList);
            }

            ServletUtil.writeXML(resp, xml);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Author author = getFromRequest(req);
            //service.save(author);
            ServletUtil.writeXML(resp, XmlUtil.toXml(author));
        } catch (JAXBException | ParserConfigurationException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private Author getFromRequest(HttpServletRequest request) throws IOException, ServletException, ParserConfigurationException, SAXException {
        Long id = RegexUtil.matchId(request, "authors/xml");
        String body = getBody(request);

        return createAuthor(id, body);
    }

    private String getBody(HttpServletRequest request) throws IOException {
        return request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
    }

    private Author createAuthor(Long id, String body) throws ParserConfigurationException, IOException, SAXException {
        Author author = null;
        if (id != null) {
            author = service.getById(id);
        }
        if (author == null && id != null) {
            author = new Author(id);
        }

        if (author != null && body != null) {
            Element rootXml = getXmlRootElement(body);

            author.setNome(rootXml.getAttributeNode("nome").getValue());
            author.setEditora(rootXml.getAttributeNode("editora").getValue());
        }
        return author;
    }

    private Element getXmlRootElement(String xmlBody) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(xmlBody)));
        Element rootElement = document.getDocumentElement();

        return rootElement;
    }

}