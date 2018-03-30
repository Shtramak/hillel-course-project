package com.courses.tellus.autosalon.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandlerFactory {
    private static Map<String, InternalHandler> handlerMap = new HashMap<>();

    public HandlerFactory() {
        handlerMap.put("customer", new CustomerHandler());
    }

    public void handleGetRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String hanlerKey = handlerPathFromRequest(request);
        if (handlerMap.containsKey(hanlerKey)) {
            InternalHandler handler = handlerMap.get(hanlerKey);
            handler.get(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    public void handlePostRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String hanlerKey = handlerPathFromRequest(request);
        if (handlerMap.containsKey(hanlerKey)) {
            InternalHandler handler = handlerMap.get(hanlerKey);
            handler.post(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private String handlerPathFromRequest(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        return pathInfo.split("/")[1];
    }
}
