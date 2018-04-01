package com.courses.tellus.autosalon.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.tellus.autosalon.config.springjdbc.JdbcTemplatesConfig;
import com.courses.tellus.autosalon.dao.springjdbc.AutosalonDao;
import com.courses.tellus.autosalon.model.Autosalon;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@WebServlet(urlPatterns = "/CreateAutosalon")
public class AutosalonServlet extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        final RequestDispatcher dispatcher =
                request.getRequestDispatcher("WEB-INF/jsp/createautosalon.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException, ServletException {
        final Autosalon autosalon = new Autosalon();
        autosalon.setName(request.getParameter("name"));
        autosalon.setAddress(request.getParameter("address"));
        autosalon.setTelephone(request.getParameter("telephone"));
        final AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(JdbcTemplatesConfig.class);
        context.getBean(AutosalonDao.class).insert(autosalon);
        request.setAttribute("autosalon", autosalon);
        final RequestDispatcher dispatcher =
                request.getRequestDispatcher("WEB-INF/jsp/createautosalon.jsp");
        dispatcher.forward(request, response);
    }
}
