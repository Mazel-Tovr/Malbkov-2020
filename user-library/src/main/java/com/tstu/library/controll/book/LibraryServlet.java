package com.tstu.library.controll.book;

import com.tstu.library.businesslayer.BookServiceImpl;
import com.tstu.library.businesslayer.interfaces.IBookService;
import com.tstu.library.exception.DataException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/library")
public class LibraryServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
                IBookService bookService = new BookServiceImpl();
                req.setAttribute("booklist", bookService.getAllNotTaken());
                req.getRequestDispatcher("/Library.jsp").forward(req, resp);

        } catch (DataException e) {
            resp.sendRedirect(req.getContextPath());
        }


    }
}
