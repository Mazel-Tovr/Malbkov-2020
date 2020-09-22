package com.epam.library.controll.search;

import com.epam.library.businesslayer.BookServiceImpl;
import com.epam.library.businesslayer.interfaces.IBookService;
import com.epam.library.exception.DataException;
import com.epam.library.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/library/findusersbookmark")
public class FindByUsersBookmarkServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            req.removeAttribute("booklist");
            req.removeAttribute("error");
            User user = (User) req.getSession().getAttribute("user");
            IBookService bookService = new BookServiceImpl();
            try {
                req.setAttribute("booklist", bookService.searchBookWhereUserBookMark(user.getUserId()));
                req.getRequestDispatcher("/buttonpages/Search/SearchByUsersBookmark.jsp").forward(req, resp);
            } catch (DataException e) {
                req.setAttribute("error", e.getMessage());
                req.getRequestDispatcher("/buttonpages/Search/SearchByUsersBookmark.jsp").forward(req, resp);
            }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
