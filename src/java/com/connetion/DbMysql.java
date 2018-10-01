/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connetion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Rene
 */
public class DbMysql {
    private String url;
    private String username;
    private String password;
    private Connection cn;
    
    public DbMysql(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
    
    public void connection() throws SQLException {
        if(this.cn == null || this.cn.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                cn = DriverManager.getConnection(url, this.username, this.password);
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public void discconect() throws SQLException {
        if(this.cn == null && !cn.isClosed()) {
            cn.close();
        }
    }
    
    public Connection getConnection() throws SQLException {
        if(this.cn == null || this.cn.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                cn = DriverManager.getConnection(url, this.username, this.password);
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        
        return this.cn;
    }
}
