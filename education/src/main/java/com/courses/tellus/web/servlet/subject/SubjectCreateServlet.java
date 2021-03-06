package com.courses.tellus.web.servlet.subject;

import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.tellus.config.jdbc.ConnectionFactory;
import com.courses.tellus.entity.model.Subject;
import com.courses.tellus.persistence.dao.jdbc.SubjectDao;

@WebServlet(name = "createSubject", value = "/create/subject")
public class SubjectCreateServlet extends HttpServlet {

    public static final long serialVersionUID = 1L;
    private transient SubjectDao subjectDao;

    @Override
    public void init() throws ServletException {
        subjectDao = new SubjectDao(ConnectionFactory.getInstance());
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/servlets/subject/subject_create.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        final Subject subject = createEntityFromRequest(req);
        subjectDao.insert(subject);
        req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/servlets/subject/subject_create.jsp")
                .forward(req, resp);
    }

    private Subject createEntityFromRequest(final HttpServletRequest request) {
        final String name = request.getParameter("name");
        final String description = request.getParameter("description");
        final boolean valid = "Y".equals(request.getParameter("activeRadios"));
        final int day = Integer.parseInt(request.getParameter("day"));
        final int month = Integer.parseInt(request.getParameter("month"));
        final int year = Integer.parseInt(request.getParameter("year"));
        return new Subject(name, description, valid, LocalDate.of(year, month, day));
    }
}