package com.courses.tellus.autosalon.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface InternalHandler {
    void get(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

    void post(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}
