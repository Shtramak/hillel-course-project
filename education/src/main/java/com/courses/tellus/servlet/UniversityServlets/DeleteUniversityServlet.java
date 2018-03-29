package com.courses.tellus.servlet.UniversityServlets;

import com.courses.tellus.connection.jdbc.ConnectionFactory;
import com.courses.tellus.dao.jdbc.UniversityDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "deleteUniversity", value = "/deleteUniversity")
public class DeleteUniversityServlet extends HttpServlet {

    public static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UniversityDao universityDao = new UniversityDao(ConnectionFactory.getInstance());
        Long universityId = Long.parseLong(req.getParameter("univer_Id"));
        universityDao.delete(universityId);
        req.getServletContext().getRequestDispatcher("/WEB-INF/UniversityViews/universityDeleted.html").forward(req, resp);
    }
}
