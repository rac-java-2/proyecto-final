/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.models.Usuario;
import java.sql.SQLException;
import java.util.List;

public interface IUsuarioDAO {
    void insertar(Usuario u) throws SQLException;
    
    void modificar(Usuario u) throws SQLException;
    
    void eliminar(Usuario u) throws SQLException;
    
    List<Usuario> obtenerTodos() throws SQLException;
    
    Usuario obtener(Integer id) throws SQLException;

}
