package com.epam.library.controll.bookmark;

import com.epam.library.businesslayer.BookmarkImpl;
import com.epam.library.businesslayer.LogServiceImpl;
import com.epam.library.businesslayer.interfaces.IBookmarkService;
import com.epam.library.businesslayer.interfaces.ILogService;
import com.epam.library.exception.DataException;
import com.epam.library.model.Bookmark;
import com.epam.library.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/library/bookmark/editingbookmark")
public class EditBookmark extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            IBookmarkService bookmarkService = new BookmarkImpl();
            long bookmarkId = Long.parseLong(req.getParameter("bookmarkId"));
            Bookmark bookmark = bookmarkService.getBookmark(bookmarkId);
            req.setAttribute("bookmark", bookmark);
            req.getRequestDispatcher("/buttonpages/activity/EditBookmark.jsp").forward(req, resp);

        } catch (DataException | NumberFormatException e) {
            req.setAttribute("result", e.getMessage());
            req.getRequestDispatcher("/buttonpages/activity/EditBookmark.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ILogService logService = new LogServiceImpl();
        req.removeAttribute("result");
        try {
            User user = (User) req.getSession().getAttribute("user");
            long bookmarkId = Long.parseLong(req.getParameter("bookmarkId"));

            long bookId = Long.parseLong(req.getParameter("bookId"));
            int pageNumber = Integer.parseInt(req.getParameter("pageNumber"));

            IBookmarkService bookmarkService = new BookmarkImpl();
            bookmarkService.editing(bookmarkId, new Bookmark(user.getUserId(), bookId, pageNumber));

            req.setAttribute("result", "Bookmark successfully edited");
            logService.addLog(user, "Bookmark successfully edited by user" + user.getNickName());
            req.getRequestDispatcher("/buttonpages/activity/EditBookmark.jsp").forward(req, resp);

        } catch (DataException | NumberFormatException e) {
            req.setAttribute("result", e.getMessage());
            req.getRequestDispatcher("/buttonpages/activity/EditBookmark.jsp").forward(req, resp);
        }

    }
}
