package com.controllers;

import com.connetion.Db;
import com.dao.mysql.MySQLDaoManager;
import com.models.Usuario;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController {

    private ServletContext sc;
    private Connection cn;
    private MySQLDaoManager manager;

    public LoginController(ServletContext sc) {
        this.sc = sc;
    }

    public LoginController(ServletContext sc, Connection cn) {
        this.sc = sc;
        this.cn = cn;
        this.manager = new MySQLDaoManager(cn);
    }

    public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("Controlador Login");
        sc.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(request, response);

    }

    public void validate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String nickname = request.getParameter("nickname");
        String password = request.getParameter("password");

        Usuario u;

        try {
            u = manager.getUsuarioDAO().getByCredentials(nickname, password);
            cn.close();
            
            if (u != null) {
                System.out.println(u.toString());
                request.setAttribute("estado", "La conexion se realizo con exito!");
                session.setAttribute("nickname", u.getNickname());
                session.setAttribute("role", u.getRole());
    
                response.sendRedirect(request.getContextPath() + "/app/Home");
                //sc.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
            } else {
                request.setAttribute("estado", "Las credenciales son incorrectas");
                response.getWriter().println("Existen errores enlas sesion");
                
                sc.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();

            request.setAttribute("estado", "Vuelva pronto!");

            // sc.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(request, response);
            response.sendRedirect(request.getContextPath() + "/app/Login");
        } else {
            response.sendRedirect(request.getContextPath() + "/Login");
        }
    }

}
