package com.dao;

import com.models.Inscripcion;
import java.sql.SQLException;
import java.util.List;

public interface IInscripcionDAO {
    void insertar(Inscripcion i) throws SQLException;
    
    void modificar(Inscripcion i) throws SQLException;
    
    void eliminar(Inscripcion i) throws SQLException;
    
    List<Inscripcion> obtenerTodos() throws SQLException;
    
    Inscripcion obtener(Integer id) throws SQLException;    
}
