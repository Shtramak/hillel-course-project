package com.courses.tellus.autosalon.servlet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.net.HttpURLConnection;
import java.net.URI;

import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

//@ContextConfiguration(classes = {JdbcTemplatesConfig.class, CustomerDao.class})
//@ExtendWith(SpringExtension.class)
public class CustomerServletIntegrationTest {
    private static Server server;
    private static URI serverUri;

    @BeforeAll
    public static void startJetty() throws Exception
    {
        // Create Server
        server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(0); // auto-bind to available port
        server.addConnector(connector);

        ServletContextHandler context = new ServletContextHandler();
        ServletHolder defaultServ = new ServletHolder(new CustomerServlet());
//        defaultServ.setInitParameter("resourceBase",System.getProperty("user.dir"));
//        defaultServ.setInitParameter("dirAllowed","true");
        context.addServlet(defaultServ,"/customer/*");
        server.setHandler(context);

        // Start Server
        server.start();

        // Determine Base URI for Server
        String host = connector.getHost();
        if (host == null)
        {
            host = "localhost";
        }
        int port = connector.getLocalPort();
        serverUri = new URI(String.format("http://%s:%d/",host,port));
    }

    @AfterAll
    public static void stopJetty()
    {
        try
        {
            server.stop();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void testGet() throws Exception
    {
        // Test GET
        HttpURLConnection http = (HttpURLConnection) serverUri.resolve("/customer/list").toURL().openConnection();
        http.connect();
        assertThat("Response Code", http.getResponseCode(), is(HttpStatus.OK_200));
    }
}
