package com.tstu.library.dao.interfaces;

import com.tstu.library.model.Book;

import java.util.List;

public interface IBookOperations extends ICommonOperations<Book> {
    List<Book> getAll();

    List<Book> getAllNotTaken();

    List<Book> getAllTakenByUser(long userId);

    List<Book> searchBookByPartOfBookName(String partOfName);

    List<Book> searchBookByPartOfAuthorName(String partOfAuthorName);

    Book searchBookByISBN(String ISBN);

    List<Book> searchBookByYearRange(int from, int to);

    List<Book> searchBookByYearAndPageCountAndPartName(int year, int pageCount, String partOfName);

    List<Book> searchBookWhereUserBookMark(long userId);

    void editing(long bookId, Book book);

    void takeBook(long bookId,long userId);

    void returnBook(long bookId);
}
