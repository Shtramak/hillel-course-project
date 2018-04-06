package com.courses.tellus.servlet.university;

import com.courses.tellus.connection.jdbc.ConnectionFactory;
import com.courses.tellus.dao.jdbc.UniversityDao;
import com.courses.tellus.entity.University;
import com.courses.tellus.exception.jdbc.DatabaseConnectionException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "listOfUniversities", value = "/listOfUniversities")
public class GetAllUniversitiesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(GetAllUniversitiesServlet.class);
    private transient UniversityDao universityDao;

    @Override
    public void init() {
        universityDao = new UniversityDao(ConnectionFactory.getInstance());
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            final List<University> listOfUniversities = universityDao.getAll();
            if (listOfUniversities.size() > 0) {
                req.setAttribute("universityList", listOfUniversities);
            } else {
                final String message = "Database is empty!";
                req.setAttribute("dbIsEmpty", message);
            }
        } catch (DatabaseConnectionException exception) {
            LOGGER.debug(exception.getMessage(), exception);
            req.setAttribute("error", exception);
            req.getServletContext().getRequestDispatcher("/WEB-INF/UniversityViews/listOfUniversities.jsp").forward(req, resp);
        }
        req.getServletContext().getRequestDispatcher("/WEB-INF/UniversityViews/listOfUniversities.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        this.doGet(req, resp);
    }
}