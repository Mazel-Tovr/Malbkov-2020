package com.tstu.library.businesslayer.interfaces;

import com.tstu.library.exception.DataException;
import com.tstu.library.model.Book;

import java.util.List;

public interface IBookService {
    void addBookToDateBase(String bookName, int releaseYear, int pageCount, String ISBN, String publisher, long authorId) throws DataException;

    Book getBook(long bookId) throws DataException;

    List<Book> getAll() throws DataException;

    void dropBookById(long bookId) throws DataException;

    List<Book> searchBookByPartOfBookName(String partOfName) throws DataException;

    List<Book> searchBookByPartOfAuthorName(String partOfAuthorName) throws DataException;

    Book searchBookByISBN(String ISBN) throws DataException;

    List<Book> searchBookByYearRange(int from, int to) throws DataException;

    List<Book> searchBookByYearAndPageCountAndPartName(int year, int pageCount, String partOfName) throws DataException;

    List<Book> searchBookWhereUserBookMark(long userId) throws DataException;

    void editingBook(long bookId, String bookName, int releaseYear, int pageCount, String ISBN, String publisher, long authorId, boolean isTaken) throws DataException;

    List<Book> getAllNotTaken() throws DataException;

    List<Book> getAllTakenByUser(long userId) throws DataException;

    void takeBook(long bookId, long userId) throws DataException;

    void returnBook(long bookId) throws DataException;
}
