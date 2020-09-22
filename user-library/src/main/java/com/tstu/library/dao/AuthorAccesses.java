package com.tstu.library.dao;

import com.tstu.library.dao.interfaces.IAuthorOperations;
import com.tstu.library.model.Author;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorAccesses implements IAuthorOperations {

    private ConnectionDB dbConnection = ConnectionDB.getInstance();

    private static final Logger logger = Logger.getLogger("DataLayerLogger");


    @Override
    public List<Author> getAll() {
        List<Author> authors = new ArrayList<>();
        try {
            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement("SELECT * FROM authors");
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                authors.add(new Author(resultSet.getInt("authorid"),
                        resultSet.getString("name"), resultSet.getString("secondname"),
                        resultSet.getString("lastname"), resultSet.getDate("dob")));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return authors;
    }

    @Override
    public Author getEntity(long id) {
        try {
            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement("SELECT * FROM authors WHERE authorid = ?");
            stmt.setLong(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return new Author(resultSet.getInt("authorid"),
                        resultSet.getString("name"), resultSet.getString("secondname"),
                        resultSet.getString("lastname"), resultSet.getDate("dob"));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void addNewEntity(Author entity) {
        try {
            PreparedStatement stmt = dbConnection.getDbConnection().
                    prepareStatement("INSERT INTO authors VALUES (?, ?, ?, ?, ?)");
            stmt.setLong(1,entity.getAuthorId());
            stmt.setString(2, entity.getName());
            stmt.setString(3, entity.getSecondName());
            stmt.setString(4, entity.getLastName());
            stmt.setDate(5, Date.valueOf(entity.getDob().toString()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

    }

    @Override
    public void deleteEntity(Author entity) {

        try {
            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement("DELETE FROM authors WHERE authorid =?");
            stmt.setLong(1, entity.getAuthorId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void deleteEntity(long id) {
        try {
            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement("DELETE FROM authors WHERE authorid =?");
            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }


    @Override
    public int getMaxIdPlusOne() {
        try {
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement("SELECT MAX(authorid) FROM authors");
            ResultSet queryResult = preparedStatement.executeQuery();
            if (queryResult.next()) {
                return queryResult.getInt(1)+1;
            } else {
                logger.error("Не удалось получить максимальный ID");
                return -1;
            }
        } catch (SQLException ex) {
            logger.error("Не удалось получить максимальный ID");
            return -1;
        }
    }


    @Override
    public void deleteAuthorAndAllHisBooks(int authorId) {
        try {

            deleteEntity(authorId);

            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement("DELETE FROM books WHERE author =?");
            stmt.setLong(1, authorId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public Author isAuthorExist(Author author) {
        try {
            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement("SELECT * FROM authors WHERE name = ? AND secondName = ?" +
                            " AND lastName = ? AND dob = ?");
            stmt.setString(1, author.getName());
            stmt.setString(2, author.getSecondName());
            stmt.setString(3, author.getLastName());
            stmt.setDate(4, Date.valueOf(author.getDob().toString()) );
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return new Author(resultSet.getInt("authorid"),
                        resultSet.getString("name"), resultSet.getString("secondname"),
                        resultSet.getString("lastname"), resultSet.getDate("dob"));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void editing(int authorId, Author author) {
        try {
            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement(" UPDATE authors SET name=?,secondname=?,lastname=?,dob=?  WHERE authorid = ?");

            stmt.setString(1, author.getName());
            stmt.setString(2, author.getSecondName());
            stmt.setString(3, author.getLastName());
            stmt.setDate(4, Date.valueOf(author.getDob().toString()) );

            stmt.setLong(5,authorId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
}
