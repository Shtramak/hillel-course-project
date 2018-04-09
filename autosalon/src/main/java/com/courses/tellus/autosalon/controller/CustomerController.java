package com.courses.tellus.autosalon.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

import com.courses.tellus.autosalon.dao.springjdbc.CustomerDao;
import com.courses.tellus.autosalon.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/autosalon/customer")
public class CustomerController {
    private final CustomerDao customerDao;

    @Autowired
    public CustomerController(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @RequestMapping("/list")
    public String listOfCustomers(Model model) {
        List<Customer> customers = customerDao.getAll();
        model.addAttribute("customers", customers);
        return "listCustomers";
    }

    @RequestMapping("{id:[0-9]+}")
    public String userById(@PathVariable("id") String customerId, Model model) {
        Optional<Customer> customer = customerDao.getById(Long.valueOf(customerId));
        String viewName;
        if (customer.isPresent()) {
            model.addAttribute("customer", customer.get());
            viewName = "customerById";
        } else {
            model.addAttribute("customerId", customerId);
            viewName = "customerNotFound";
        }
        return viewName;
    }

    @RequestMapping(value = "/add")
    public String addCustomerPage(Model model) {
        return "addCustomer";
    }

    @PostMapping(value = "/add")
    public String addCustomerToBd(HttpServletRequest request, Model model) throws IOException {
        request.setCharacterEncoding("utf-8");
        Customer customer = customerFromRequest(request);
        customerDao.insert(customer);
        model.addAttribute("customer", customer);
        return "successfulAdd";
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