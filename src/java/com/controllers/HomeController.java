package com.controllers;

import com.dao.mysql.MySQLDaoManager;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HomeController {
    private ServletContext sc;
    private Connection cn;
    private MySQLDaoManager manager;

    public HomeController(ServletContext sc) {
        this.sc = sc;
    }

    public HomeController(ServletContext sc, Connection cn) {
        this.sc = sc;
        this.cn = cn;
        this.manager = new MySQLDaoManager(cn);
    }

    public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        response.getWriter().println("Controlador Home");
        
        if (session.getAttribute("nickname") == null) {
            response.sendRedirect(request.getContextPath() + "/app/Login");
        } else {
            sc.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
        }

    }
}
