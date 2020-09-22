package com.epam.library.controll.bookmark;

import com.epam.library.businesslayer.BookmarkImpl;
import com.epam.library.businesslayer.interfaces.IBookmarkService;
import com.epam.library.exception.DataException;
import com.epam.library.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/library/bookmark")
public class BookmarkServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


            User user = (User)req.getSession().getAttribute("user");
            req.setAttribute("user", user);
            IBookmarkService bookmarkService = new BookmarkImpl();
            try {
                req.setAttribute("bookmarklist",bookmarkService.getAllUserBookMark( user.getUserId()));
            } catch (DataException ignore) {
              //  e.printStackTrace();
            }
            req.getRequestDispatcher("/Bookmark.jsp").forward(req, resp);

    }
}
