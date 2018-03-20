package com.courses.tellus.autosalon.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.tellus.autosalon.model.Autosalon;

@WebServlet(urlPatterns = "/autosalonAutorization")
public class AutosalonServlet extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/forma.jsp").forward(req, resp);
    }

    @Override
    public void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException, ServletException {
        final String login = request.getParameter("login");
        final String password = request.getParameter("password");
        final Autosalon autosalon = new Autosalon();
        autosalon.setName(login);
        autosalon.setTelephone(password);
        request.setAttribute("autosalon", autosalon);
        request.getRequestDispatcher("WEB-INF/jsp/forma.jsp").forward(request, response);

    }

}
