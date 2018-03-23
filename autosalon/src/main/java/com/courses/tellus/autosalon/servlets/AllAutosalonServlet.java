package com.courses.tellus.autosalon.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.tellus.autosalon.config.springjdbc.JdbcTemplatesConfig;
import com.courses.tellus.autosalon.model.Autosalon;
import com.courses.tellus.autosalon.service.AutosalonService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@WebServlet(urlPatterns = "/allAutosalon")
public class AllAutosalonServlet extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        final AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(JdbcTemplatesConfig.class);
        final List<Autosalon> autosalon = context.getBean(AutosalonService.class).getAll();
        request.setAttribute("autosalon", autosalon);
        final RequestDispatcher dispatcher =
                request.getRequestDispatcher("WEB-INF/jsp/allAutosalon.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException, ServletException {
    }
}
