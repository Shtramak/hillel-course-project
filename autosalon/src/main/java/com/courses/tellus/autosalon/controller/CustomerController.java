package com.courses.tellus.autosalon.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

import com.courses.tellus.autosalon.model.Customer;
import com.courses.tellus.autosalon.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/autosalon/customer")
public class CustomerController {
    private final transient CustomerService service;

    @Autowired
    public CustomerController(final CustomerService service) {
        this.service = service;
    }

    /**
     * This method forwards List of customers to listCustomers.jsp.
     *
     * @param model - Model.
     * @return - name of jsp
     */
    @RequestMapping("/list")
    public String listOfCustomers(final Model model) {
        final List<Customer> customers = service.getAll();
        model.addAttribute("customers", customers);
        return "listCustomers";
    }

    /**
     * @param customerId customer id from Http request
     * @param model      - Model.
     * @return - name of jsp
     */
    @RequestMapping("{id:[0-9]+}")
    public String customerById(@PathVariable("id") final String customerId, final Model model) {
        final Optional<Customer> customer = service.getById(Long.valueOf(customerId));
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

    /**
     * @param customerId customer id from Http request
     * @param model      - Model.
     * @return - name of jsp
     */
    @RequestMapping("/delete/{id:[0-9]+}")
    public String deleteById(@PathVariable("id") final String customerId, final Model model) {
        final Optional<Customer> customer = service.getById(Long.valueOf(customerId));
        String viewName;
        if (customer.isPresent()) {
            service.delete(Long.valueOf(customerId));
            model.addAttribute("customer", customer.get());
            viewName = "customerDeletedById";
        } else {
            model.addAttribute("customerId", customerId);
            viewName = "customerNotFound";
        }
        return viewName;
    }

    /**
     * @param model - Model.
     * @return - name of jsp
     */
    @RequestMapping("/add")
    public String addCustomerPage(final Model model) {
        return "addCustomer";
    }

    /**
     * @param request - Http request.
     * @param model   - Model.
     * @return - name of jsp
     */
    @PostMapping("/add")
    public String addCustomerToBd(final HttpServletRequest request, final Model model) throws IOException {
        request.setCharacterEncoding("utf-8");
        final Customer customer = customerFromRequest(request);
        service.insert(customer);
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