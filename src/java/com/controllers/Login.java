package com.controllers;

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
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email.equals("raraujo@unap.edu.pe") && password.equalsIgnoreCase("123")) {
            request.setAttribute("estado", "La conexion se realizo con exito!");
            session.setAttribute("user", "Rene Araujo");
            response.sendRedirect(request.getContextPath() + "/Home");
        } else {
            request.setAttribute("estado", "Las credenciales son incorrectas");

            getServletContext().getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(request, response);
        }
    }
}
