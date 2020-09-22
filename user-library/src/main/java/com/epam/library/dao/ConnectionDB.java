package com.epam.library.dao;

import com.epam.library.ClassConfig;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB implements AutoCloseable
{

    private static final Logger logger =  Logger.getRootLogger();

    private Connection dbConnection = null;

    private static ConnectionDB instance = null;

    private ClassConfig config = ClassConfig.getInstance();

    private ConnectionDB() {
        getDBConnection();
    }

    public static ConnectionDB getInstance()
    {
        if(instance == null)
        {
            synchronized (ConnectionDB.class)
            {
                if(instance == null)
                {
                    instance = new ConnectionDB();
                }
            }
        }
        return instance;
    }

    public Connection getDbConnection() throws SQLException {
        if(dbConnection == null || dbConnection.isClosed()) getDBConnection();
        return dbConnection;
    }

    private void getDBConnection()
    {
        try
        {
            Class.forName(config.getDB_DRIVER());
        }
        catch (ClassNotFoundException e)
        {
            logger.error(e.getMessage());
        }
        try
        {
            dbConnection = DriverManager.getConnection(config.getDB_CONNECTION(),
                    config.getDB_USER(),config.getDB_PASSWORD());
            logger.info("Database connection is established");
            //return true;
        }
        catch (SQLException e)
        {
            logger.error(e.getMessage());

        }
        //return false;
    }

    @Override
    public void close() throws Exception {
        if(dbConnection != null)
            dbConnection.close();
    }
}
