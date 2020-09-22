package com.epam.library.dao;

import com.epam.library.dao.interfaces.ICommonOperations;
import com.epam.library.model.EnumRole;
import com.epam.library.model.Role;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleAccesses implements ICommonOperations<Role> {

    private ConnectionDB dbConnection = ConnectionDB.getInstance();

    private static final Logger logger = Logger.getLogger("DataLayerLogger");

    @Override
    public Role getEntity(long id)
    {
        try {
            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement("SELECT * FROM Roles WHERE roleid = ?");
            stmt.setLong(1,id);
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next())
            {
                return new Role(resultSet.getInt("roleid"), EnumRole.valueOf(resultSet.getString("type")));
            }
        }
        catch (SQLException e)
        {
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void addNewEntity(Role entity)
    {
        try
        {
            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement("INSERT INTO Roles VALUES (?, ?)");
            stmt.setLong(1,entity.getRoleId());
            stmt.setString(1,entity.getType().toString());
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void deleteEntity(Role entity)
    {
        try {
            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement(" DELETE FROM Roles WHERE roleid =?");
            stmt.setLong(1,entity.getRoleId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void deleteEntity(long id)
    {
        try {
            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement("DELETE FROM Roles WHERE roleid =?");
            stmt.setLong(1,id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public int getMaxIdPlusOne() {
        try {
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement("SELECT MAX(roleid) FROM Roles");
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



}
