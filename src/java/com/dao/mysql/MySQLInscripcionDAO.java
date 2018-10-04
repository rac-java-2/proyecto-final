package com.dao.mysql;

import com.dao.IInscripcionDAO;
import com.models.Curso;
import com.models.Inscripcion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQLInscripcionDAO implements IInscripcionDAO {
    final String INSERT = "INSERT INTO inscriptions(first_name, last_name, cellphone, courses_id, price, registration_date) VALUES(?, ?, ?, ?, ?, ?)";
    final String UPDATE = "UPDATE inscriptions SET first_name = ?, last_name = ?, cellphone = ?, courses_id = ?, price = ?, registration_date = ? WHERE id = ?";
    final String DELETE = "DELETE FROM inscriptions WHERE id = ?";
    final String GETALL = "SELECT id, first_name, last_name, cellphone, courses_id, price, registration_date FROM inscriptions";
    final String GETONE = "SELECT id, first_name, last_name, cellphone, courses_id, price, registration_date FROM inscriptions WHERE id = ?";
    
    private Connection cn;
    
    public MySQLInscripcionDAO(Connection cn) {
        this.cn = cn;
    }
    
    private Inscripcion convertir(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        String cellphone = rs.getString("cellphone");
        Integer courseId = rs.getInt("courses_id");
        Double price = rs.getDouble("price");
        Date registrationDate = rs.getDate("registration_date");

        Inscripcion i = new Inscripcion(firstName, lastName, cellphone, courseId, price, registrationDate);
        i.setId(id);
        
        return i;
    }
    
    @Override
    public void insertar(Inscripcion i) throws SQLException {
        PreparedStatement pstmt = null;
        
        pstmt = cn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, i.getFirstName());
        pstmt.setString(2, i.getLastName());
        pstmt.setString(3, i.getCellphone());
        pstmt.setInt(4, i.getCourseId());
        pstmt.setDouble(5, i.getPrice());
        pstmt.setDate(6, new Date(i.getRegistrationDate().getTime()));
        pstmt.executeUpdate();
        
        ResultSet rs = pstmt.getGeneratedKeys();
        
        if(rs.next()) {
            i.setId(rs.getInt(1));
        }

        rs.close();
        pstmt.close();
    }

    @Override
    public void modificar(Inscripcion i) throws SQLException {
        PreparedStatement pstmt;
        
        pstmt = cn.prepareStatement(UPDATE);
        pstmt.setString(1, i.getFirstName());
        pstmt.setString(2, i.getLastName());
        pstmt.setString(3, i.getCellphone());
        pstmt.setInt(4, i.getCourseId());
        pstmt.setDouble(5, i.getPrice());
        pstmt.setDate(6, new Date(i.getRegistrationDate().getTime()));
        pstmt.setInt(7, i.getId());
        pstmt.executeUpdate();
        
        pstmt.close();
    }

    @Override
    public void eliminar(Inscripcion i) throws SQLException {
        PreparedStatement pstmt = null;
        pstmt = cn.prepareStatement(DELETE);
        
        pstmt.setInt(1, i.getId());
        
        pstmt.executeUpdate();
        pstmt.close();
    }

    @Override
    public List<Inscripcion> getTodos() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Inscripcion> inscripciones = new ArrayList<>();
        
        pstmt = cn.prepareStatement(GETALL);
        rs = pstmt.executeQuery();
        
        while(rs.next()) {
            inscripciones.add(convertir(rs));
        }
        
        rs.close();
        pstmt.close();
        
        return inscripciones;
    }

    @Override
    public Inscripcion obtener(Integer id) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Inscripcion i = null;
        
        pstmt = cn.prepareStatement(GETONE);
        pstmt.setInt(1, id);
        rs = pstmt.executeQuery();
        
        if(rs.next()) {
            i = convertir(rs);
        }
        
        rs.close();
        pstmt.close();
        
        return i;
    }

    @Override
    public Curso obtenerCurso() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Curso c = null;
        
        pstmt = cn.prepareStatement("SELECT id, description FROM courses WHERE id = ?");
        pstmt.setInt(1, 1);
        rs = pstmt.executeQuery();
        
        if (rs.next()) {
            Integer id = rs.getInt("id");
            String description = rs.getString("description");

            c = new Curso(description);
            c.setId(id);

        }

        rs.close();
        pstmt.close();
        
        return c;
    }
}
