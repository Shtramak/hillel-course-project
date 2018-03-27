package com.courses.tellus.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;

import com.courses.tellus.connection.jdbc.ConnectionFactory;
import com.courses.tellus.dao.jdbc.SubjectDao;
import com.courses.tellus.entity.Subject;

@WebServlet(name = "editSubject", value = "/editSubject")
public class SubjectEditServlet extends HttpServlet {

    public static final long serialVersionUID = 1L;
    private static transient SubjectDao subjectDao;

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        subjectDao = new SubjectDao(ConnectionFactory.getInstance());
        final Long subjectId = Long.parseLong(req.getParameter("subjectId"));
        final Optional<Subject> opt = subjectDao.getById(subjectId);
        if (opt.isPresent()) {
            final Subject subject = opt.get();
            setJspEditAttribute(subject, req);
        } else {
            final String error = "Input id not exist";
            req.setAttribute("error", error);
        }
        req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/subject_edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        subjectDao = new SubjectDao(ConnectionFactory.getInstance());
        final Long subjectId = Long.parseLong(req.getParameter("subjectId"));
        final String name = req.getParameter("name");
        final String description = req.getParameter("description");
        final boolean valid = "Y".equals(req.getParameter("activeRadios"));
        final int day = Integer.parseInt(req.getParameter("day"));
        final int month = Integer.parseInt(req.getParameter("month"));
        final int year = Integer.parseInt(req.getParameter("year"));
        final Subject subject = new Subject();
        subject.setSubjectId(subjectId);
        subject.setName(name);
        subject.setDescription(description);
        subject.setValid(valid);
        subject.setDateOfCreation(day, month, year);
        subjectDao.update(subject);
        req.getServletContext().getRequestDispatcher("/subjectList").forward(req, resp);
    }

    private void setJspEditAttribute(final Subject subject, final HttpServletRequest req) {
        req.setAttribute("subjectId", subject.getSubjectId());
        req.setAttribute("name", subject.getName());
        req.setAttribute("description", subject.getDescription());
        if (subject.isValid()) {
            req.setAttribute("setYes", "checked");
        } else {
            req.setAttribute("setNo", "checked");
        }
        final Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date(subject.getDateOfCreation()));
        req.setAttribute("day", (calendar.get(Calendar.DAY_OF_MONTH)));
        req.setAttribute("month", (calendar.get(Calendar.MONTH)));
        req.setAttribute("year", (calendar.get(Calendar.YEAR)));
    }
}