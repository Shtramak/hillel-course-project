package com.courses.tellus.autosalon.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.autosalon.config.springjdbc.JdbcTemplatesConfig;
import com.courses.tellus.autosalon.dao.springjdbc.CustomerDao;
import com.courses.tellus.autosalon.model.Customer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@WebServlet(urlPatterns = "/customer/*")
public class CustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo.equals("/add")) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/addCustomer.jsp");
            dispatcher.forward(request, response);
        }
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JdbcTemplatesConfig.class);
        CustomerDao customerDao = context.getBean(CustomerDao.class);
        if (pathInfo.equals("/list")) {
            request.setCharacterEncoding("UTF-8");
            List<Customer> allCustomersAfter = customerDao.getAll();
            request.setAttribute("allCustomersAfter", allCustomersAfter);
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/listCustomers.jsp").forward(request, response);
        }
        if (Character.isDigit(pathInfo.charAt(1))) {
            Long customerId = Long.valueOf(pathInfo.substring(1));
            request.setAttribute("customerId", customerId);
            Optional<Customer> customerOpt = customerDao.getById(customerId);
            if (customerOpt.isPresent()){
                Customer customer = customerOpt.get();
                request.setAttribute("customer", customer);
                request.getRequestDispatcher("/WEB-INF/jsp/customerById.jsp").forward(request, response);
            }else{
                getServletContext().getRequestDispatcher("/WEB-INF/jsp/customerNotFound.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JdbcTemplatesConfig.class);
        CustomerDao customerDao = context.getBean(CustomerDao.class);
        request.setCharacterEncoding("UTF-8");
        Customer customer = customerFromRequest(request);
        customerDao.insert(customer);
        request.setAttribute("customer", customer);
        request.getRequestDispatcher("/WEB-INF/jsp/successfulAdd.jsp").forward(request, response);
    }

    private Customer customerFromRequest(HttpServletRequest request) throws IOException {
        Long customerId = Long.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String phone = request.getParameter("phone");
        LocalDate birthday = stringToLocalDate(request.getParameter("birthday"));
        Double funds = Double.valueOf(request.getParameter("funds"));
        return new Customer(customerId, name, surname, birthday, phone, funds);
    }

    private static LocalDate stringToLocalDate(String stringDate) {
        String[] dateParameters = stringDate.split("-");
        Integer year = Integer.valueOf(dateParameters[0]);
        Integer month = Integer.valueOf(dateParameters[1]);
        Integer day = Integer.valueOf(dateParameters[2]);
        return LocalDate.of(year, month, day);
    }
}
