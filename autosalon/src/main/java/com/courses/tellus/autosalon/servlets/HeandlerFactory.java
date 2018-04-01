package com.courses.tellus.autosalon.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HeandlerFactory {
    private final transient Map<String, InternalHeandler> handlers = new HashMap<>();

    public HeandlerFactory() {

        handlers.put("autosalon", new AutosalonHeandler());
    }

    /**
     * Method get.
     * @param request parameter.
     * @param response parameter.
     * @throws IOException exception.
     * @throws ServletException exception.
     */
    public void heandlerGetRequest(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException, ServletException {
        final String handlerKey = handlerPathFromRequest(request);
        if (handlers.containsKey(handlerKey)) {
            final InternalHeandler handler = handlers.get(handlerKey);
            handler.get(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    /**
     * Method post.
     * @param request parameter.
     * @param response parameter.
     * @throws IOException exception.
     * @throws ServletException exception.
     */
    public void heandlerPostRequest(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException, ServletException {
        final String handlerKey = handlerPathFromRequest(request);
        if (handlers.containsKey(handlerKey)) {
            final InternalHeandler handler = handlers.get(handlerKey);
            handler.post(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private String handlerPathFromRequest(final HttpServletRequest request) {
        final String pathInfo = request.getPathInfo();
        return pathInfo.split("/")[1];
    }
}
