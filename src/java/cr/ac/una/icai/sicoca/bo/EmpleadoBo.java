/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sicoca.bo;

import cr.ac.una.icai.sicoca.clases.Empleado;
import cr.ac.una.icai.sicoca.dao.EmpleadoDao;
import java.util.List;

/**
 *
 * @author enzoq
 */
public class EmpleadoBo {

    private EmpleadoDao dao;

    public EmpleadoBo() {
        dao = new EmpleadoDao();
    }

    public Integer insert(Empleado obj) {
        return getDao().insert(obj);
    }

    public Integer delete(Empleado obj) {
        return getDao().delete(obj);
    }

    public Integer update(Empleado obj) {
        return getDao().update(obj);
    }

    public Empleado getById(Integer id) {
        return getDao().getById(id);
    }

    public List<Empleado> getByName(String name) {
        return getDao().getByName(name);
    }

    public List<Empleado> getAll() {
        return getDao().getAll();
    }

    public EmpleadoDao getDao() {
        return dao;
    }

    public void setDao(EmpleadoDao dao) {
        this.dao = dao;
    }
}
