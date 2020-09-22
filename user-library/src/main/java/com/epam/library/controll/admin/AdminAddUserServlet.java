package com.epam.library.controll.admin;

import com.epam.library.businesslayer.SystemExploiter;
import com.epam.library.businesslayer.interfaces.IAdminFeatures;
import com.epam.library.exception.UserException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/library/admin/register")
public class AdminAddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            req.setAttribute("adduser", "show");
            req.getRequestDispatcher("/Admin.jsp").forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            req.removeAttribute("result");

            IAdminFeatures userFeatures = new SystemExploiter();
            try {
                userFeatures.toRegister(req.getParameter("firstname"), req.getParameter("lastname"),
                req.getParameter("nickname"), req.getParameter("password"),
                Integer.parseInt(req.getParameter("roleId")));
                req.setAttribute("result", "User successfully added");
                req.getRequestDispatcher("/Admin.jsp").forward(req, resp);
            } catch (UserException | NumberFormatException e) {
                req.setAttribute("result", e.getMessage());
                req.getRequestDispatcher("/Admin.jsp").forward(req, resp);
            }


    }
}
