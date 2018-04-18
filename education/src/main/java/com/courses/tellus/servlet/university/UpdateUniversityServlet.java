package com.courses.tellus.servlet.university;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.tellus.config.jdbc.ConnectionFactory;
import com.courses.tellus.dao.jdbc.UniversityDao;
import com.courses.tellus.model.University;

@WebServlet(name = "updateUniversity", value = "/update/university")
public class UpdateUniversityServlet extends HttpServlet {

    public static final long serialVersionUID = 1L;
    private transient UniversityDao universityDao;

    @Override
    public void init() throws ServletException {
        universityDao = new UniversityDao(ConnectionFactory.getInstance());
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        final Long universityId = Long.parseLong(req.getParameter("uniId"));
        final Optional<University> university = universityDao.getById(universityId);
        req.setAttribute("university", university.get());
        req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/servlets/university/updateUniversity.jsp")
                .forward(req, resp);
    }

        @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        final University university = getEntityFromRequest(req);
        universityDao.update(university);
        req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/servlets/university/universityUpdated.jsp")
                .forward(req, resp);
        }

    private University getEntityFromRequest(final HttpServletRequest req) {
        final Long universityId = Long.parseLong(req.getParameter("uniId"));
        final String nameOfUniversity = req.getParameter("nameOfUniversity");
        final String address = req.getParameter("address");
        final String specialization = req.getParameter("specialization");

        return new University(universityId, nameOfUniversity, address, specialization);
    }
}


