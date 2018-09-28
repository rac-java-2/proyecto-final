package com.controllers.Login;

import java.io.IOException;
import java.io.PrintWriter;
import javax.print.attribute.standard.Severity;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (null == accion) {
            this.accionPorDefault(request, response);
        } else {
            switch (accion) {
                case "formIndex":
                    break;
                case "validarSesion":
                    this.validarSesion(request, response);
                    break;
                case "terminarSesion":
                    this.terminarSesion(request, response);
                    break;
                default:
                    this.accionPorDefault(request, response);
                    break;
            }
        }
    }

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getPathInfo();
        String metodo;
        
        try {
            metodo = accion.substring(1).split("/")[0];
            
            switch(metodo) {
                case "help":
                    response.getWriter().append("metodo ayuda");
                    break;
                case "exit":
                    response.getWriter().append("metodo salida del sistema");
                    break;
                default:
                    response.getWriter().append("desconocido");
                    break;
            }
            
        } catch(NullPointerException e) {
            response.getWriter().append("indice");
        }
        
        
        // processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email.equals("raraujo@unap.edu.pe") && password.equalsIgnoreCase("123")) {
            request.setAttribute("estado", "La conexion se realizo con exito!");
            session.setAttribute("user", "Rene Araujo");
            response.sendRedirect(request.getContextPath() + "/Home");
        } else {
            request.setAttribute("estado", "Las credenciales son incorrectas");

            dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void validarSesion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    private void terminarSesion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();

        session.invalidate();
        System.out.println("invalidate");
        request.setAttribute("estado", "Vuelva pronto!");

        dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    private void accionPorDefault(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext sc = request.getServletContext();

        sc.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(request, response);
    }
}
