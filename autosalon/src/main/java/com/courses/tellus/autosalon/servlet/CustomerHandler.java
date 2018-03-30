package com.courses.tellus.autosalon.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.tellus.autosalon.config.springjdbc.JdbcTemplatesConfig;
import com.courses.tellus.autosalon.dao.springjdbc.CustomerDao;
import com.courses.tellus.autosalon.model.Customer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CustomerHandler implements InternalHandler {
    private final CustomerDao customerDao;

    public CustomerHandler() {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JdbcTemplatesConfig.class);
        customerDao = context.getBean(CustomerDao.class);
    }

    @Override
    public void get(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        final String path = requestPathWithoutContext(request);
        if (path.equals("add")) {
            request.getRequestDispatcher("/WEB-INF/jsp/addCustomer.jsp").forward(request, response);
        } else if (path.equals("list")) {
            forwardToListCustomers(customerDao, request, response);
        } else if (path.matches("\\d+")) {
            forwardToCustomerById(customerDao, request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/jsp/customerNotFound.jsp").forward(request, response);
        }
    }

    @Override
    public void post(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        final Customer customer = customerFromRequest(request);
        customerDao.insert(customer);
        request.setAttribute("customer", customer);
        request.getRequestDispatcher("/WEB-INF/jsp/successfulAdd.jsp").forward(request, response);

    }

    private void forwardToListCustomers(CustomerDao customerDao,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws ServletException, IOException {
        final List<Customer> customers = customerDao.getAll();
        request.setAttribute("customers", customers);
        request.getRequestDispatcher("/WEB-INF/jsp/listCustomers.jsp").forward(request, response);
    }

    private void forwardToCustomerById(CustomerDao customerDao,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws ServletException, IOException {
        String path = requestPathWithoutContext(request);
        final Long customerId = Long.valueOf(path.substring(1));
        request.setAttribute("customerId", customerId);
        final Optional<Customer> customerOpt = customerDao.getById(customerId);
        if (customerOpt.isPresent()) {
            final Customer customer = customerOpt.get();
            request.setAttribute("customer", customer);
            request.getRequestDispatcher("/WEB-INF/jsp/customerById.jsp").forward(request, response);
        }
    }


    private String requestPathWithoutContext(HttpServletRequest request) {
        final String fullPath = request.getPathInfo();
        return fullPath.split("/")[2];
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
