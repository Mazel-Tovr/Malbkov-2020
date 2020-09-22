package com.epam.library.controll.filter;

import com.epam.library.model.EnumRole;
import com.epam.library.model.User;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.*;

@WebFilter(urlPatterns = {"/library/admin","/library/admin/*"},initParams = @WebInitParam(name = "active",value = "true"))
public class AdminFilter implements Filter {
    private FilterConfig config = null;
    private boolean active = false;

    public void init (FilterConfig config) throws ServletException
    {
        this.config = config;
        String act = config.getInitParameter("active");
        if (act != null)
            active = (act.toUpperCase().equals("TRUE"));
    }

    public void doFilter (ServletRequest request, ServletResponse response,
                          FilterChain chain) throws IOException, ServletException
    {
        if (active) {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpSession session = req.getSession(false);
            if(session == null)  throw new ServletException("You shall not pass!");
            User user = (User) session.getAttribute("user");
            if(user == null)  throw new ServletException("You shall not pass!");
            if(!user.getRole().getType().equals(EnumRole.Admin)) throw new ServletException("You shall not pass!");
                chain.doFilter(request, response);//You shall pass!

        }
    }

    public void destroy()
    {
        config = null;
    }

}
