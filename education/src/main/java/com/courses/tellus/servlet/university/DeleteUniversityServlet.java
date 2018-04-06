package com.courses.tellus.servlet.university;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.tellus.connection.jdbc.ConnectionFactory;
import com.courses.tellus.dao.jdbc.UniversityDao;
import com.courses.tellus.exception.jdbc.DatabaseConnectionException;
import org.apache.log4j.Logger;

@WebServlet(name = "deleteUniversity", value = "/deleteUniversity")
public class DeleteUniversityServlet extends HttpServlet {
    public static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(DeleteUniversityServlet.class);
    private transient UniversityDao universityDao;

    @Override
    public void init() throws ServletException {
        universityDao = new UniversityDao(ConnectionFactory.getInstance());
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        final Long uniId = Long.parseLong(req.getParameter("uniId"));
        try {
            universityDao.delete(uniId);
            req.getServletContext().getRequestDispatcher("/WEB-INF/UniversityViews/universityDeleted.html").forward(req, resp);
        } catch (DatabaseConnectionException exception) {
            LOGGER.debug(exception.getCause(), exception);
            req.setAttribute("error", exception);
            req.getServletContext().getRequestDispatcher("/WEB-INF/UniversityViews/listOfUniversities.jsp").forward(req, resp);
        }
    }
}