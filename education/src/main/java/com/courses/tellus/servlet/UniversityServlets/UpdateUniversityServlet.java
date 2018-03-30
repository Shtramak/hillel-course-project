package com.courses.tellus.servlet.UniversityServlets;

import com.courses.tellus.connection.jdbc.ConnectionFactory;
import com.courses.tellus.dao.jdbc.UniversityDao;
import com.courses.tellus.entity.University;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "updateUniversity", value = "/updateUniversity")
public class UpdateUniversityServlet extends HttpServlet {

    private static transient UniversityDao universityDao;

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        universityDao = new UniversityDao(ConnectionFactory.getInstance());
        Long universityId = Long.parseLong(req.getParameter("univer_id"));
       Optional <University> university = universityDao.getById(universityId);
        req.setAttribute("university", university);

        req.getServletContext().getRequestDispatcher("/WEB-INF/UniversityViews/updateUniversity.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        universityDao = new UniversityDao(ConnectionFactory.getInstance());
        final Long universityId = Long.parseLong(req.getParameter("univer_id"));
        final String nameOfUniversity = req.getParameter("nameOfUniversity");
        final String address = req.getParameter("address");
        final String specialization = req.getParameter("specializations");

        final University university = new University();

        university.setUniId(universityId);
        university.setNameOfUniversity(nameOfUniversity);
        university.setAddress(address);
        university.setSpecialization(specialization);
        universityDao.update(university);
        req.getServletContext().getRequestDispatcher("/WEB-INF/UniversityViews/universityUpdated.html").forward(req, resp);
    }}
