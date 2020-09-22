package com.tstu.library.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Bookmark")
@XmlRootElement
public class Bookmark extends Entity
{
    @XmlAttribute
    private long bookmarkId;
    @XmlAttribute
    private long userId;
    @XmlAttribute
    private long bookId;
    @XmlAttribute
    private int pageNumber;

    public Bookmark(long bookmarkId, long userId, long bookId, int pageNumber) {
        this.bookmarkId = bookmarkId;
        this.userId = userId;
        this.bookId = bookId;
        this.pageNumber = pageNumber;
    }

    public Bookmark() {
    }

    public Bookmark(long userId, long bookId, int pageNumber) {
        this.userId = userId;
        this.bookId = bookId;
        this.pageNumber = pageNumber;
    }

    public long getBookmarkId() {
        return bookmarkId;
    }

    public long getUserId() {
        return userId;
    }

    public long getBookId() {
        return bookId;
    }

    public int getPageNumber() {
        return pageNumber;
    }
}
