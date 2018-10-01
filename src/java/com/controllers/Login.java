package com.controllers;

import com.connetion.DbMysql;
import com.dao.ICursoDAO;
import com.dao.IUsuarioDAO;
import com.dao.mysql.MySQLCursoDAO;
import com.dao.mysql.MySQLDaoManager;
import com.dao.mysql.MySQLUsuarioDAO;
import com.models.Curso;
import com.models.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class Login extends HttpServlet {
    private DbMysql db;
    private Connection cn;

    /*
    private void redirigir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession(false);
        ServletContext sc = request.getServletContext();

        System.out.println(sc.getInitParameter("db.url"));
        System.out.println(sc.getInitParameter("db.password"));

        if (session.getAttribute("user") == null) {
            dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/pages/index.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/Home");
        }
    }
     */
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        String jdbcUrl = getServletContext().getInitParameter("jdbcUrl");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
        
        db = new DbMysql(jdbcUrl, jdbcUsername, jdbcPassword);
        
        try {
            cn = db.getConnection();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        
    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(request, response);
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
            System.out.println("invalidate");
            request.setAttribute("estado", "Vuelva pronto!");

            getServletContext().getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/Login");
        }
    }
    
    private void validate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String nickname = request.getParameter("nickname");
        String password = request.getParameter("password");
    
        MySQLDaoManager man = new MySQLDaoManager(cn);

        Usuario u;

        try {
            u = man.getUsuarioDAO().getByCredentials(nickname, password);

            if (u != null) {
                System.out.println(u.toString());
                request.setAttribute("estado", "La conexion se realizo con exito!");
                session.setAttribute("nickname", u.getNickname());
                session.setAttribute("role", u.getRole());
                response.sendRedirect(request.getContextPath() + "/Home");
            } else {
                request.setAttribute("estado", "Las credenciales son incorrectas");

                getServletContext().getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getPathInfo();
        String metodo;

        try {
            metodo = accion.substring(1).split("/")[0];

            switch (metodo) {
                case "index":
                    this.index(request, response);
                    break;
                case "help":
                    response.getWriter().append("metodo ayuda");
                    break;
                case "logout":
                    this.logout(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (NullPointerException e) {
            this.index(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getPathInfo();
        String metodo;

        try {
            metodo = accion.substring(1).split("/")[0];

            switch (metodo) {
                case "validate":
                    this.validate(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (NullPointerException e) {
            this.index(request, response);
        }
    }
}
