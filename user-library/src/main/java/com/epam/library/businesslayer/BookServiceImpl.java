package com.epam.library.businesslayer;


import com.epam.library.businesslayer.interfaces.IBookService;
import com.epam.library.dao.AuthorAccesses;
import com.epam.library.dao.BookAccesses;
import com.epam.library.dao.UserAccesses;
import com.epam.library.dao.interfaces.IAuthorOperations;
import com.epam.library.dao.interfaces.IBookOperations;
import com.epam.library.dao.interfaces.IUserOperation;
import com.epam.library.exception.DataException;
import com.epam.library.model.Author;
import com.epam.library.model.Book;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

import static com.epam.library.exception.ExceptionsCods.*;

public class BookServiceImpl implements IBookService {

    private static final Pattern ISBN_PATTERN = Pattern.compile("\\d{3}\\-\\d{10}");
    private static final Pattern YEAR_PATTERN = Pattern.compile("\\d{1,4}");
    private static final int CURRENT_YEAR = LocalDate.now().getYear();
    private IBookOperations bookTable = new BookAccesses();
    private IAuthorOperations authorTable = new AuthorAccesses();
    private IUserOperation userTable = new UserAccesses();
    private static final Logger logger = Logger.getLogger("BusinessLayerLogger");

    @Override
    public void addBookToDateBase(String bookName, int releaseYear, int pageCount,
                                  String ISBN, String publisher, long authorId) throws DataException {

        if (!YEAR_PATTERN.matcher(String.valueOf(releaseYear)).matches() || releaseYear > CURRENT_YEAR)
            throw new DataException("Date input error",WRONG_DATE_FORMAT);
        if(pageCount < 0)
            throw new DataException("Page count should be more then 0",PAGE_COUNT_SHOULD_BE_MORE_THEN_ZERO);
        if (!ISBN_PATTERN.matcher(ISBN).matches())
            throw new DataException("ISBN input error",ISBN_WRONG_FORMAT);//TODO NEED TEST THIS
        Author author = authorTable.getEntity(authorId);
        if (author == null)
            throw new DataException("Author with such id doesn't exist",AUTHOR_DOES_NOT_EXIST);
        int maxId = bookTable.getMaxIdPlusOne();
        if(maxId < 0) throw new DataException("Max id less then 0",ENTITY_CUN_NOT_BE_ADDED_MAX_ID_NOT_RECEIVED);
        logger.info("Book added");
        bookTable.addNewEntity(new Book(maxId,bookName, releaseYear, pageCount, ISBN, publisher, author,false));
    }

    @Override
    public Book getBook(long bookId) throws DataException {
        Book book = bookTable.getEntity(bookId);
        if (book == null)
            throw new DataException("Book with such id does't exist",BOOK_DOES_NOT_EXIST);
        logger.info("Book gotten");
        return book;
    }

    @Override
    public List<Book> getAll() throws DataException {
        List<Book> bookList = bookTable.getAll();
        if (bookList.isEmpty())
            throw new DataException("Books are absent",EMPTY_RESULT);
        logger.info("Books gotten");
        return bookList;
    }

    @Override
    public void dropBookById(long bookId) throws DataException {
        //TODO bookmark check
        bookTable.deleteEntity(getBook(bookId));
        logger.info("Book deleted");
    }


    @Override
    public List<Book> searchBookByPartOfBookName(String partOfName) throws DataException {
        if(partOfName.isEmpty())
            throw new DataException("Empty input",EMPTY_FIELD_INPUT);
        List<Book> bookList = bookTable.searchBookByPartOfBookName(partOfName);
        if (bookList.isEmpty())
            throw new DataException("No matches found",NO_MATCHES_FOUND);
        logger.info("Books gotten");
        return bookList;
    }

    @Override
    public List<Book> searchBookByPartOfAuthorName(String partOfAuthorName) throws DataException {
        if(partOfAuthorName.isEmpty())
            throw new DataException("Empty input",EMPTY_FIELD_INPUT);
        List<Book> bookList = bookTable.searchBookByPartOfAuthorName(partOfAuthorName);
        if (bookList.isEmpty())
            throw new DataException("No matches found",NO_MATCHES_FOUND);
        logger.info("Books gotten");
        return bookList;
    }

    @Override
    public Book searchBookByISBN(String ISBN) throws DataException {
        if(ISBN.isEmpty())
            throw new DataException("Empty input",EMPTY_FIELD_INPUT);
        Book book = bookTable.searchBookByISBN(ISBN);
        if (book == null)
            throw new DataException("No matches found",NO_MATCHES_FOUND);
        logger.info("Books gotten");
        return book;
    }

    @Override
    public List<Book> searchBookByYearRange(int from, int to) throws DataException {
        if ((!YEAR_PATTERN.matcher(String.valueOf(from)).matches()|| from > CURRENT_YEAR) ||
                (!YEAR_PATTERN.matcher(String.valueOf(to)).matches() || to > CURRENT_YEAR))
                throw new DataException("Date input error",WRONG_DATE_FORMAT);
        List<Book> bookList = bookTable.searchBookByYearRange(from,to);
        if (bookList.isEmpty())
            throw new DataException("No matches found",NO_MATCHES_FOUND);
        logger.info("Books gotten");
        return bookList;
    }

    @Override
    public List<Book> searchBookByYearAndPageCountAndPartName(int year, int pageCount, String partOfName) throws DataException {
        if (!YEAR_PATTERN.matcher(String.valueOf(year)).matches() || year > CURRENT_YEAR)
            throw new DataException("Date input error",WRONG_DATE_FORMAT);
        if(pageCount < 0)
            throw new DataException("Page count should be more then 0",PAGE_COUNT_SHOULD_BE_MORE_THEN_ZERO);
        List<Book> bookList = bookTable.searchBookByYearAndPageCountAndPartName(year,pageCount,partOfName);
        if (bookList.isEmpty())
            throw new DataException("No matches found",NO_MATCHES_FOUND);
        logger.info("Books gotten");
        return bookList;
    }

    @Override
    public List<Book> searchBookWhereUserBookMark(long userId) throws DataException {
        if(userTable.getEntity(userId) == null)
            throw new DataException("User with such id doesn't exist",USER_DOES_NOT_EXIST);
        List<Book> bookList = bookTable.searchBookWhereUserBookMark(userId);
        if (bookList.isEmpty())
            throw new DataException("No matches found",NO_MATCHES_FOUND);
        logger.info("Books gotten");
        return bookList;
    }

    @Override
    public void editingBook(long bookId, String bookName, int releaseYear, int pageCount, String ISBN, String publisher, long authorId,boolean isTaken) throws DataException {
        if (!YEAR_PATTERN.matcher(String.valueOf(releaseYear)).matches() || releaseYear > CURRENT_YEAR)
            throw new DataException("Date input error",WRONG_DATE_FORMAT);
        if(pageCount < 0)
            throw new DataException("Page count should be more then 0",PAGE_COUNT_SHOULD_BE_MORE_THEN_ZERO);
        if (!ISBN_PATTERN.matcher(ISBN).matches())
            throw new DataException("ISBN input error",ISBN_WRONG_FORMAT);//TODO NEED TEST THIS
        Author author = authorTable.getEntity(authorId);
        if (author == null)
            throw new DataException("Author with such id doesn't exist",AUTHOR_DOES_NOT_EXIST);
        bookTable.editing(bookId,new Book(bookName,releaseYear,pageCount,ISBN,publisher,author,isTaken));
        logger.info("Book edited");
    }

    @Override
    public List<Book> getAllNotTaken() throws DataException {

        List<Book> bookList = bookTable.getAllNotTaken();
        if (bookList.isEmpty())
            throw new DataException("Books are absent",EMPTY_RESULT);
        logger.info("Books gotten");
        return bookList;
    }

    @Override
    public List<Book> getAllTakenByUser(long userId) throws DataException {
        if(userTable.getEntity(userId) == null)
            throw new DataException("User with such id doesn't exist",USER_DOES_NOT_EXIST);
        List<Book> bookList = bookTable.getAllTakenByUser(userId);
        if (bookList.isEmpty())
            throw new DataException("Books are absent",EMPTY_RESULT);
        logger.info("Books gotten");
        return bookList;

    }

    @Override
    public void takeBook(long bookId, long userId) throws DataException {
        if(userTable.getEntity(userId) == null)
            throw new DataException("User with such id doesn't exist",USER_DOES_NOT_EXIST);
        Book book = bookTable.getEntity(bookId);
        if(book == null)
            throw new DataException("Book with such id doesn't exist",BOOK_DOES_NOT_EXIST);
        if(book.getIsTaken())
            throw new DataException("Book already taken",BOOK_ALREADY_TAKEN);
        bookTable.takeBook(bookId,userId);
        logger.info("Book taken");
    }

    @Override
    public void returnBook(long bookId) throws DataException {
        Book book = bookTable.getEntity(bookId);
        if(book == null)
            throw new DataException("Book with such id doesn't exist",BOOK_DOES_NOT_EXIST);
        bookTable.returnBook(bookId);
        logger.info("Book returned");
    }


}
