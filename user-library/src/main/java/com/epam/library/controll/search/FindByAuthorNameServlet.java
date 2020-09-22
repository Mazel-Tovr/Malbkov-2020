package com.epam.library.controll.search;

import com.epam.library.businesslayer.BookServiceImpl;
import com.epam.library.businesslayer.interfaces.IBookService;
import com.epam.library.exception.DataException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/library/findbyauthorname")
public class FindByAuthorNameServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {

            req.getRequestDispatcher("/buttonpages/Search/SearchByAuthorName.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {

            String authorName = req.getParameter("authorName");
            req.removeAttribute("booklist");
            req.removeAttribute("error");
            IBookService bookService = new BookServiceImpl();
            try {

                req.setAttribute("booklist", bookService.searchBookByPartOfAuthorName(authorName));
                req.getRequestDispatcher("/buttonpages/Search/SearchByAuthorName.jsp").forward(req, resp);

            } catch (DataException e) {
                req.setAttribute("error",e.getMessage());
                req.getRequestDispatcher("/buttonpages/Search/SearchByAuthorName.jsp").forward(req, resp);
            }

    }
}
