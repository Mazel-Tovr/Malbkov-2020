package com.epam.library.controll.entrance;

import com.epam.library.businesslayer.SystemExploiter;
import com.epam.library.businesslayer.interfaces.IUserFeatures;
import com.epam.library.exception.UserException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signup")
public class RegistrationServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {

        req.getRequestDispatcher("/SignUp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try
        {
            req.removeAttribute("result");
            IUserFeatures userFeatures = new SystemExploiter();
            userFeatures.toRegister(req.getParameter("firstname"),req.getParameter("lastname"),
                    req.getParameter("nickname"),req.getParameter("password"));
            req.setAttribute("result","User successfully registered");
            req.getRequestDispatcher("/SignUp.jsp").forward(req, resp);
        } catch (UserException e) {
            req.setAttribute("result",e.getMessage());
            req.getRequestDispatcher("/SignUp.jsp").forward(req, resp);
        }

    }
}
