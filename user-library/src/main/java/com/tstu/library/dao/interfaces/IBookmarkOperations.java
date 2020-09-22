package com.tstu.library.dao.interfaces;

import com.tstu.library.model.Bookmark;

import java.util.List;

public interface IBookmarkOperations extends ICommonOperations<Bookmark>
{
    List<Bookmark> getAllUserBookMark(long userId);
    void editing(long bookmarkId,Bookmark bookmark);

}
