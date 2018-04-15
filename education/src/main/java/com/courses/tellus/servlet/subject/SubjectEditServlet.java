package com.courses.tellus.servlet.subject;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.tellus.connection.jdbc.ConnectionFactory;
import com.courses.tellus.dao.jdbc.SubjectDao;
import com.courses.tellus.model.Subject;

@WebServlet(name = "editSubject", value = "/update/subject")
public class SubjectEditServlet extends HttpServlet {

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
        final Optional<Subject> subject = subjectDao.getById(subjectId);
        setJspEditAttribute(subject.get(), req);
        req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/subject/subject_edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        final Subject subject = createEntityFromRequest(req);
        subjectDao.update(subject);
        req.getServletContext().getRequestDispatcher("/subjectList").forward(req, resp);
    }

    private Subject createEntityFromRequest(final HttpServletRequest request) {
        final Long subjectId = Long.parseLong(request.getParameter("subjectId"));
        final String name = request.getParameter("name");
        final String description = request.getParameter("description");
        final boolean valid = "Y".equals(request.getParameter("activeRadios"));
        final int day = Integer.parseInt(request.getParameter("day"));
        final int month = Integer.parseInt(request.getParameter("month"));
        final int year = Integer.parseInt(request.getParameter("year"));
        return new Subject(subjectId, name, description, valid, new GregorianCalendar(year, month, day));
    }

    private void setJspEditAttribute(final Subject subject, final HttpServletRequest req) {
        req.setAttribute("subjectId", subject.getSubjectId());
        req.setAttribute("name", subject.getName());
        req.setAttribute("description", subject.getDescription());
        req.setAttribute("valid", subject.isValid());
        final Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date(subject.getDateOfCreation()));
        req.setAttribute("day", calendar.get(Calendar.DAY_OF_MONTH));
        req.setAttribute("month", calendar.get(Calendar.MONTH));
        req.setAttribute("year", calendar.get(Calendar.YEAR));
    }
}