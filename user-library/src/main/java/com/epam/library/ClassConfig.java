package com.epam.library;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ClassConfig {
    private static final Logger logger = Logger.getRootLogger();

    private static ClassConfig instance;

    private String DB_DRIVER;
    private String DB_CONNECTION;
    private String DB_USER;
    private String DB_PASSWORD;

    public static ClassConfig getInstance() {
        if (instance != null) return instance;

        synchronized (ClassConfig.class) {
            if (instance == null) {
                instance = new ClassConfig();
            }
            return instance;
        }

    }

    private ClassConfig() {
        try {

            String rootPath = new File(".").getAbsolutePath();
            StringBuilder path = new StringBuilder(rootPath);
            path.deleteCharAt(path.length()-1);
            path.append("db\\lib");

            Properties properties = new Properties();
            String propFileName = "database.properties";

            logger.debug("getting date from properties");
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            if (inputStream != null) {
                properties.load(inputStream);
                DB_DRIVER = properties.getProperty("DB_DRIVER");
                DB_CONNECTION = "jdbc:h2:"+path.toString();  //= properties.getProperty("DB_CONNECTION");
                DB_USER = properties.getProperty("DB_USER");
                DB_PASSWORD = properties.getProperty("DB_PASSWORD");
                inputStream.close();

            }
            logger.debug("date were gotten");

        } catch (IOException e) {
           logger.error(e);
        }
    }

    public String getDB_DRIVER() {
        return DB_DRIVER;
    }

    public String getDB_CONNECTION() {
        return DB_CONNECTION;
    }

    public String getDB_USER() {
        return DB_USER;
    }

    public String getDB_PASSWORD() {
        return DB_PASSWORD;
    }
}
