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
import com.courses.tellus.exception.jdbc.DatabaseConnectionException;
import com.courses.tellus.exception.jdbc.EntityIdNotFoundException;
import org.apache.log4j.Logger;

@WebServlet(name = "editSubject", value = "/editSubject")
public class SubjectEditServlet extends HttpServlet {

    public static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(SubjectListServlet.class);
    private static transient SubjectDao subjectDao;

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        subjectDao = new SubjectDao(ConnectionFactory.getInstance());
        final Long subjectId = Long.parseLong(req.getParameter("subjectId"));
        try {
            final Subject subject = subjectDao.getById(subjectId);
            setJspEditAttribute(subject, req);
            req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/subject_edit.jsp").forward(req, resp);
        } catch (EntityIdNotFoundException | DatabaseConnectionException except) {
            LOGGER.debug(except.getMessage(), except);
            req.setAttribute("error", except);
            req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/general_error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        subjectDao = new SubjectDao(ConnectionFactory.getInstance());
        Subject subject = createEntityFromRequest(req);
        try {
            subjectDao.update(subject);
            req.getServletContext().getRequestDispatcher("/subjectList").forward(req, resp);
        } catch (DatabaseConnectionException except) {
            LOGGER.debug(except.getMessage(), except);
            req.setAttribute("error", except);
            req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/general_error.jsp").forward(req, resp);
        }
    }

    private Subject createEntityFromRequest(HttpServletRequest request) {
        final Long subjectId = Long.parseLong(request.getParameter("subjectId"));
        final String name = request.getParameter("name");
        final String description = request.getParameter("description");
        final boolean valid = "Y".equals(request.getParameter("activeRadios"));
        final int day = Integer.parseInt(request.getParameter("day"));
        final int month = Integer.parseInt(request.getParameter("month"));
        final int year = Integer.parseInt(request.getParameter("year"));
        return new Subject(subjectId, name, description, valid, new GregorianCalendar(day, month, year));

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