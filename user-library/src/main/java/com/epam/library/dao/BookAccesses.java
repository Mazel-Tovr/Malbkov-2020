package com.epam.library.dao;

import com.epam.library.dao.interfaces.IAuthorOperations;
import com.epam.library.dao.interfaces.IBookOperations;
import com.epam.library.model.Book;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookAccesses implements IBookOperations {

    private ConnectionDB dbConnection = ConnectionDB.getInstance();

    private static final Logger logger = Logger.getLogger("DataLayerLogger");

    private IAuthorOperations authorOperations = new AuthorAccesses();


    @Override
    public List<Book> getAll() {
        List<Book> bookList = new ArrayList<>();
        try {
            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement("SELECT * FROM books");
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                bookList.add(new Book(resultSet.getLong("bookid"), resultSet.getString("bookName"),
                        resultSet.getInt("releaseYear"), resultSet.getInt("pageCount"),
                        resultSet.getString("ISBN"), resultSet.getString("publisher"),
                        authorOperations.getEntity(resultSet.getInt("author")),resultSet.getString("IS_TAKEN").equals("TRUE")));
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return bookList;
    }

    @Override
    public List<Book> getAllNotTaken() {
        List<Book> bookList = new ArrayList<>();
        try {
            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement("SELECT * FROM books WHERE IS_TAKEN = 'False'");
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                bookList.add(new Book(resultSet.getLong("bookid"), resultSet.getString("bookName"),
                        resultSet.getInt("releaseYear"), resultSet.getInt("pageCount"),
                        resultSet.getString("ISBN"), resultSet.getString("publisher"),
                        authorOperations.getEntity(resultSet.getInt("author")),resultSet.getString("IS_TAKEN").equals("TRUE")));
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return bookList;
    }

    @Override
    public List<Book> getAllTakenByUser(long userId) {

        List<Book> bookList = new ArrayList<>();
        try {
            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement("SELECT * FROM BOOKS WHERE BOOKID IN (SELECT BOOK_ID FROM USER_BOOK WHERE USER_ID = ?)");
            stmt.setLong(1, userId);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                bookList.add(new Book(resultSet.getLong("bookid"), resultSet.getString("bookName"),
                        resultSet.getInt("releaseYear"), resultSet.getInt("pageCount"),
                        resultSet.getString("ISBN"), resultSet.getString("publisher"),
                        authorOperations.getEntity(resultSet.getInt("author")),resultSet.getString("IS_TAKEN").equals("TRUE")));
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return bookList;

    }

    @Override
    public List<Book> searchBookByPartOfBookName(String partOfName) {
        List<Book> bookList = new ArrayList<>();
        try {
            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement("SELECT * FROM books WHERE bookName LIKE ? ");
            stmt.setString(1, "%" + partOfName + "%");
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                bookList.add(new Book(resultSet.getLong("bookid"), resultSet.getString("bookName"),
                        resultSet.getInt("releaseYear"), resultSet.getInt("pageCount"),
                        resultSet.getString("ISBN"), resultSet.getString("publisher"),
                        authorOperations.getEntity(resultSet.getInt("author")),resultSet.getString("IS_TAKEN").equals("TRUE")));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return bookList;
    }

    @Override
    public List<Book> searchBookByPartOfAuthorName(String partOfAuthorName) {
        List<Book> bookList = new ArrayList<>();
        try {
            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement("SELECT * FROM books WHERE author IN " +
                            "(SELECT authorid FROM authors WHERE name LIKE ? )" );
            stmt.setString(1, "%" + partOfAuthorName + "%");
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                bookList.add(new Book(resultSet.getLong("bookid"), resultSet.getString("bookName"),
                        resultSet.getInt("releaseYear"), resultSet.getInt("pageCount"),
                        resultSet.getString("ISBN"), resultSet.getString("publisher"),
                        authorOperations.getEntity(resultSet.getInt("author")),resultSet.getString("IS_TAKEN").equals("TRUE")));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return bookList;
    }

    @Override
    public Book searchBookByISBN(String ISBN) {
        try {
            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement("SELECT * FROM books WHERE ISBN = ? ");
            stmt.setString(1, ISBN);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return new Book(resultSet.getLong("bookid"), resultSet.getString("bookName"),
                        resultSet.getInt("releaseYear"), resultSet.getInt("pageCount"),
                        resultSet.getString("ISBN"), resultSet.getString("publisher"),
                        authorOperations.getEntity(resultSet.getInt("author")),resultSet.getString("IS_TAKEN").equals("TRUE"));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Book> searchBookByYearRange(int from, int to) {
        List<Book> bookList = new ArrayList<>();
        try {
            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement("SELECT * FROM books WHERE releaseYear BETWEEN ? AND ? ");
            stmt.setInt(1, from);
            stmt.setInt(2, to);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                bookList.add(new Book(resultSet.getLong("bookid"), resultSet.getString("bookName"),
                        resultSet.getInt("releaseYear"), resultSet.getInt("pageCount"),
                        resultSet.getString("ISBN"), resultSet.getString("publisher"),
                        authorOperations.getEntity(resultSet.getInt("author")),resultSet.getString("IS_TAKEN").equals("TRUE")));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return bookList;
    }

    @Override
    public List<Book> searchBookByYearAndPageCountAndPartName(int year, int pageCount, String partOfName) {
        List<Book> bookList = new ArrayList<>();
        try {
            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement("SELECT * FROM books WHERE releaseYear = ? AND pageCount = ? " +
                            "AND bookName LIKE ? ");
            stmt.setInt(1, year);
            stmt.setInt(2, pageCount);
            stmt.setString(3, "%" + partOfName + "%");
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                bookList.add(new Book(resultSet.getLong("bookid"), resultSet.getString("bookName"),
                        resultSet.getInt("releaseYear"), resultSet.getInt("pageCount"),
                        resultSet.getString("ISBN"), resultSet.getString("publisher"),
                        authorOperations.getEntity(resultSet.getInt("author")),resultSet.getString("IS_TAKEN").equals("TRUE")));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return bookList;
    }

    @Override
    public List<Book> searchBookWhereUserBookMark(long userId)
    {
        List<Book> bookList = new ArrayList<>();
        try {
            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement("SELECT * FROM books WHERE bookid IN " +
                            "(SELECT bookid FROM bookmarks WHERE userid = ? )" );

            stmt.setLong(1,userId);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                bookList.add(new Book(resultSet.getLong("bookid"), resultSet.getString("bookName"),
                        resultSet.getInt("releaseYear"), resultSet.getInt("pageCount"),
                        resultSet.getString("ISBN"), resultSet.getString("publisher"),
                        authorOperations.getEntity(resultSet.getInt("author")),resultSet.getString("IS_TAKEN").equals("TRUE")));
            }

        }catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return bookList;
    }

    @Override
    public void editing(long bookId, Book book) {

        try {
            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement(" UPDATE books SET bookName =?,releaseYear=?,pageCount=?,ISBN=?,publisher=?,author=?  WHERE bookid = ?");

            stmt.setString(1, book.getBookName());
            stmt.setInt(2,book.getReleaseYear());
            stmt.setInt(3, book.getPageCount());
            stmt.setString(4, book.getISBN());
            stmt.setString(5,book.getPublisher());
            stmt.setLong(6,book.getAuthor().getAuthorId());

            stmt.setLong(7,bookId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

    }

    @Override
    public void takeBook(long bookId,long userId) {
        try {
            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement("UPDATE books SET IS_TAKEN = ? WHERE bookid = ?");
            stmt.setBoolean(1,true);
            stmt.setLong(2,bookId);
            stmt.executeUpdate();
            long iD = -1;
            PreparedStatement stmt1 = dbConnection.getDbConnection().prepareStatement("SELECT MAX(ID) FROM USER_BOOK");
            ResultSet queryResult = stmt1.executeQuery();
            if (queryResult.next()) {
                iD = queryResult.getInt(1)+1;
            }
            if (iD == -1) throw new SQLException();
            PreparedStatement stmt2 = dbConnection.getDbConnection().
                    prepareStatement("INSERT INTO USER_BOOK VALUES (? ,?, ?)");
            stmt2.setLong(1,iD);
            stmt2.setLong(2,bookId);
            stmt2.setLong(3,userId);
            stmt2.executeUpdate();

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

    }

    @Override
    public void returnBook(long bookId) {
        try {

            PreparedStatement stmt1 = dbConnection.getDbConnection()
                    .prepareStatement("UPDATE books SET IS_TAKEN ='False' WHERE bookid = ?");

            stmt1.setLong(1,bookId);
            stmt1.executeUpdate();
            stmt1.close();

            PreparedStatement stmt2 = dbConnection.getDbConnection()
                    .prepareStatement("DELETE FROM USER_BOOK WHERE BOOK_ID =?");
            stmt2.setLong(1,bookId);
            stmt2.executeUpdate();

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public Book getEntity(long id) {
        try {
            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement("SELECT * FROM books WHERE bookid = ? ");
            stmt.setLong(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return new Book(resultSet.getLong("bookid"), resultSet.getString("bookName"),
                        resultSet.getInt("releaseYear"), resultSet.getInt("pageCount"),
                        resultSet.getString("ISBN"), resultSet.getString("publisher"),
                        authorOperations.getEntity(resultSet.getInt("author")),resultSet.getString("IS_TAKEN").equals("TRUE"));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void addNewEntity(Book entity) {
        try {
            PreparedStatement stmt = dbConnection.getDbConnection().
                    prepareStatement("INSERT INTO books VALUES (?, ?, ?, ?, ?, ?, ?,'FALSE')");
            stmt.setLong(1,entity.getBookId());
            stmt.setString(2, entity.getBookName());
            stmt.setInt(3, entity.getReleaseYear());
            stmt.setInt(4, entity.getPageCount());
            stmt.setString(5, entity.getISBN());
            stmt.setString(6,entity.getPublisher());
            stmt.setLong(7,entity.getAuthor().getAuthorId());
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            logger.error(e.getMessage());
        }

    }

    @Override
    public void deleteEntity(Book entity) {

        try {
            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement("DELETE FROM BOOKMARKS WHERE bookid =?");
            stmt.setLong(1,entity.getBookId());
            stmt.executeUpdate();

             stmt = dbConnection.getDbConnection()
                    .prepareStatement("DELETE FROM books WHERE bookid =?");
            stmt.setLong(1,entity.getBookId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void deleteEntity(long id) {

        try {
            PreparedStatement stmt = dbConnection.getDbConnection()
                    .prepareStatement("DELETE FROM books WHERE bookid =?");
            stmt.setLong(1,id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public int getMaxIdPlusOne() {
        try {
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement("SELECT MAX(bookid) FROM books");
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
