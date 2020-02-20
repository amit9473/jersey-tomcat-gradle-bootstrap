/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.example;

import java.io.File;
import java.util.Properties;

import javax.servlet.ServletException;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class Library {
	
	private static final String APP_PROPERTIES = "application.properties";
	
    public boolean someLibraryMethod() {
        return true;
    }
    
    public static void main(String[] args) throws Exception	 {
    	Properties properties = new Properties();
    	properties.load(Library.class.getClassLoader().getResourceAsStream(APP_PROPERTIES));
        int port = Integer.parseInt(properties.getProperty("port"));
    	if (args.length>0)
    		port = Integer.parseInt(args[0]);    		
    	new Library().start(port);
    }

    public void start(int port) throws ServletException, LifecycleException {

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);

        Context context = tomcat.addWebapp("/", new File(".").getAbsolutePath());

        Tomcat.addServlet(context, "jersey-container-servlet", resourceConfig());
        context.addServletMapping("/*", "jersey-container-servlet");

        tomcat.start();	
        tomcat.getServer().await();
    }

    private ServletContainer resourceConfig() {
        return new ServletContainer(new ResourceConfig(new ResourceLoader().getClasses()));
    }
}
