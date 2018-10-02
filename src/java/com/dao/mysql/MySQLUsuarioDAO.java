package com.dao.mysql;

import com.dao.IUsuarioDAO;
import com.models.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQLUsuarioDAO implements IUsuarioDAO {
    final String INSERT = "INSERT INTO users(nickname, password, role) VALUES(?, AES_ENCRYPT(?, UNHEX(?)), ?)";
    final String UPDATE = "UPDATE users SET nickname = ? AND password = ? WHERE id = ?";
    final String DELETE = "DELETE FROM users WHERE id = ?";
    final String GETALL = "SELECT id, nickname, password, role FROM users";
    final String GETONE = "SELECT id, nickname, password, role FROM users WHERE id = ?";
    
    private final String BY_CREDENTIALS = "SELECT id, nickname, password, role FROM users WHERE nickname = ? AND AES_DECRYPT(password, UNHEX(?)) = ?";
    
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
        pstmt.setString(3, "1E51540F");
        pstmt.setString(4, u.getRole());
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

    @Override
    public Usuario getByCredentials(String nickname, String password) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Usuario u = null;
        
        pstmt = cn.prepareStatement(BY_CREDENTIALS);
        pstmt.setString(1, nickname);
        pstmt.setString(2, "1E51540F");
        pstmt.setString(3, password);
        
        rs = pstmt.executeQuery();
        
        if(rs.next()) {
            u = convertir(rs);
        }
        
        rs.close();
        pstmt.close();
        
        return u;
    }
}
