/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sicoca.bo;

import cr.ac.una.icai.sicoca.clases.Instructor;
import cr.ac.una.icai.sicoca.dao.InstructorDao;
import java.util.List;

/**
 *
 * @author enzoq
 */
public class InstructorBo {

    private InstructorDao dao;

    public InstructorBo() {
        dao = new InstructorDao();
    }

    public Integer insert(Instructor obj) {
        return getDao().insert(obj);
    }

    public Integer delete(Instructor obj) {
        return getDao().delete(obj);
    }

    public Integer update(Instructor obj) {
        return getDao().update(obj);
    }

    public Instructor getById(Integer id) {
        return getDao().getById(id);
    }

    public List<Instructor> getByName(String name) {
        return getDao().getByName(name);
    }

    public List<Instructor> getAll() {
        return getDao().getAll();
    }

    public InstructorDao getDao() {
        return dao;
    }

    public void setDao(InstructorDao dao) {
        this.dao = dao;
    }
}
