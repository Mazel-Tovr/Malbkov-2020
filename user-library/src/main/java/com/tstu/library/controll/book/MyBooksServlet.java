package com.tstu.library.controll.book;

import com.tstu.library.businesslayer.BookServiceImpl;
import com.tstu.library.businesslayer.interfaces.IBookService;
import com.tstu.library.exception.DataException;
import com.tstu.library.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/library/mybooks")
public class MyBooksServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
        IBookService bookService = new BookServiceImpl();
        User user = (User) req.getSession().getAttribute("user");
        req.setAttribute("booklist",bookService.getAllTakenByUser(user.getUserId()));
        req.getRequestDispatcher("/MyBooks.jsp").forward(req, resp);
        } catch (DataException e) {
            req.setAttribute("result",e.getMessage());
            req.getRequestDispatcher("/MyBooks.jsp").forward(req, resp);
        }

    }
}
