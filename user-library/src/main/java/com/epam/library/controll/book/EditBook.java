package com.epam.library.controll.book;

import com.epam.library.businesslayer.BookServiceImpl;
import com.epam.library.businesslayer.LogServiceImpl;
import com.epam.library.businesslayer.interfaces.IBookService;
import com.epam.library.businesslayer.interfaces.ILogService;
import com.epam.library.exception.DataException;
import com.epam.library.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/library/editingbook")
public class EditBook extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            IBookService bookService = new BookServiceImpl();
            long bookId = Long.parseLong(req.getParameter("bookId"));
            req.setAttribute("book", bookService.getBook(bookId));
            req.getRequestDispatcher("/buttonpages/activity/EditBook.jsp").forward(req, resp);
        } catch (DataException | NumberFormatException e) {
            req.setAttribute("result", e.getMessage());
            req.getRequestDispatcher("/buttonpages/activity/EditBook.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ILogService logService = new LogServiceImpl();
        User user = (User) req.getSession().getAttribute("user");
        req.removeAttribute("result");
        try {
            long bookId = Long.parseLong(req.getParameter("bookId"));
            String bookName = req.getParameter("bookName");
            int releaseYear = Integer.parseInt(req.getParameter("releaseYear"));
            int pageCount = Integer.parseInt(req.getParameter("pageCount"));
            String ISBN = req.getParameter("bookISBN");
            String publisher = req.getParameter("publisher");
            long authorId = Long.parseLong(req.getParameter("authorId"));
            boolean isTaken = Boolean.parseBoolean(req.getParameter("isTaken"));

            IBookService bookService = new BookServiceImpl();

            bookService.editingBook(bookId,bookName, releaseYear, pageCount, ISBN, publisher, authorId,isTaken);
            req.setAttribute("result", "Book successfully edited");
            logService.addLog(user,"Book successfully edited by user" +user.getNickName());
            req.getRequestDispatcher("/buttonpages/activity/AddBook.jsp").forward(req, resp);

        } catch (DataException | NumberFormatException e) {
            req.setAttribute("result", e.getMessage());
            req.getRequestDispatcher("/buttonpages/activity/AddBook.jsp").forward(req, resp);
        }

    }
}
