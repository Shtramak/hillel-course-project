package com.courses.tellus.autosalon.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.courses.tellus.autosalon.config.springjdbc.JdbcTemplatesConfig;
import com.courses.tellus.autosalon.dao.springjdbc.CustomerDao;
import com.courses.tellus.autosalon.model.Customer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@WebServlet("/customer/add")
public class CustomerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Customer customer = customerFromRequest(request);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JdbcTemplatesConfig.class);
        CustomerDao customerDao = context.getBean(CustomerDao.class);
        customerDao.insert(customer);
        request.setAttribute("customer", customer);
        List<Customer> allCustomersAfter = customerDao.getAll();
        request.setAttribute("allCustomersAfter", allCustomersAfter);
        request.getRequestDispatcher("/WEB-INF/jsp/successfulPage.jsp").forward(request, response);
    }

    private Customer customerFromRequest(HttpServletRequest request) {
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
