package com.courses.tellus.autosalon.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.tellus.autosalon.config.springjdbc.JdbcTemplatesConfig;
import com.courses.tellus.autosalon.dao.springjdbc.CustomerDao;
import com.courses.tellus.autosalon.model.Customer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@WebServlet(urlPatterns = "/customer/*")
public class CustomerServlet extends HttpServlet {
    @Override
    @SuppressWarnings("PMD.NcssCount")
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        final String pathInfo = request.getPathInfo();
        request.setCharacterEncoding("UTF-8");
        if (pathInfo.equals("/add")) {
            final RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/addCustomer.jsp");
            dispatcher.forward(request, response);
        }
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JdbcTemplatesConfig.class);
        final CustomerDao customerDao = context.getBean(CustomerDao.class);
        if (pathInfo.equals("/list")) {
            final List<Customer> allCustomersAfter = customerDao.getAll();
            request.setAttribute("allCustomersAfter", allCustomersAfter);
            request.getRequestDispatcher("/WEB-INF/jsp/listCustomers.jsp").forward(request, response);
        }
        if (Character.isDigit(pathInfo.charAt(1))) {
            final Long customerId = Long.valueOf(pathInfo.substring(1));
            request.setAttribute("customerId", customerId);
            final Optional<Customer> customerOpt = customerDao.getById(customerId);
            if (customerOpt.isPresent()) {
                final Customer customer = customerOpt.get();
                request.setAttribute("customer", customer);
                request.getRequestDispatcher("/WEB-INF/jsp/customerById.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/WEB-INF/jsp/customerNotFound.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        final AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(JdbcTemplatesConfig.class);
        final CustomerDao customerDao = context.getBean(CustomerDao.class);
        request.setCharacterEncoding("UTF-8");
        final Customer customer = customerFromRequest(request);
        customerDao.insert(customer);
        request.setAttribute("customer", customer);
        request.getRequestDispatcher("/WEB-INF/jsp/successfulAdd.jsp").forward(request, response);
    }

    private Customer customerFromRequest(final HttpServletRequest request) throws IOException {
        final Long customerId = Long.valueOf(request.getParameter("id"));
        final String name = request.getParameter("name");
        final String surname = request.getParameter("surname");
        final String phone = request.getParameter("phone");
        final LocalDate birthday = stringToLocalDate(request.getParameter("birthday"));
        final Double funds = Double.valueOf(request.getParameter("funds"));
        return new Customer(customerId, name, surname, birthday, phone, funds);
    }

    private static LocalDate stringToLocalDate(final String stringDate) {
        final String[] dateParameters = stringDate.split("-");
        final Integer year = Integer.valueOf(dateParameters[0]);
        final Integer month = Integer.valueOf(dateParameters[1]);
        final Integer day = Integer.valueOf(dateParameters[2]);
        return LocalDate.of(year, month, day);
    }
}