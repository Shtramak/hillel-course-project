package com.courses.tellus.servlet.subject;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.tellus.config.jdbc.ConnectionFactory;
import com.courses.tellus.dao.jdbc.SubjectDao;
import com.courses.tellus.model.Subject;

@WebServlet(name = "subjectList", value = "/list/subject")
public class SubjectListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private transient SubjectDao subjectDao;

    @Override
    public void init() throws ServletException {
        subjectDao = new SubjectDao(ConnectionFactory.getInstance());
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        final List<Subject> subjectList = subjectDao.getAll();
        if (subjectList.size() > 0) {
            req.setAttribute("subjectList", subjectList);
        } else {
            final String message = "Database is empty!";
            req.setAttribute("emptydb", message);
        }
        req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/subject/subject_list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        this.doGet(req, resp);
    }
}