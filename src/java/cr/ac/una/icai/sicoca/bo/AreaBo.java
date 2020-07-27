/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sicoca.bo;

import cr.ac.una.icai.sicoca.clases.Area;
import cr.ac.una.icai.sicoca.dao.AreaDao;
import java.util.List;

/**
 *
 * @author enzoq
 */
public class AreaBo {

    private AreaDao dao;

    public AreaBo() {
        dao = new AreaDao();
    }

    public Integer insert(Area obj) {
        return getDao().insert(obj);
    }

    public Integer delete(Area obj) {
        return getDao().delete(obj);
    }

    public Integer update(Area obj) {
        return getDao().update(obj);
    }

    public Area getById(Integer id) {
        return getDao().getById(id);
    }

    public List<Area> getByName(String name) {
        return getDao().getByName(name);
    }

    public List<Area> getAll() {
        return getDao().getAll();
    }

    public AreaDao getDao() {
        return dao;
    }

    public void setDao(AreaDao dao) {
        this.dao = dao;
    }

}
