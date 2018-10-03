package com.controllers;

import com.connetion.Db;
import com.dao.mysql.MySQLDaoManager;
import com.models.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.regex.Pattern;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Route extends HttpServlet {

    private ServletContext sc;
    private String action;

    private final String[] routes = {
        "^/Login$",
        "^/Login/index$",
        "^/Login/validate$",
        "^/Login/logout$",
        "^/Login/[0-9]+$",
        "^/Home$",
        "^/Home/index$",
        "^/Usuario$",
        "^/Usuario/index$",
        "^/Usuario/create$",
        "^/Usuario/store$",
        "^/Usuario/destroy/[0-9]+$",
        "^/Curso$",
        "^/Curso/index$",
        "^/Curso/create$",
        "^/Curso/store$",
        "^/Curso/destroy/[0-9]+$",
        "^/Inscripcion$",
        "^/Inscripcion/index$",
        "^/Inscripcion/create$",
        "^/Inscripcion/store$",
        "^/Inscripcion/destroy/[0-9]+$"
    };

    @Override
    public void init(ServletConfig config) {
        sc = config.getServletContext();
    }

    private int validateRoute(String action) {
        int indexRoute = -1;

        for (int i = 0; i < routes.length; i++) {
            if (Pattern.matches(routes[i], action)) {
                indexRoute = i;
                break;
            }
        }

        return indexRoute;
    }

    private void actionsGet(HttpServletRequest request, HttpServletResponse response, String selectedRoute) throws ServletException, IOException {
        switch (selectedRoute) {
            case "^/Login$":
            case "^/Login/index$":
                new LoginController(sc).index(request, response);
                break;
            case "^/Login/logout$":
                new LoginController(sc).logout(request, response);
                break;
            case "^/Home$":
            case "^/Home/index$":
                new HomeController(sc).index(request, response);
                break;
            case "^/Usuario$":
            case "^/Usuario/index$":
                new UsuarioController(sc, Db.getConnection()).index(request, response);
                break;
            case "^/Usuario/create$":
                new UsuarioController(sc).create(request, response);
                break;
            case "^/Usuario/destroy/[0-9]+$":
                new UsuarioController(sc, Db.getConnection()).destroy(request, response, action.substring(action.lastIndexOf('/') + 1));
                break;
            case "^/Curso$":
            case "^/Curso/index$":
                new CursoController(sc, Db.getConnection()).index(request, response);
                break;
            case "^/Curso/create$":
                new CursoController(sc).create(request, response);
                break;
            case "^/Curso/destroy/[0-9]+$":
                new CursoController(sc, Db.getConnection()).destroy(request, response, action.substring(action.lastIndexOf('/') + 1));
                break;
            case "^/Inscripcion$":
            case "^/Inscripcion/index$":
                new InscripcionController(sc, Db.getConnection()).index(request, response);
                break;
            case "^/Inscripcion/create$":
                new InscripcionController(sc, Db.getConnection()).create(request, response);
                break;
            default:
                System.out.println("Metodo GET sin implementar");
        }
    }

    private void actionsPost(HttpServletRequest request, HttpServletResponse response, String selectedRoute) throws ServletException, IOException {
        switch (selectedRoute) {
            case "^/Login/validate$":
                new LoginController(sc, Db.getConnection()).validate(request, response);
                break;
            case "^/Usuario/store$":
                new UsuarioController(sc, Db.getConnection()).store(request, response);
                break;
            case "^/Curso/store$":
                new CursoController(sc, Db.getConnection()).store(request, response);
                break;
            case "^/Inscripcion/store$":
                new InscripcionController(sc, Db.getConnection()).store(request, response);
                break;
            default:
                System.out.println("Metodo POST sin implementar");
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        action = request.getPathInfo();
        System.out.println(action);
        int indexRoute = this.validateRoute(action);

        if (indexRoute >= 0) {
            String selectedRoute = this.routes[indexRoute];

            System.out.println("Peticion: " + request.getMethod());

            switch (request.getMethod()) {
                case "GET":
                    actionsGet(request, response, selectedRoute);
                    break;
                case "POST":
                    actionsPost(request, response, selectedRoute);
                    break;
                default:
                    System.out.println("Metodo desconocido");
            }

        } else {
            //response.sendError(HttpServletResponse.SC_NOT_FOUND);
            System.out.println("error 404");
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
}
