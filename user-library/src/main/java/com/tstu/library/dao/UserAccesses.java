package com.tstu.library.dao;

import com.tstu.library.dao.interfaces.ICommonOperations;
import com.tstu.library.dao.interfaces.IUserOperation;
import com.tstu.library.model.Role;
import com.tstu.library.model.User;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAccesses implements IUserOperation {

    private ConnectionDB dbConnection = ConnectionDB.getInstance();

    private static final Logger logger = Logger.getLogger("DataLayerLogger");

    private ICommonOperations<Role> role = new RoleAccesses();

    @Override
    public boolean isUserExist(String nickName) {
        try
        {
            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement("SELECT nickname FROM users WHERE nickname =?");
            stmt.setString(1,nickName);
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next())
            {
                return true;
            }
        }
        catch (SQLException e)
        {
            logger.error(e.getMessage());
        }
        return false;
    }

    @Override
    public User getUser(String nickName, String password){

        try {
            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement("SELECT * FROM users WHERE nickname =? AND password=?");
            stmt.setString(1, nickName);
            stmt.setString(2, password);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next())
            {
                return new User(resultSet.getLong("userid"), resultSet.getString("firstname"),
                        resultSet.getString("lastname"), resultSet.getString("nickname"),
                        resultSet.getString("password"), role.getEntity(resultSet.getInt("roleid")),
                        resultSet.getString("isblocked").equals("TRUE"));//TODO Test this

            }
        }
        catch (SQLException e)
        {
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public User getEntity(long id) {
        try {
            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement("SELECT * FROM users WHERE userid = ?");
            stmt.setLong(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next())
            {
                return new User(resultSet.getLong("userid"), resultSet.getString("firstname"),
                        resultSet.getString("lastname"), resultSet.getString("nickname"),
                        resultSet.getString("password"), role.getEntity(resultSet.getInt("roleid")),
                        resultSet.getString("isblocked").equals("TRUE"));
            }
        }
        catch (SQLException e)
        {
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void addNewEntity(User entity) {
        try
        {
            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement("INSERT INTO users VALUES (?, ?, ?, ?, ?, ?, 'False')");
            stmt.setLong(1,entity.getUserId());
            stmt.setString(2,entity.getFirstName());
            stmt.setString(3,entity.getLastName());
            stmt.setString(4,entity.getNickName());stmt.setString(5,entity.getPassword());
            stmt.setInt(6,entity.getRole().getRoleId());
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void blockUser(User user) {
        try {
            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement(" UPDATE Users SET isblocked = 'True' WHERE userid =?");
            stmt.setLong(1,user.getUserId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void blockUser(long userId) {
        try {
            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement(" UPDATE Users SET isblocked = 'True' WHERE userid =?");
            stmt.setLong(1,userId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void deleteEntity(User entity) {
        try {
            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement(" DELETE FROM Users WHERE userid =?");
            stmt.setLong(1,entity.getUserId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void deleteEntity(long id) {
        try {
            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement(" DELETE FROM Users WHERE userid =?");
            stmt.setLong(1,id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public int getMaxIdPlusOne() {

        try {
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement("SELECT MAX(userid) FROM users");
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
