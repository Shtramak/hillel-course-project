package com.courses.tellus.servlet.UniversityServlets;

import com.courses.tellus.connection.jdbc.ConnectionFactory;
import com.courses.tellus.dao.jdbc.UniversityDao;
import com.courses.tellus.entity.University;
import org.h2.tools.RunScript;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;

@WebServlet(name = "createUniversity", value = "/createUniversity")
public class CreateUniversityServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream("database/h2/init_h2.sql");
        try {
            RunScript.execute(ConnectionFactory.getInstance().getConnection(), new InputStreamReader(resourceAsStream));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/WEB-INF/UniversityViews/addUniversity.jsp").forward(req, resp);
    }

    @Override
    public void doPost(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        final UniversityDao universityDao =  new UniversityDao(ConnectionFactory.getInstance());

        final String nameOfUniversity = req.getParameter("nameOfUniversity");
        final String address = req.getParameter("address");
        final String specialization = req.getParameter("specialization");

        final University university = new University();
        university.setNameOfUniversity(nameOfUniversity);
        university.setAddress(address);
        university.setSpecialization(specialization);
        universityDao.insert(university);
        req.getServletContext().getRequestDispatcher("/WEB-INF/UniversityViews/universityAdded.html").forward(req, resp);
    }
}