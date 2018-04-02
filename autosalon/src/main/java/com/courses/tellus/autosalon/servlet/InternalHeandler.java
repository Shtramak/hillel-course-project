package com.courses.tellus.autosalon.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface InternalHeandler {

    /**
     * Method Post.
     * @param request parameter.
     * @param response parameter.
     * @throws ServletException exception.
     * @throws IOException exception.
     */
    void post(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    /**
     * Method Get.
     * @param request parameter.
     * @param response parameter.
     * @throws ServletException exception.
     * @throws IOException exception.
     */
    void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
