package com.tstu.library.businesslayer;

import com.tstu.library.businesslayer.interfaces.IAuthorService;
import com.tstu.library.businesslayer.interfaces.IBookService;
import com.tstu.library.exception.DataException;
import com.tstu.library.model.Author;
import com.tstu.library.model.Book;
import org.apache.log4j.Logger;

import java.util.List;

public class ParserEmpowerment
{
    private IAuthorService authorService = new AuthorServiceImpl();
    private IBookService bookService = new BookServiceImpl();
    private static final Logger logger = Logger.getLogger("BusinessLayerLogger");

    //Мне стыдно за такой код ((
    public void addBookToDB(List<Book> listBook)  {
        if(!listBook.isEmpty()) {
            for (Book book : listBook) {
                Author bookAuthor = book.getAuthor();
                long authorId;
                try {

                    Author author = authorService.getByNameSecondNameLastNameDob(bookAuthor.getName(), bookAuthor.getSecondName(),
                            bookAuthor.getLastName(), bookAuthor.getDob().toString());
                    if (author == null) {
                        authorId = addAuthorToDBAndReturnAuthorId(bookAuthor);
                    } else authorId = author.getAuthorId();


                    try {
                        bookService.searchBookByISBN(book.getISBN());
                        logger.debug("Book already exist ");
                    } catch (DataException e) {
                        logger.debug("Book doesn't exist");
                        bookService.addBookToDateBase(book.getBookName(), book.getReleaseYear(), book.getPageCount(),
                                book.getISBN(), book.getPublisher(), authorId);
                        logger.debug("Book from Json was add");

                    }

                } catch (DataException e) {
                    logger.error("Json input exception: " + e.getMessage());
                }
            }
        }
    }

    private long addAuthorToDBAndReturnAuthorId(Author author) throws DataException {
        
        authorService.addAuthor(author.getName(), author.getSecondName(), author.getLastName(), author.getDob().toString());
        logger.debug("New author from Json was add to DB");
        return authorService.getByNameSecondNameLastNameDob(author.getName(), author.getSecondName(), author.getLastName(), author.getDob().toString()).getAuthorId();
    }

}
