package com.courses.tellus.autosalon.dao.servlets;

import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.HttpURLConnection;
import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AutosalonServletTest {

    private static Server server;

    @BeforeAll
    public static void startJetty() throws Exception {
        server = new Server(8080);
        ServletContextHandler context = new ServletContextHandler();
        ServletHolder defaultServ = new ServletHolder("default", DefaultServlet.class);
        defaultServ.setInitParameter("resourceBase",System.getProperty("user.dir"));
        defaultServ.setInitParameter("dirAllowed","true");
        context.addServlet(defaultServ,"/");
        server.setHandler(context);
        server.start();
    }

    @AfterAll
    public static void stopJetty() throws Exception {
        server.stop();
    }

    @Test
    public void testLocalhost() throws Exception {
        HttpURLConnection http = (HttpURLConnection) new URL("http://localhost:8080").openConnection();
        http.connect();
        assertThat("Response Code", http.getResponseCode(), is(HttpStatus.OK_200));
    }
}
