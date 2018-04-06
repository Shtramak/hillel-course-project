package com.courses.tellus.servlet.subject;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.tellus.connection.jdbc.ConnectionFactory;
import com.courses.tellus.dao.jdbc.SubjectDao;
import com.courses.tellus.exception.jdbc.DatabaseConnectionException;
import org.apache.log4j.Logger;

@WebServlet(name = "deleteSubject", value = "/deleteSubject")
public class SubjectDeleteServlet extends HttpServlet {

    public static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(SubjectDeleteServlet.class);
    private transient SubjectDao subjectDao;

    @Override
    public void init() throws ServletException {
        subjectDao = new SubjectDao(ConnectionFactory.getInstance());
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        final Long subjectId = Long.parseLong(req.getParameter("subjectId"));
        try {
            subjectDao.delete(subjectId);
            req.getServletContext().getRequestDispatcher("/subjectList").forward(req, resp);
        } catch (DatabaseConnectionException except) {
            LOGGER.debug(except.getCause(), except);
            req.setAttribute("error", except);
            req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/general_error.jsp").forward(req, resp);
        }
    }
}
