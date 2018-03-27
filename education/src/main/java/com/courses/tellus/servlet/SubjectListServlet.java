package com.courses.tellus.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.tellus.connection.jdbc.ConnectionFactory;
import com.courses.tellus.dao.jdbc.SubjectDao;
import com.courses.tellus.entity.Subject;

@WebServlet(name = "subjectList", value = "/subjectList")
public class SubjectListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final SubjectDao subjectDao = new SubjectDao(ConnectionFactory.getInstance());

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        final Optional<List<Subject>> opt = subjectDao.getAll();
        if (opt.isPresent() && opt.get().size() > 0) {
            final List<Subject> subjectList = opt.get();
            req.setAttribute("subjectList", subjectList);
        } else if (opt.isPresent() && opt.get().size() == 0){
            final String error = "Database is empty!";
            req.setAttribute("emptydb", error);
        } else {
            final String error = "Database error!";
            req.setAttribute("error", error);
        }
        req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/subject_list.jsp").forward(req, resp);
    }
}