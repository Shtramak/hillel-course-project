package com.courses.tellus.web.servlet.subject;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.tellus.config.jdbc.ConnectionFactory;
import com.courses.tellus.persistence.dao.jdbc.SubjectDao;

@WebServlet(name = "deleteSubject", value = "/delete/subject")
public class SubjectDeleteServlet extends HttpServlet {

    public static final long serialVersionUID = 1L;
    private transient SubjectDao subjectDao;

    @Override
    public void init() throws ServletException {
        subjectDao = new SubjectDao(ConnectionFactory.getInstance());
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        final Long subjectId = Long.parseLong(req.getParameter("subjectId"));
        subjectDao.delete(subjectId);
        req.getServletContext().getRequestDispatcher("/list/subject").forward(req, resp);
    }
}
