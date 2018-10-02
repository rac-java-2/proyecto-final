package com.controllers;

import com.dao.mysql.MySQLDaoManager;
import com.models.Usuario;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UsuarioController {
    private ServletContext sc;
    private Connection cn;
    private MySQLDaoManager manager;

    public UsuarioController(ServletContext sc) {
        this.sc = sc;
    }

    public UsuarioController(ServletContext sc, Connection cn) {
        this.sc = sc;
        this.cn = cn;
        this.manager = new MySQLDaoManager(cn);
    }

    public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Usuario> usuarios;

        try {
            usuarios = manager.getUsuarioDAO().obtenerTodos();
            request.setAttribute("usuarios", usuarios);
            cn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        response.getWriter().println("Controlador Usuario");
        sc.getRequestDispatcher("/WEB-INF/pages/usuario/index.jsp").forward(request, response);        
    }

    public void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        sc.getRequestDispatcher("/WEB-INF/pages/usuario/create.jsp").forward(request, response);
    }

    public void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nickname = request.getParameter("nickname");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        Usuario u = new Usuario(nickname, password, role);

        try {
            manager.getUsuarioDAO().insertar(u);
            cn.close();
        } catch (SQLException ex) {
            System.out.println("Usuario: " + ex.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/app/Usuario");
    }

    public void destroy(HttpServletRequest request, HttpServletResponse response, String id) throws ServletException, IOException {
        try {
            Usuario u = manager.getUsuarioDAO().obtener(Integer.parseInt(id));

            manager.getUsuarioDAO().eliminar(u);
            cn.close();
        } catch (SQLException ex) {
            System.out.println("Usuario: " + ex.getMessage());
        }

        System.out.println("Redireccion al servlet /Usuario");
        response.sendRedirect(request.getContextPath() + "/app/Usuario");
    }
}
