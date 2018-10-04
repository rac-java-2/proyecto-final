package com.controllers;

import com.dao.mysql.MySQLDaoManager;
import com.models.Curso;
import com.models.Inscripcion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
            inscripciones = manager.getInscripcionDAO().getTodos();
            
            request.setAttribute("inscripciones", inscripciones);

            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }

        sc.getRequestDispatcher("/WEB-INF/pages/inscripcion/index.jsp").forward(request, response);
    }
    
    public void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Curso> cursos;

        try {
            cursos = manager.getCursoDAO().obtenerTodos();
            request.setAttribute("cursos", cursos);

            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sc.getRequestDispatcher("/WEB-INF/pages/inscripcion/create.jsp").forward(request, response);
    }
    
    public void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String firstName = request.getParameter("first_name");
            String lastName = request.getParameter("last_name");
            String cellphone = request.getParameter("cellphone");
            Integer courseId = Integer.parseInt(request.getParameter("courses_id"));
            Double price = Double.parseDouble(request.getParameter("price"));
            Date registrationDate;
            
            try {
                registrationDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("registration_date"));  
            } catch(ParseException e) {
                System.out.println("Error al parsear fecha: " + e.getMessage());
                registrationDate = new Date();
            }
            
            Inscripcion i = new Inscripcion(firstName, lastName, cellphone, courseId, price, registrationDate);
            
            manager.getInscripcionDAO().insertar(i);
            cn.close();
        } catch (SQLException ex) {
            System.out.println("Usuario: " + ex.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/app/Inscripcion");
    }
    
        public void destroy(HttpServletRequest request, HttpServletResponse response, String id) throws ServletException, IOException {
        try {
            Inscripcion i = manager.getInscripcionDAO().obtener(Integer.parseInt(id));

            manager.getInscripcionDAO().eliminar(i);
            cn.close();
        } catch (SQLException ex) {
            System.out.println("Inscripcion: " + ex.getMessage());
        }

        System.out.println("Redireccion al servlet /Inscripcion");
        response.sendRedirect(request.getContextPath() + "/app/Inscripcion/index");
    }

    
}
