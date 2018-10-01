/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.mysql;

import com.dao.IUsuarioDAO;
import com.models.Curso;
import com.models.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rene
 */
public class MySQLUsuarioDAO implements IUsuarioDAO {
    final String INSERT = "INSERT INTO users(nickname, password) VALUES(?, ?)";
    final String UPDATE = "UPDATE users SET nickname = ? AND password = ? WHERE id = ?";
    final String DELETE = "DELETE FROM usuarios WHERE id = ?";
    final String GETALL = "SELECT id, nickname, password, role FROM users";
    final String GETONE = "SELECT id, nickname, password, role FROM users WHERE id = ?";
    
    private Connection cn;
    
    public MySQLUsuarioDAO(Connection cn) {
        this.cn = cn;
    }
    
    private Usuario convertir(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        String nickname = rs.getString("nickname");
        String password = rs.getString("password");
        String role = rs.getString("role");
        
        Usuario u = new Usuario(nickname, password, role);
        u.setId(id);
        
        return u;
    }
    
    @Override
    public void insertar(Usuario u) throws SQLException {
        PreparedStatement pstmt = null;
        
        pstmt = cn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, u.getNickname());
        pstmt.setString(2, u.getPassword());
        pstmt.setString(3, u.getRole());
        pstmt.executeUpdate();
        
        ResultSet rs = pstmt.getGeneratedKeys();
        
        if(rs.next()) {
            u.setId(rs.getInt(1));
        }

        rs.close();
        pstmt.close();
    }

    @Override
    public void modificar(Usuario u) throws SQLException {
        PreparedStatement pstmt;
        
        pstmt = cn.prepareStatement(UPDATE);
        pstmt.setString(1, u.getNickname());
        pstmt.setString(2, u.getPassword());
        pstmt.setInt(3, u.getId());
        pstmt.executeUpdate();
        
        pstmt.close();
    }

    @Override
    public void eliminar(Usuario u) throws SQLException {
        PreparedStatement pstmt = null;
        pstmt = cn.prepareStatement(DELETE);
        
        pstmt.setInt(1, u.getId());
        
        pstmt.executeUpdate();
        pstmt.close();
    }

    @Override
    public List<Usuario> obtenerTodos() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<>();
        
        pstmt = cn.prepareStatement(GETALL);
        rs = pstmt.executeQuery();
        
        while(rs.next()) {
            usuarios.add(convertir(rs));
        }
        
        rs.close();
        pstmt.close();
        
        return usuarios;
    }

    @Override
    public Usuario obtener(Integer id) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Usuario u = null;
        
        pstmt = cn.prepareStatement(GETONE);
        pstmt.setInt(1, id);
        rs = pstmt.executeQuery();
        
        if(rs.next()) {
            u = convertir(rs);
        }
        
        rs.close();
        pstmt.close();
        
        return u;
    }
}
