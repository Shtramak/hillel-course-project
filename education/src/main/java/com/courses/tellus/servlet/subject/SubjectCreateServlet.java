package com.courses.tellus.servlet.subject;

import java.io.IOException;
import java.util.GregorianCalendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.tellus.connection.jdbc.ConnectionFactory;
import com.courses.tellus.dao.jdbc.SubjectDao;
import com.courses.tellus.entity.Subject;
import com.courses.tellus.exception.jdbc.DatabaseConnectionException;
import org.apache.log4j.Logger;

@WebServlet(name = "createSubject", value = "/createSubject")
public class SubjectCreateServlet extends HttpServlet {

    public static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(SubjectCreateServlet.class);
    private transient SubjectDao subjectDao;

    @Override
    public void init() throws ServletException {
        subjectDao = new SubjectDao(ConnectionFactory.getInstance());
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/subject_create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            final Subject subject = createEntityFromRequest(req);
            subjectDao.insert(subject);
            req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/subject_create.jsp").forward(req, resp);
        } catch (DatabaseConnectionException except) {
            LOGGER.error(except.getCause(), except);
            req.setAttribute("error", except.getMessage());
            req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/general_error.jsp").forward(req, resp);
        }
    }

    private Subject createEntityFromRequest(final HttpServletRequest request) {
        final String name = request.getParameter("name");
        final String description = request.getParameter("description");
        final boolean valid = "Y".equals(request.getParameter("activeRadios"));
        final int day = Integer.parseInt(request.getParameter("day"));
        final int month = Integer.parseInt(request.getParameter("month"));
        final int year = Integer.parseInt(request.getParameter("year"));
        return new Subject(name, description, valid, new GregorianCalendar(year, month, day));

    }
}