package com.connetion;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Db {

    public static Connection getConnection() {
        Connection cn = null;
        
        try {
            Context initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:/comp/env/jdbc/connectionMySQL");

            cn = ds.getConnection();
            System.out.println("Obteniendo conexion");
        } catch (SQLException | NamingException ex) {
            System.out.println(ex.getMessage());
        }

        return cn;
    }
}
