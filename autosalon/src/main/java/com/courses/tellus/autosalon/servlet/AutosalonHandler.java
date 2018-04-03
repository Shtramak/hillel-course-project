package com.courses.tellus.autosalon.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.tellus.autosalon.config.springjdbc.JdbcTemplatesConfig;
import com.courses.tellus.autosalon.dao.springjdbc.AutosalonDao;
import com.courses.tellus.autosalon.model.Autosalon;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutosalonHandler implements InternalHandler {

    private final transient AutosalonDao autosalonDao;

    public AutosalonHandler() {
        final AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(JdbcTemplatesConfig.class);
        autosalonDao = context.getBean(AutosalonDao.class);
    }

    /**
     * Implementation method post for auto.
     * @param request parameter.
     * @param response parameter.
     * @throws ServletException exeption.
     * @throws IOException exeption.
     */
    @Override
    public void post(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        final Autosalon autosalon = requestAutosalon(request);
        autosalonDao.insert(autosalon);
        request.setAttribute("autosalon", autosalon);
        request.getRequestDispatcher("/WEB-INF/jsp/createautosalon.jsp").forward(request, response);
    }

    /**
     * Implementation method get for auto.
     * @param request parameter.
     * @param response parameter.
     * @throws ServletException exeption.
     * @throws IOException exeption.
     */
    @Override
    public void get(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        final String path = requestPathWithoutContext(request);
        if (path.equals("allAutosalon")) {
            final List<Autosalon> autosalonList = autosalonDao.getAll();
            request.setAttribute("autosalon", autosalonList);
            final RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/allAutosalon.jsp");
            dispatcher.forward(request, response);
        }
        if (path.equals("createautosalon")) {
            final RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/createautosalon.jsp");
            dispatcher.forward(request, response);
        }
    }

    private String requestPathWithoutContext(final HttpServletRequest request) {
        final String fullPath = request.getPathInfo();
        return fullPath.split("/")[2];
    }

    private Autosalon requestAutosalon(final HttpServletRequest request) {
        final String name = request.getParameter("name");
        final String address = request.getParameter("address");
        final String telephone = request.getParameter("telephone");
        return new Autosalon(name, address, telephone);
    }
}
