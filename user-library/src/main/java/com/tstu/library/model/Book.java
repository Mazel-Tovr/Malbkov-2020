package com.tstu.library.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;


@XmlType(name = "book")
@XmlRootElement
public class Book extends Entity
{
    @XmlAttribute
    private long bookId;
    @XmlAttribute
    private String bookName;
    @XmlAttribute
    private int releaseYear;
    @XmlAttribute
    private int pageCount;
    @XmlAttribute
    private String ISBN;
    @XmlAttribute
    private String publisher;
    @XmlElement(type = Author.class)
    private Author author;
    @XmlAttribute
    private boolean isTaken;

    public Book(long bookId, String bookName, int releaseYear, int pageCount, String ISBN, String publisher, Author author ,boolean isTaken) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.releaseYear = releaseYear;
        this.pageCount = pageCount;
        this.ISBN = ISBN;
        this.publisher = publisher;
        this.author = author;
    }

    public Book(String bookName, int releaseYear, int pageCount, String ISBN, String publisher, Author author,boolean isTaken) {
        this.bookName = bookName;
        this.releaseYear = releaseYear;
        this.pageCount = pageCount;
        this.ISBN = ISBN;
        this.publisher = publisher;
        this.author = author;
    }

    public Book() {
    }

    public boolean getIsTaken() {
        return isTaken;
    }

    public long getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public int getPageCount() {
        return pageCount;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getPublisher() {
        return publisher;
    }

    public Author getAuthor() {
        return author;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return releaseYear == book.releaseYear &&
                pageCount == book.pageCount &&
                isTaken == book.isTaken &&
                Objects.equals(bookName, book.bookName) &&
                Objects.equals(ISBN, book.ISBN) &&
                Objects.equals(publisher, book.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookName, releaseYear, pageCount, ISBN, publisher, isTaken);
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName +
                ", releaseYear=" + releaseYear +
                ", pageCount=" + pageCount +
                ", ISBN='" + ISBN +
                ", publisher='" + publisher +
                ", authorId=" + author.getAuthorId() +
                '}';
    }
}
