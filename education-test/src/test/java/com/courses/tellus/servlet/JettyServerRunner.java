package com.courses.tellus.servlet;

import com.courses.tellus.servlet.subject.SubjectListServlet;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

public class JettyServerRunner {

    private Server server;
    private int port = 8080;

    public void setUp() throws Exception {

        server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setHost("localhost");
        connector.setPort(8081);
        server.setConnectors(new Connector[]{connector});

        ServletHandler servletHandler = new ServletHandler();
        servletHandler.addServletWithMapping(SubjectListServlet.class, "/subjectList");
        server.setHandler(servletHandler);
    }

    public void startServer() throws Exception {
        server.start();
    }

    public void stopJetty() {
        try {
            server.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Server getServer() {
        return server;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
