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
import java.util.List;
import java.util.Optional;

@WebServlet(name = "listOfUniversities", value = "/listOfUniversities")
public class GetAllUniversitiesServlet extends HttpServlet {

    private static final UniversityDao universityDao = new UniversityDao(ConnectionFactory.getInstance());

    @Override
    public void doGet(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        final Optional<List<University>> optionalUniversities = universityDao.getAll();
        if (optionalUniversities.isPresent() && optionalUniversities.get().size() > 0) {
            final List<University> universityList = optionalUniversities.get();

            req.setAttribute("universityList", universityList);

        } else if (optionalUniversities.isPresent() && optionalUniversities.get().size() == 0){
            final String error = "Universities not found";
            req.setAttribute("dbIsEmpty", error);
        } else {
            final String error = "Database Exception!";
            req.setAttribute("error", error);
        }
        req.getServletContext().getRequestDispatcher("/WEB-INF/UniversityViews/listOfUniversities.jsp").forward(req, resp);
    }
}