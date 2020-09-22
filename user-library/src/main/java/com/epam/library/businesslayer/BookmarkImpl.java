package com.epam.library.businesslayer;

import com.epam.library.businesslayer.interfaces.IBookmarkService;
import com.epam.library.dao.BookAccesses;
import com.epam.library.dao.BookMarksAccesses;
import com.epam.library.dao.UserAccesses;
import com.epam.library.dao.interfaces.IBookOperations;
import com.epam.library.dao.interfaces.IBookmarkOperations;
import com.epam.library.dao.interfaces.IUserOperation;
import com.epam.library.exception.DataException;
import com.epam.library.model.Bookmark;
import org.apache.log4j.Logger;

import java.util.List;

import static com.epam.library.exception.ExceptionsCods.*;

public class BookmarkImpl implements IBookmarkService {

    private IBookOperations bookTable = new BookAccesses();
    private IUserOperation userTable = new UserAccesses();
    private IBookmarkOperations bookmarkTable = new BookMarksAccesses();
    private static final Logger logger = Logger.getLogger("BusinessLayerLogger");

    @Override
    public void addBookmark(long userId, long bookId, int pageNumber) throws DataException {

        if (userTable.getEntity(userId) == null) throw new DataException("User with such id doesn't exist",USER_DOES_NOT_EXIST);
        if (bookTable.getEntity(bookId) == null) throw new DataException("Book with such id doesn't exist",BOOK_DOES_NOT_EXIST);
        if (pageNumber < 0) throw new DataException("Page count should be more then 0",PAGE_COUNT_SHOULD_BE_MORE_THEN_ZERO);
        if (bookTable.getEntity(bookId).getPageCount() < pageNumber) throw new DataException("Book doesn't have such page",BOOK_DOSE_NOT_HAVE_SUCH_PAGE);
        int maxId = bookmarkTable.getMaxIdPlusOne();
        if(maxId < 0) throw new DataException("Max id less then 0",ENTITY_CUN_NOT_BE_ADDED_MAX_ID_NOT_RECEIVED);
        logger.debug("Validation successfully passed");
        logger.info("Bookmark was add");
        bookmarkTable.addNewEntity(new Bookmark(maxId,userId, bookId, pageNumber));

    }

    @Override
    public Bookmark getBookmark(long bookmarkId) throws DataException {
        Bookmark bookmark = bookmarkTable.getEntity(bookmarkId);
        if (bookmark == null) throw new DataException("Bookmark with such id doesn't exist",BOOKMARK_DOES_NOT_EXIST);
        logger.info("Bookmark was get");
        return bookmark;
    }

    @Override
    public void dropBookmark(long bookmarkId) throws DataException {
        bookmarkTable.deleteEntity(getBookmark(bookmarkId));
        logger.info("Bookmark deleted");
    }

    @Override
    public List<Bookmark> getAllUserBookMark(long userId) throws DataException {
      List<Bookmark> bookmarks = bookmarkTable.getAllUserBookMark(userId);
      if(bookmarks.isEmpty()) throw new DataException("Bookmark are absent",EMPTY_RESULT);
        logger.info("Bookmarks were gotten");
      return bookmarks;
    }

    @Override
    public void editing(long bookmarkId, Bookmark bookmark) throws DataException {
        if (userTable.getEntity(bookmark.getUserId()) == null) throw new DataException("User with such id doesn't exist",USER_DOES_NOT_EXIST);
        if (bookTable.getEntity(bookmark.getBookId()) == null) throw new DataException("Book with such id doesn't exist",BOOK_DOES_NOT_EXIST);
        if (bookmark.getPageNumber()< 0) throw new DataException("Page count should be more then 0",PAGE_COUNT_SHOULD_BE_MORE_THEN_ZERO);
        if (bookTable.getEntity(bookmarkId).getPageCount() < bookmark.getPageNumber()) throw new DataException("Book doesn't have such page",BOOK_DOSE_NOT_HAVE_SUCH_PAGE);
        bookmarkTable.editing(bookmarkId, bookmark);
        logger.info("Bookmark edited");
    }
}

