package com.seidelsoft.servlet;

import com.seidelsoft.model.Author;
import com.seidelsoft.util.JsonUtil;
import com.seidelsoft.util.RegexUtil;
import com.seidelsoft.util.ServletUtil;
import com.seidelsoft.webservices.AuthorService;
import com.seidelsoft.webservices.Response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/authors/json/*")
public class AuthorJsonServlet extends HttpServlet {

    AuthorService service = new AuthorService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = RegexUtil.matchId(req, "authors/json");
        String json = "";

        if (id != null) {
            json = JsonUtil.toJson(service.getById(id));
        } else {
            List<Author> list = service.getList();
            json = JsonUtil.toJson(list);
        }

        ServletUtil.writeJSON(resp, json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Author author = getFromRequest(req);
        service.save(author);
        ServletUtil.writeJSON(resp, JsonUtil.toJson(author));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = RegexUtil.matchId(req, "authors/json");
        if (id != null) {
            service.delete(id);
            String json = JsonUtil.toJson(Response.Ok("Registro excluído com sucesso!"));
            ServletUtil.writeJSON(resp, json);
        } else {
            resp.sendError(404, "URL Inválida!");
        }
    }

    private Author getFromRequest(HttpServletRequest request) throws IOException, ServletException {
        Long id = RegexUtil.matchId(request, "authors/json");
        String body = getBody(request);

        return service.createAuthor(id, body);
    }

    private String getBody(HttpServletRequest request) throws IOException {
        return request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
    }

}