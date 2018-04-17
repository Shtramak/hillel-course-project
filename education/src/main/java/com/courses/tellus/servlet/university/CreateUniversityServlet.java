package com.courses.tellus.servlet.university;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.tellus.config.jdbc.ConnectionFactory;
import com.courses.tellus.dao.jdbc.UniversityDao;
import com.courses.tellus.model.jdbc.University;

@WebServlet(name = "createUniversity", value = "/create/university")
public class CreateUniversityServlet extends HttpServlet {

    public static final long serialVersionUID = 1L;
    private transient UniversityDao universityDao;

    @Override
    public void init() throws ServletException {
        universityDao = new UniversityDao(ConnectionFactory.getInstance());
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/university/addUniversity.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        universityDao.insert(getUniversityFromRequest(req));
        req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/university/universityAdded.jsp")
                .forward(req, resp);
    }

    private University getUniversityFromRequest(final HttpServletRequest req) {
        final String nameOfUniversity = req.getParameter("nameOfUniversity");
        final String address = req.getParameter("address");
        final String specialization = req.getParameter("specialization");
        final University university = new University();
        university.setNameOfUniversity(nameOfUniversity);
        university.setAddress(address);
        university.setSpecialization(specialization);
        return university;

    }
}