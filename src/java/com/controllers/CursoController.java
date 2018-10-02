/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.dao.mysql.MySQLDaoManager;
import com.models.Curso;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CursoController {

    private ServletContext sc;
    private Connection cn;
    private MySQLDaoManager manager;

    public CursoController(ServletContext sc) {
        this.sc = sc;
    }

    public CursoController(ServletContext sc, Connection cn) {
        this.sc = sc;
        this.cn = cn;
        this.manager = new MySQLDaoManager(cn);
    }

    public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Curso> cursos;

        try {
            cursos = manager.getCursoDAO().obtenerTodos();
            request.setAttribute("cursos", cursos);

            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }

        sc.getRequestDispatcher("/WEB-INF/pages/curso/index.jsp").forward(request, response);
    }

    public void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        sc.getRequestDispatcher("/WEB-INF/pages/curso/create.jsp").forward(request, response);
    }

    public void destroy(HttpServletRequest request, HttpServletResponse response, String id) throws ServletException, IOException {
        try {
            Curso c = manager.getCursoDAO().obtener(Integer.parseInt(id));

            manager.getCursoDAO().eliminar(c);
            cn.close();
        } catch (SQLException ex) {
            System.out.println("Usuario: " + ex.getMessage());
        }

        System.out.println("Redireccion al servlet /Curso");
        response.sendRedirect(request.getContextPath() + "/app/Curso");
    }

    public void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String description = request.getParameter("description");

        Curso c = new Curso(description);

        try {
            manager.getCursoDAO().insertar(c);
            cn.close();
        } catch (SQLException ex) {
            System.out.println("Usuario: " + ex.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/app/Curso");
    }
}
