package com.epam.library.dao;

import com.epam.library.dao.interfaces.IBookmarkOperations;
import com.epam.library.model.Bookmark;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookMarksAccesses implements IBookmarkOperations {

    private ConnectionDB dbConnection = ConnectionDB.getInstance();

    private static final Logger logger = Logger.getLogger("DataLayerLogger");

    @Override
    public Bookmark getEntity(long id) {
        try {
            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement("SELECT * FROM bookmarks WHERE bookmarkid = ?");
            stmt.setLong(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return new Bookmark(resultSet.getLong("bookmarkid"), resultSet.getLong("userid"),
                        resultSet.getLong("bookid"), resultSet.getInt("pagenumber"));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void addNewEntity(Bookmark entity) {
        try {
            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement("INSERT INTO bookmarks VALUES (?, ?, ?, ?)");
            stmt.setLong(1, entity.getBookId());
            stmt.setLong(2, entity.getUserId());
            stmt.setLong(3, entity.getBookId());
            stmt.setInt(4, entity.getPageNumber());
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void deleteEntity(Bookmark entity) {
        try {
            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement("DELETE FROM bookmarks WHERE bookmarkid = ?");
            stmt.setLong(1,entity.getBookmarkId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void deleteEntity(long id) {
        try {
            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement("DELETE FROM bookmarks WHERE bookmarkid = ?");
            stmt.setLong(1,id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public int getMaxIdPlusOne() {
        try {
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement("SELECT MAX(bookmarkid) FROM bookmarks");
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
    public List<Bookmark> getAllUserBookMark(long userId) {
        List<Bookmark> bookmarks =new ArrayList<>();
        try {
            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement("SELECT * FROM bookmarks WHERE userid = ?");
            stmt.setLong(1,userId);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                bookmarks.add(new Bookmark(resultSet.getLong("bookmarkid"), resultSet.getLong("userid"),
                        resultSet.getLong("bookid"), resultSet.getInt("pagenumber")));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return bookmarks;
    }

    @Override
    public void editing(long bookmarkId, Bookmark bookmark) {
        try {
            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement(" UPDATE bookmarks SET bookid=?,pagenumber=?  WHERE bookmarkid = ?");

            stmt.setLong(1, bookmark.getBookId());
            stmt.setInt(2,bookmark.getPageNumber());


            stmt.setLong(3,bookmarkId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
}
