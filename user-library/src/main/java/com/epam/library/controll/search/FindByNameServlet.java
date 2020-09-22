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


@WebServlet("/library/findbyname")
public class FindByNameServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {

            req.getRequestDispatcher("/buttonpages/Search/SearchByName.jsp").forward(req, resp);
//            if (SecurityValidation.isSecured(req)) {  }
//        else
//        {
//            resp.sendRedirect(req.getContextPath());
//        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {

            String bookName = req.getParameter("bookName");
            req.removeAttribute("booklist");
            req.removeAttribute("error");
            IBookService bookService = new BookServiceImpl();
            try {

                req.setAttribute("booklist", bookService.searchBookByPartOfBookName(bookName));
                req.getRequestDispatcher("/buttonpages/Search/SearchByName.jsp").forward(req, resp);

            } catch (DataException e) {
                req.setAttribute("error",e.getMessage());
                req.getRequestDispatcher("/buttonpages/Search/SearchByName.jsp").forward(req, resp);
            }

    }
}

