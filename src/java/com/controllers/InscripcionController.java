package com.controllers;

import com.dao.mysql.MySQLDaoManager;
import com.models.Curso;
import com.models.Inscripcion;
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

public class InscripcionController {

    private ServletContext sc;
    private Connection cn;
    private MySQLDaoManager manager;

    public InscripcionController(ServletContext sc) {
        this.sc = sc;
    }

    public InscripcionController(ServletContext sc, Connection cn) {
        this.sc = sc;
        this.cn = cn;
        this.manager = new MySQLDaoManager(cn);
    }

    public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Inscripcion> inscripciones;

        try {
            inscripciones = manager.getInscripcionDAO().obtenerTodos();
            request.setAttribute("inscripciones", inscripciones);

            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }

        sc.getRequestDispatcher("/WEB-INF/pages/inscripcion/index.jsp").forward(request, response);
    }
    
}
