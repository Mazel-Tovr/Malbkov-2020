package com.epam.library.dao.interfaces;

import com.epam.library.model.Bookmark;

import java.util.List;

public interface IBookmarkOperations extends ICommonOperations<Bookmark>
{
    List<Bookmark> getAllUserBookMark(long userId);
    void editing(long bookmarkId,Bookmark bookmark);

}
