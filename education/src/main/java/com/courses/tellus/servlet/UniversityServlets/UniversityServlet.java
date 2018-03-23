package com.courses.tellus.servlet.UniversityServlets;

import com.courses.tellus.dao.spring.jdbc.UniversityDao;
import com.courses.tellus.entity.University;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "UniversityServlet", urlPatterns = {"/universities"})
public class UniversityServlet extends HttpServlet {

    private UniversityDao universityDao;

    private static final String ADD_UNIVERSITY_VIEW = "addUniversity.jsp";
    private static final String UPDATE_UNIVERSITY_VIEW = "updateUniversity.jsp";
    private static final String LIST_OF_UNIVERSITIES = "listOfUniversities.jsp";

    private static final String UNIVERSITY_ADDED_VIEW = "universityAdded.html";
    private static final String UNIVERSITY_DELETED_VIEW = "universityDeleted.html";
    private static final String UNIVERSITY_UPDATED_VIEW = "universityUpdated.html";

    private static final String DELETE_ACTION = "deleteUniversity";
    private static final String ADD_ACTION = "addUniversity";
    private static final String UPDATE_ACTION = "updateUniversity";
    private static final String LIST_ACTION = "listOfUniversities";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String path = "";

        String action = request.getParameter("action");

        if (DELETE_ACTION.equals(action)) {
            Long univer_id = Long.parseLong(request.getParameter("univer_id"));
            universityDao.delete(univer_id);

            path = UNIVERSITY_DELETED_VIEW;
        } else if (ADD_ACTION.equals(action)) {

            path = ADD_UNIVERSITY_VIEW;

        } else if (UPDATE_ACTION.equals(action)) {

            Long univer_id = Long.parseLong(request.getParameter("univer_id"));
            Optional<University> university = universityDao.getById(univer_id);
            request.setAttribute("university", university);
            path = UPDATE_UNIVERSITY_VIEW;

        } else if (LIST_ACTION.equals(action)) {

            List<University> universities = universityDao.getAll();
            request.setAttribute("universities", universities);
            path = LIST_OF_UNIVERSITIES;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = "";

        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");

        if (ADD_ACTION.equals(action)) {

            University university = new University();
            university.setNameOfUniversity(request.getParameter("nameOfUniversity"));
            university.setAddress(request.getParameter("address"));
            university.setSpecialization(request.getParameter("specialization"));

            universityDao.insert(university);
            path = UNIVERSITY_ADDED_VIEW;

        } else if (UPDATE_ACTION.equals(action)) {

            University university = new University();
            university.setUniId(Long.parseLong(request.getParameter("univer_id")));
            university.setNameOfUniversity(request.getParameter("nameOfUniversity"));
            university.setAddress(request.getParameter("address"));
            university.setSpecialization(request.getParameter("specialization"));

            universityDao.update(university);
            path = UNIVERSITY_UPDATED_VIEW;
        }
        response.sendRedirect(path);
    }
}
