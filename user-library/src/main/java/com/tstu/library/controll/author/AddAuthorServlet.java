package com.tstu.library.controll.author;

import com.tstu.library.businesslayer.AuthorServiceImpl;
import com.tstu.library.businesslayer.LogServiceImpl;
import com.tstu.library.businesslayer.interfaces.IAuthorService;
import com.tstu.library.businesslayer.interfaces.ILogService;
import com.tstu.library.exception.DataException;
import com.tstu.library.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/library/author/addauthor")
public class AddAuthorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/buttonpages/activity/AddAuthor.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ILogService logService = new LogServiceImpl();
        User user = (User) req.getSession().getAttribute("user");

        try {

            String authorName = req.getParameter("authorName");
            String secondName = req.getParameter("secondName");
            String lastName = req.getParameter("lastName");
            String dob = req.getParameter("dob");
            IAuthorService authorService = new AuthorServiceImpl();
            req.removeAttribute("result");
            authorService.addAuthor(authorName, secondName, lastName, dob);

            req.setAttribute("result", "Author successfully added");
            logService.addLog(user, "Author successfully added by user" + user.getNickName());
            req.getRequestDispatcher("/buttonpages/activity/AddAuthor.jsp").forward(req, resp);

        } catch (DataException | NumberFormatException e) {
            req.setAttribute("result", e.getMessage());
            req.getRequestDispatcher("/buttonpages/activity/AddAuthor.jsp").forward(req, resp);
        }

    }
}
