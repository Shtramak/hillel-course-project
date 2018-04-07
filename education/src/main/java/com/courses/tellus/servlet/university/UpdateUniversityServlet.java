package com.courses.tellus.servlet.university;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.tellus.connection.jdbc.ConnectionFactory;
import com.courses.tellus.dao.jdbc.UniversityDao;
import com.courses.tellus.entity.University;
import com.courses.tellus.exception.jdbc.DatabaseConnectionException;
import com.courses.tellus.exception.jdbc.EntityIdNotFoundException;
import org.apache.log4j.Logger;

@WebServlet(name = "updateUniversity", value = "/updateUniversity")
public class UpdateUniversityServlet extends HttpServlet {

    public static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(UpdateUniversityServlet.class);
    private transient UniversityDao universityDao;

    @Override
    public void init() throws ServletException {
        universityDao = new UniversityDao(ConnectionFactory.getInstance());
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
       final Long universityId = Long.parseLong(req.getParameter("uniId"));
        try {
           final University university = universityDao.getById(universityId);
            req.setAttribute("university", university);
            req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/university/updateUniversity.jsp").forward(req, resp);
            } catch (EntityIdNotFoundException | DatabaseConnectionException exception) {
                LOGGER.error(exception.getCause(), exception);
                req.setAttribute("error", exception);
                req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/university/listOfUniversities.jsp")
                        .forward(req, resp);
        }
        }

        @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            final University university = getEntityFromRequest(req);
        universityDao.update(university);
        req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/university/universityUpdated.html")
                .forward(req, resp);
    } catch (DatabaseConnectionException exception) {
            LOGGER.error(exception.getCause(), exception);
            req.setAttribute("error", exception);
            req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/university/listOfUniversities.jsp").forward(req, resp);
    }
    }

    private University getEntityFromRequest(final HttpServletRequest req) {
        final Long universityId = Long.parseLong(req.getParameter("uniId"));
        final String nameOfUniversity = req.getParameter("nameOfUniversity");
        final String address = req.getParameter("address");
        final String specialization = req.getParameter("specialization");

        return new University(universityId, nameOfUniversity, address, specialization);
    }
    }


