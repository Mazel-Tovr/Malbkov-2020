package com.epam.library.controll.bookmark;

import com.epam.library.businesslayer.BookmarkImpl;
import com.epam.library.businesslayer.LogServiceImpl;
import com.epam.library.businesslayer.interfaces.IBookmarkService;
import com.epam.library.businesslayer.interfaces.ILogService;
import com.epam.library.exception.DataException;
import com.epam.library.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/library/bookmark/deletebookmark")
public class DeleteBookmarkServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {

            req.getRequestDispatcher("/Bookmark.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        ILogService logService = new LogServiceImpl();

            try {
                User user = (User)req.getSession().getAttribute("user");
                long bookmarkId = Long.parseLong(req.getParameter("bookmarkId"));
                req.removeAttribute("result");
                IBookmarkService bookmarkService = new BookmarkImpl();
                bookmarkService.dropBookmark(bookmarkId);
                req.setAttribute("result","Bookmark successfully deleted");
                logService.addLog(user,"Bookmark successfully deleted by user" +user.getNickName());
                req.setAttribute("bookmarklist",bookmarkService.getAllUserBookMark( user.getUserId()));

                req.getRequestDispatcher("/Bookmark.jsp").forward(req, resp);

            } catch (DataException | NumberFormatException e ) {
                req.setAttribute("result",e.getMessage());
                req.getRequestDispatcher("/Bookmark.jsp").forward(req, resp);
            }

    }
}
