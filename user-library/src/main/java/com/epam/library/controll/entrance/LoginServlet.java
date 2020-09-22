package com.epam.library.controll.entrance;

import com.epam.library.businesslayer.SystemExploiter;

import com.epam.library.businesslayer.interfaces.IUserFeatures;

import com.epam.library.exception.UserException;
import com.epam.library.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signin")
public class LoginServlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nickName = req.getParameter("nickname");
        String password = req.getParameter("password");
        IUserFeatures userFeatures = new SystemExploiter();
        try {
            User user = userFeatures.login(nickName,password);
            if(user!=null)
            {
                req.getSession().setAttribute("user",user);//set session
                req.getSession().setMaxInactiveInterval(900);//set 15 min alive for session
                resp.sendRedirect(req.getContextPath()+"/library");
            }
        } catch (UserException e) {
            req.setAttribute("result",e.getMessage());
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}
