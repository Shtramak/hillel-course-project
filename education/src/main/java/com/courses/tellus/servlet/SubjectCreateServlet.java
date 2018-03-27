package com.courses.tellus.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.tellus.connection.jdbc.ConnectionFactory;
import com.courses.tellus.dao.jdbc.SubjectDao;
import com.courses.tellus.entity.Subject;
import org.h2.tools.RunScript;

@WebServlet(name = "createSubject", value = "/createSubject")
public class SubjectCreateServlet extends HttpServlet {

    public static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream("database/h2/init_h2.sql");
        try {
            RunScript.execute(ConnectionFactory.getInstance().getConnection(), new InputStreamReader(resourceAsStream));
        } catch (SQLException except) {
            System.out.println("fuck");
        }
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/subject_create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        final SubjectDao subjectDao =  new SubjectDao(ConnectionFactory.getInstance());

        final String name = req.getParameter("name");
        final String description = req.getParameter("description");
        final boolean valid = "Y".equals(req.getParameter("activeRadios"));
        final int day = Integer.parseInt(req.getParameter("day"));
        final int month = Integer.parseInt(req.getParameter("month"));
        final int year = Integer.parseInt(req.getParameter("year"));
        final Subject subject = new Subject();
        subject.setName(name);
        subject.setDescription(description);
        subject.setValid(valid);
        subject.setDateOfCreation(day, month, year);
        subjectDao.insert(subject);
        req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/subject_create.jsp").forward(req, resp);
    }
}