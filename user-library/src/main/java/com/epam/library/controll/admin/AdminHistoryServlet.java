package com.epam.library.controll.admin;

import com.epam.library.businesslayer.LogServiceImpl;
import com.epam.library.businesslayer.interfaces.ILogService;
import com.epam.library.exception.DataException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/library/admin/history")
public class AdminHistoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            try {
                req.removeAttribute("result");
                req.setAttribute("logs", "show");
                ILogService logService = new LogServiceImpl();
                req.setAttribute("loglist", logService.getAllLogs());
                req.getRequestDispatcher("/Admin.jsp").forward(req, resp);
            } catch (DataException e) {
                req.setAttribute("result",e.getMessage());
                req.getRequestDispatcher("/Admin.jsp").forward(req, resp);
            }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req, resp);
    }
}
