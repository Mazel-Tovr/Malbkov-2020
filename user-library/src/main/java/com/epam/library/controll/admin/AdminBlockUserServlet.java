package com.epam.library.controll.admin;

import com.epam.library.businesslayer.LogServiceImpl;
import com.epam.library.businesslayer.SystemExploiter;
import com.epam.library.businesslayer.interfaces.IAdminFeatures;
import com.epam.library.businesslayer.interfaces.ILogService;
import com.epam.library.exception.UserException;
import com.epam.library.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/library/admin/block")
public class AdminBlockUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("blockuser", "show");
        req.getRequestDispatcher("/Admin.jsp").forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ILogService logService = new LogServiceImpl();
        User user = (User) req.getSession().getAttribute("user");
        req.removeAttribute("result");

        IAdminFeatures userFeatures = new SystemExploiter();
        try {
            userFeatures.blockUser(Integer.parseInt(req.getParameter("userId")));
            req.setAttribute("result", "User successfully blocked");

            logService.addLog(user, "User" + req.getParameter("userId") + " successfully blocked by user" + user.getNickName());

            req.getRequestDispatcher("/Admin.jsp").forward(req, resp);
        } catch (UserException | NumberFormatException e) {
            req.setAttribute("result", e.getMessage());
            req.getRequestDispatcher("/Admin.jsp").forward(req, resp);
        }
//            if(SecurityValidation.isAdmin(req))
//            {
//        }else {
//            resp.sendRedirect(req.getContextPath());
//        }

    }
}
