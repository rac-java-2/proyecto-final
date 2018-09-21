package com.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if ("validarSesion".equals(accion)) {
            this.validarSesion(request, response);
        } else if("terminarSesion".equals(accion)) {
            this.terminarSesion(request, response);
        } else {
            this.accionPorDefault(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void validarSesion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email.equals("raraujo@unap.edu.pe") && password.equalsIgnoreCase("123")) {
            request.setAttribute("estado", "La conexion se realizo con exito!");
            session.setAttribute("user", "Rene Araujo");            
            // dispatcher = getServletContext().getRequestDispatcher("/Home");
            // dispatcher.forward(request, response);
            response.sendRedirect(request.getContextPath() + "/Home");
        } else {
            request.setAttribute("estado", "Las credenciales son incorrectas");
            
            dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    private void terminarSesion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();
        
        session.invalidate();
        
        request.setAttribute("estado", "Vuelva pronto!");
        
        dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }
    
    private void accionPorDefault(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        
        pw.write("Accion incorrecta");
    }
}
