package com.tstu.library;

import com.tstu.library.businesslayer.AuthorServiceImpl;
import com.tstu.library.businesslayer.BookServiceImpl;
import com.tstu.library.businesslayer.interfaces.IAuthorService;
import com.tstu.library.businesslayer.interfaces.IBookService;
import com.tstu.library.exception.DataException;
import com.tstu.library.model.Book;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class BookServiceTest {

    private IBookService bookService = new BookServiceImpl();
    private IAuthorService authorService = new AuthorServiceImpl();
    private Book book = new Book("name", 123, 123, "000-0000000009", "publisher", authorService.getAuthor(32), false);
    private long bookId;

    public BookServiceTest() throws DataException {
    }


    @Test
    @Before
    public void addBookToDateBaseTest() throws DataException {

        bookService.addBookToDateBase(book.getBookName(), book.getReleaseYear(), book.getPageCount(), book.getISBN(), book.getPublisher(), book.getAuthor().getAuthorId());
        Book book1 = bookService.searchBookByISBN(book.getISBN());
        bookId = book1.getBookId();
        Assert.assertTrue(bookService.getAll().stream().anyMatch(e -> e.equals(book)));

    }

    @Test
    public void getBookTest() throws DataException {
        Assert.assertNotNull(bookService.getBook(15));
    }

    @Test
    public void getAllTest() throws DataException {
        List<Book> books = bookService.getAll();
        Assert.assertNotNull(books);
    }


    @Test
    public void searchBookByPartOfBookNameTest() throws DataException {
        Assert.assertTrue(bookService.searchBookByPartOfBookName(book.getBookName()).stream().anyMatch(e -> e.equals(book)));
    }

    @Test
    public void searchBookByPartOfAuthorNameTest() throws DataException {

        Assert.assertTrue(bookService.searchBookByPartOfAuthorName(book.getAuthor().getName()).stream().anyMatch(e -> e.equals(book)));
    }

    @Test
    public void searchBookByISBNTest() throws DataException {
        Book book1 = bookService.searchBookByISBN(book.getISBN());
        bookId = book1.getBookId();
        Assert.assertEquals(book1, book);
    }

    @Test
    public void searchBookByYearRangeTest() throws DataException {
        Assert.assertTrue(bookService.searchBookByYearRange(122, 124).stream().anyMatch(e -> e.equals(book)));
    }

    @Test
    public void searchBookByYearAndPageCountAndPartNameTest() throws DataException {
        Assert.assertTrue(bookService.searchBookByYearAndPageCountAndPartName(book.getReleaseYear(), book.getPageCount(), book.getBookName()).stream().anyMatch(e -> e.equals(book)));
    }

    @Test
    public void searchBookWhereUserBookMarkTest() throws DataException {
        Assert.assertNotNull(bookService.searchBookWhereUserBookMark(2));
    }

    @Test
    public void getAllNotTakenTest() throws DataException {
        Assert.assertNotNull(bookService.getAllNotTaken());
    }

}
