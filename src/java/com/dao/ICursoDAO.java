/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.models.Curso;
import java.sql.SQLException;
import java.util.List;

public interface ICursoDAO {
    void insertar(Curso c) throws SQLException;
    
    void modificar(Curso c) throws SQLException;
    
    void eliminar(Curso c) throws SQLException;
    
    List<Curso> obtenerTodos() throws SQLException;
    
    Curso obtener(Integer id) throws SQLException;
}
