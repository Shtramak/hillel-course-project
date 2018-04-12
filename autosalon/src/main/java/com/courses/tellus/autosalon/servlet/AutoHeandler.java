package com.courses.tellus.autosalon.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.tellus.autosalon.config.springjdbc.JdbcTemplatesConfig;
import com.courses.tellus.autosalon.dao.springjdbc.AutoDao;
import com.courses.tellus.autosalon.model.Auto;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoHeandler implements InternalHandler {

    private final transient AutoDao autoDao;

    public AutoHeandler() {
        final AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(JdbcTemplatesConfig.class);
        autoDao = context.getBean(AutoDao.class);
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
        final Auto auto = requestAuto(request);
        autoDao.insert(auto);
        request.setAttribute("auto", auto);
        response.sendRedirect(request.getContextPath() + "listAuto");
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
        if (path.equals("listAuto")) {
            final List<Auto> autoList = autoDao.getAll();
            request.setAttribute("autoList", autoList);
            final RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/listAuto.jsp");
            dispatcher.forward(request, response);
        }
        if (path.equals("createAuto")) {
            final RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/createAuto.jsp");
            dispatcher.forward(request, response);
        }
    }

    private String requestPathWithoutContext(final HttpServletRequest request) {
        final String fullPath = request.getPathInfo();
        return fullPath.split("/")[2];
    }

    private Auto requestAuto(final HttpServletRequest request) {
        final Long idAuto = 0L;
        final String brand = request.getParameter("brand");
        final String model = request.getParameter("model");
        final Integer manufactYear = Integer.parseInt(request.getParameter("manufactYear"));
        final String producerCountry = request.getParameter("producerCountry");
        final BigDecimal price = BigDecimal.valueOf(Long.parseLong(request.getParameter("price")));
        return new Auto(idAuto, brand, model, manufactYear, producerCountry, price);
    }
}
