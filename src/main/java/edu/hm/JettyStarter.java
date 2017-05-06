package edu.hm;

import org.eclipse.jetty.server.*;
import org.eclipse.jetty.webapp.*;

/**
 * Start the application without an AppServer like tomcat.
 * @author ab@cs.hm.edu
 *
 */
public final class JettyStarter {

    public static final String APP_URL = "/";
    public static final int PORT = 8090;
    public static final String WEBAPP_DIR = "./src/main/webapp/";
    
    /**
     * Default Constructor because of Checkstyle (no joke).
     */
    private JettyStarter()  {
        
    }
    
    /**
     * Main Program for executing a Jetty Client.
     * @param args Program Arguments
     * @throws Exception Exception
     */
    public static void main(String... args) throws Exception {
        Server jetty = new Server(PORT);
        jetty.setHandler(new WebAppContext(WEBAPP_DIR, APP_URL));
        jetty.start();
        System.out.println("Jetty listening on port " + PORT);
        jetty.join();
    }
}