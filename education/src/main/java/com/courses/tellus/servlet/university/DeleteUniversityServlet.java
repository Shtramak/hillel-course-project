package com.courses.tellus.servlet.university;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.tellus.config.jdbc.ConnectionFactory;
import com.courses.tellus.dao.jdbc.UniversityDao;

@WebServlet(name = "deleteUniversity", value = "/delete/university")
public class DeleteUniversityServlet extends HttpServlet {

    public static final long serialVersionUID = 1L;
    private transient UniversityDao universityDao;

    @Override
    public void init() throws ServletException {
        universityDao = new UniversityDao(ConnectionFactory.getInstance());
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        final Long uniId = Long.parseLong(req.getParameter("uniId"));
        universityDao.delete(uniId);
        req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/university/universityDeleted.jsp")
                .forward(req, resp);
    }
}