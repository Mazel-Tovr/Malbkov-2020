package com.epam.library.businesslayer.interfaces;

import com.epam.library.exception.DataException;
import com.epam.library.model.Bookmark;

import java.util.List;

public interface IBookmarkService
{
    void addBookmark(long userId, long bookId, int pageNumber) throws DataException;
    Bookmark getBookmark(long bookmarkId) throws DataException;
    void dropBookmark(long bookmarkId) throws DataException;
    List<Bookmark> getAllUserBookMark(long userId) throws DataException;
    void editing(long bookmarkId,Bookmark bookmark) throws DataException;
}
