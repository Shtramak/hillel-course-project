package com.courses.tellus.autosalon.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/autosalon/*")
public class AutosalonServlet extends HttpServlet {

    private transient HeandlerFactory handlerFactory;

    @Override
    public void init() throws ServletException {
        handlerFactory = new HeandlerFactory();
    }

    @Override
    public void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        handlerFactory.heandlerGetRequest(request, response);
    }

    @Override
    public void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        handlerFactory.heandlerPostRequest(request, response);
    }

}
