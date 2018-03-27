package com.courses.tellus.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.courses.tellus.connection.jdbc.ConnectionFactory;
import com.courses.tellus.dao.jdbc.SubjectDao;

@WebServlet(name = "deleteSubject", value = "/deleteSubject")
public class SubjectDeleteServlet extends HttpServlet {

    public static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SubjectDao subjectDao = new SubjectDao(ConnectionFactory.getInstance());
        Long subjectId = Long.parseLong(req.getParameter("subjectId"));
        subjectDao.delete(subjectId);
        req.getServletContext().getRequestDispatcher("/subjectList").forward(req, resp);
    }
}
