package com.tstu.library.controll;

import com.tstu.library.dao.ConnectionDB;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyAppServletContextListener implements ServletContextListener {

    private static final Logger logger = Logger.getRootLogger();

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        try {
           ConnectionDB.getInstance().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Run this before web application is started
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        //System.out.println("ServletContextListener started");




    }
}
