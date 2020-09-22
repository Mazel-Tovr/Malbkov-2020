package com.tstu.library.controll.book;

import com.tstu.library.businesslayer.BookServiceImpl;
import com.tstu.library.businesslayer.LogServiceImpl;
import com.tstu.library.businesslayer.interfaces.IBookService;
import com.tstu.library.businesslayer.interfaces.ILogService;
import com.tstu.library.exception.DataException;
import com.tstu.library.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/library/addbook")
public class AddBookServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {

            req.getRequestDispatcher("/buttonpages/activity/AddBook.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        ILogService logService = new LogServiceImpl();
        User user = (User) req.getSession().getAttribute("user");
            try {

                String bookName = req.getParameter("bookName");
                int releaseYear = Integer.parseInt(req.getParameter("releaseYear"));
                int pageCount = Integer.parseInt(req.getParameter("pageCount"));
                String ISBN = req.getParameter("bookISBN");
                String publisher = req.getParameter("publisher");
                long authorId = Long.parseLong(req.getParameter("authorId"));
                req.removeAttribute("result");
                IBookService bookService = new BookServiceImpl();

                bookService.addBookToDateBase(bookName,releaseYear,pageCount,ISBN,publisher,authorId);
                logService.addLog(user,"Book successfully added by User "+user.getNickName() );
                req.setAttribute("result","Book successfully added");
                req.getRequestDispatcher("/buttonpages/activity/AddBook.jsp").forward(req, resp);

            } catch (DataException | NumberFormatException e ) {
                req.setAttribute("result",e.getMessage());
                req.getRequestDispatcher("/buttonpages/activity/AddBook.jsp").forward(req, resp);
            }

    }
}
