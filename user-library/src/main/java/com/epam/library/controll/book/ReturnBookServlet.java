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

@WebServlet("/library/returnbook")
public class ReturnBookServlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ILogService logService = new LogServiceImpl();
        User user = (User) req.getSession().getAttribute("user");
        try {
            long bookId = Long.parseLong(req.getParameter("bookId"));

            req.removeAttribute("result");
            IBookService bookService = new BookServiceImpl();

            bookService.returnBook(bookId);

            req.setAttribute("booklist", bookService.getAllNotTaken());
            req.setAttribute("result","Book successfully return");
            logService.addLog(user,user.getNickName() +" return book with id "+bookId);
            req.getRequestDispatcher("/Library.jsp").forward(req, resp);

        } catch (DataException | NumberFormatException e ) {
            req.setAttribute("result",e.getMessage());
            logService.addLog(user,user.getNickName() +" "+e.getMessage());
            req.getRequestDispatcher("/Library.jsp").forward(req, resp);
        }
    }
}
