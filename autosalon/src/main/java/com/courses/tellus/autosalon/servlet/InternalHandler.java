package com.courses.tellus.autosalon.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface InternalHandler {
    /**
     * Method that should invokes in doGet() method of Servlet.
     *
     * @param request  HttpServletRequest from servlet container
     * @param response HttpServletResponse from servlet container
     */
    void get(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

    /**
     * Method that should invokes in doPost() method of Servlet.
     *
     * @param request  HttpServletRequest from servlet container
     * @param response HttpServletResponse from servlet container
     */
    void post(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}
