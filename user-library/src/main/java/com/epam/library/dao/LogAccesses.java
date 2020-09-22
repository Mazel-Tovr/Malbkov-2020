package com.epam.library.dao;

import com.epam.library.dao.interfaces.ILogOperations;
import com.epam.library.model.Log;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LogAccesses implements ILogOperations
{
    private ConnectionDB dbConnection = ConnectionDB.getInstance();

    private static final Logger logger = Logger.getLogger("DataLayerLogger");

    @Override
    public Log getEntity(long id) {
        try {
            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement("SELECT * FROM Logs WHERE logid = ? ");
            stmt.setLong(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next())
            {
                return new Log(resultSet.getLong("logid"),
                        resultSet.getLong("userid"),resultSet.getString("text"));
            }
        }
        catch (SQLException e)
        {
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void addNewEntity(Log entity)
    {
        try {
        PreparedStatement stmt = dbConnection.getDbConnection()
                .prepareStatement("INSERT INTO Logs VALUES (?, ?, ?)");
        stmt.setLong(1,entity.getLogId());
        stmt.setLong(2,entity.getUserId());
        stmt.setString(3,entity.getText());
        stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void deleteEntity(Log entity) {
        try {
            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement("DELETE FROM Logs WHERE logid =?");
            stmt.setLong(1,entity.getLogId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void deleteEntity(long id) {
        try {
            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement("DELETE FROM Logs WHERE logid =?");
            stmt.setLong(1,id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public int getMaxIdPlusOne() {
        try {
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement("SELECT MAX(logid) FROM Logs");
            ResultSet queryResult = preparedStatement.executeQuery();
            if (queryResult.next()) {
                return queryResult.getInt(1)+1;
            }
            else {
                logger.error("Не удалось получить максимальный ID");
                return -1;
            }
        }
        catch (SQLException ex) {
            logger.error("Не удалось получить максимальный ID");
            return -1;
        }
    }

    @Override
    public List<Log> getAllLogs() {
        List<Log> logList = new ArrayList<>();
        try {

            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement("SELECT * FROM Logs");
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                 logList.add(new Log(resultSet.getLong("logid"),
                         resultSet.getLong("userid"),resultSet.getString("text")));
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
     return logList;
    }
}
