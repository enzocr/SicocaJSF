/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sicoca.bo;

import cr.ac.una.icai.sicoca.clases.Curso;
import cr.ac.una.icai.sicoca.dao.CursoDao;
import java.util.List;

/**
 *
 * @author enzoq
 */
public class CursoBo {

    private CursoDao dao;

    public CursoBo() {
        dao = new CursoDao();
    }

    public Integer insert(Curso obj) {
        return getDao().insert(obj);
    }

    public Integer delete(Curso obj) {
        return getDao().delete(obj);
    }

    public Integer update(Curso obj) {
        return getDao().update(obj);
    }

    public Curso getById(Integer id) {
        return getDao().getById(id);
    }

    public List<Curso> getByName(String name) {
        return getDao().getByName(name);
    }

    public List<Curso> getAll() {
        return getDao().getAll();
    }

    public CursoDao getDao() {
        return dao;
    }

    public void setDao(CursoDao dao) {
        this.dao = dao;
    }
}
