/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.mysql;

import com.dao.ICursoDAO;
import com.models.Curso;
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
public class MySQLCursoDAO implements ICursoDAO {
    final String INSERT = "INSERT INTO courses(description) VALUES(?)";
    final String UPDATE = "UPDATE courses SET description = ? WHERE id = ?";
    final String DELETE = "DELETE FROM courses WHERE id = ?";
    final String GETALL = "SELECT id, description FROM courses";
    final String GETONE = "SELECT id, description FROM courses WHERE id = ?";
    
    private Connection cn;
    
    public MySQLCursoDAO(Connection cn) {
        this.cn = cn;
    }
    
    private Curso convertir(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        String descripcion = rs.getString("description");
        
        Curso c = new Curso(descripcion);
        c.setId(id);
        
        return c;
    }
    
    @Override
    public void insertar(Curso c) throws SQLException {
        PreparedStatement pstmt = null;
        
        pstmt = cn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, c.getDescription());
        pstmt.executeUpdate();
        
        ResultSet rs = pstmt.getGeneratedKeys();
        
        if(rs.next()) {
            c.setId(rs.getInt(1));
        }

        rs.close();
        pstmt.close();
    }

    @Override
    public void modificar(Curso c) throws SQLException {
        PreparedStatement pstmt;
        
        pstmt = cn.prepareStatement(UPDATE);
        pstmt.setString(1, c.getDescription());
        pstmt.setInt(2, c.getId());
        pstmt.executeUpdate();
        
        pstmt.close();
    }

    @Override
    public void eliminar(Curso c) throws SQLException {
        PreparedStatement pstmt = null;
        pstmt = cn.prepareStatement(DELETE);
        
        pstmt.setInt(1, c.getId());
        
        pstmt.executeUpdate();
        pstmt.close();
    }

    @Override
    public List<Curso> obtenerTodos() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Curso> cursos = new ArrayList<>();
        
        pstmt = cn.prepareStatement(GETALL);
        rs = pstmt.executeQuery();
        
        while(rs.next()) {
            cursos.add(convertir(rs));
        }
        
        rs.close();
        pstmt.close();
        
        return cursos;
    }

    @Override
    public Curso obtener(Integer id) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Curso c = null;
        
        pstmt = cn.prepareStatement(GETONE);
        pstmt.setInt(1, id);
        rs = pstmt.executeQuery();
        
        if(rs.next()) {
            c = convertir(rs);
        }
        
        rs.close();
        pstmt.close();
        
        return c;
    }
}
