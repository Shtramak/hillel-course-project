package com.courses.tellus.web.servlet.university;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.tellus.config.jdbc.ConnectionFactory;
import com.courses.tellus.entity.model.University;
import com.courses.tellus.persistence.dao.jdbc.UniversityDao;

@WebServlet(name = "listOfUniversities", value = "/list/universities")
public class GetAllUniversitiesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private transient UniversityDao universityDao;

    @Override
    public void init() {
        universityDao = new UniversityDao(ConnectionFactory.getInstance());
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        final List<University> universities = universityDao.getAll();
        if (universities.size() > 0) {
            req.setAttribute("universityList", universities);
        } else {
            final String message = "Database is empty!";
            req.setAttribute("dbIsEmpty", message);
        }
        req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/servlets/university/listOfUniversities.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        this.doGet(req, resp);
    }
}