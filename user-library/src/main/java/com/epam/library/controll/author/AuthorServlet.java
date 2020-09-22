package com.epam.library.controll.author;

import com.epam.library.businesslayer.AuthorServiceImpl;
import com.epam.library.businesslayer.interfaces.IAuthorService;
import com.epam.library.exception.DataException;
import com.epam.library.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/library/author")
public class AuthorServlet extends HttpServlet  {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


            User user = (User)req.getSession().getAttribute("user");
            req.setAttribute("user", user);
            IAuthorService authorService = new AuthorServiceImpl();
            try {
                req.setAttribute("authorlist",authorService.getAll());
            } catch (DataException e) {
                e.printStackTrace();
            }
            req.getRequestDispatcher("/Author.jsp").forward(req, resp);

    }
}
