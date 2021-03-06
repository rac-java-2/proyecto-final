package com.dao.mysql;

import com.dao.ICursoDAO;
import com.dao.IDAOManager;
import com.dao.IInscripcionDAO;
import com.dao.IUsuarioDAO;
import java.sql.Connection;

public class MySQLDaoManager implements IDAOManager {
    private Connection cn;
    
    private IUsuarioDAO usuarios = null;
    private ICursoDAO cursos = null;
    private IInscripcionDAO inscripciones = null;

    public MySQLDaoManager(Connection cn) {
        this.cn = cn;
    }

    @Override
    public ICursoDAO getCursoDAO() {
        if(cursos == null) {
            cursos = new MySQLCursoDAO(cn);
        }
        
        return cursos;
    }

    @Override
    public IUsuarioDAO getUsuarioDAO() {
        if(usuarios == null) {
            usuarios = new MySQLUsuarioDAO(cn);
        }
        
        return usuarios;
    }

    @Override
    public IInscripcionDAO getInscripcionDAO() {
        if(inscripciones == null) {
            inscripciones = new MySQLInscripcionDAO(cn);
        }
        
        return inscripciones;
    }
}
