package com.courses.tellus.autosalon.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class HandlerFactory {
    private static Map<String, InternalHandler> handlerMap = new HashMap<>();

    static {
        handlerMap.put("customer", CustomerHandler.getInstance());
    }

    private HandlerFactory() {
    }

    /**
     * Method handles get method of InternalHandler from map or returns 404 page.
     *
     * @param request  HttpServletRequest from servlet container
     * @param response HttpServletResponse from servlet container
     */
    public static void handleGetRequest(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException, ServletException {
        final String handlerKey = handlerPathFromRequest(request);
        if (handlerMap.containsKey(handlerKey)) {
            final InternalHandler handler = handlerMap.get(handlerKey);
            handler.get(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    /**
     * Method handles post method of InternalHandler from map or returns 404 page.
     *
     * @param request  HttpServletRequest from servlet container
     * @param response HttpServletResponse from servlet container
     */
    public static void handlePostRequest(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException, ServletException {
        final String handlerKey = handlerPathFromRequest(request);
        if (handlerMap.containsKey(handlerKey)) {
            final InternalHandler handler = handlerMap.get(handlerKey);
            handler.post(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private static String handlerPathFromRequest(final HttpServletRequest request) {
        final String pathInfo = request.getRequestURI();
        return pathInfo.split("/")[2];
    }
}