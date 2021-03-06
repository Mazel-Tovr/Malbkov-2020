package com.tstu.library.controll.bookmark;

import com.tstu.library.businesslayer.BookServiceImpl;
import com.tstu.library.businesslayer.BookmarkImpl;
import com.tstu.library.businesslayer.LogServiceImpl;
import com.tstu.library.businesslayer.interfaces.IBookmarkService;
import com.tstu.library.businesslayer.interfaces.ILogService;
import com.tstu.library.exception.DataException;
import com.tstu.library.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/library/bookmark/addbookmark")
public class AddBookmarkServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long id = Long.parseLong(req.getParameter("bookId"));
            req.setAttribute("book", new BookServiceImpl().getBook(id));
            req.getRequestDispatcher("/buttonpages/activity/AddBookmark.jsp").forward(req, resp);
        } catch (DataException | NumberFormatException e) {
            req.setAttribute("result", e.getMessage());
            req.getRequestDispatcher("/buttonpages/activity/AddBookmark.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ILogService logService = new LogServiceImpl();

        req.removeAttribute("result");
        try {
            User user = (User) req.getSession().getAttribute("user");
            long bookId = Long.parseLong(req.getParameter("bookId"));
            int pageNumber = Integer.parseInt(req.getParameter("pageNumber"));

            IBookmarkService bookmarkService = new BookmarkImpl();
            bookmarkService.addBookmark(user.getUserId(), bookId, pageNumber);

            req.setAttribute("result", "Bookmark successfully added");
            logService.addLog(user," by user" +user.getNickName());
            req.getRequestDispatcher("/buttonpages/activity/AddBookmark.jsp").forward(req, resp);

        } catch (DataException | NumberFormatException e) {
            req.setAttribute("result", e.getMessage());
            req.getRequestDispatcher("/buttonpages/activity/AddBookmark.jsp").forward(req, resp);
        }

    }
}
