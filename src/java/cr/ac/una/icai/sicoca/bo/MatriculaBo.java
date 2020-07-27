/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sicoca.bo;

import cr.ac.una.icai.sicoca.clases.Matricula;
import cr.ac.una.icai.sicoca.dao.MatriculaDao;
import java.util.List;

/**
 *
 * @author enzoq
 */
public class MatriculaBo {

    private MatriculaDao dao;

    public MatriculaBo() {
        dao = new MatriculaDao();
    }

    public Integer insert(Matricula obj) {
        return getDao().insert(obj);
    }

    public Integer update(Matricula obj) {
        return getDao().update(obj);
    }

    public List<Matricula> getAll() {
        return getDao().getAll();
    }

    public List<Matricula> getByCourse(Matricula obj) {
        return getDao().getByCourse(obj);
    }

    public MatriculaDao getDao() {
        return dao;
    }

    public void setDao(MatriculaDao dao) {
        this.dao = dao;
    }
}
