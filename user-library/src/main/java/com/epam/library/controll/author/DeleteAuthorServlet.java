package com.epam.library.controll.author;

import com.epam.library.businesslayer.AuthorServiceImpl;
import com.epam.library.businesslayer.LogServiceImpl;
import com.epam.library.businesslayer.interfaces.IAuthorService;
import com.epam.library.businesslayer.interfaces.ILogService;
import com.epam.library.exception.DataException;
import com.epam.library.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/library/author/deleteauthor")
public class DeleteAuthorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/Author.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ILogService logService = new LogServiceImpl();
        User user = (User) req.getSession().getAttribute("user");
        try {

            int authorId = Integer.parseInt(req.getParameter("authorId"));
            req.removeAttribute("result");
            IAuthorService authorService = new AuthorServiceImpl();
            authorService.dropAuthorAndAllHisBooks(authorId);
            req.setAttribute("authorlist", authorService.getAll());
            req.setAttribute("result", "Author successfully deleted");
            logService.addLog(user, "Author successfully deleted by user" + user.getNickName());
            req.getRequestDispatcher("/Author.jsp").forward(req, resp);

        } catch (DataException | NumberFormatException e) {
            req.setAttribute("result", e.getMessage());
            req.getRequestDispatcher("/Author.jsp").forward(req, resp);
        }

    }
}
