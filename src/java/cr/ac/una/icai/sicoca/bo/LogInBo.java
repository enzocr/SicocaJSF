/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sicoca.bo;

import cr.ac.una.icai.sicoca.clases.Usuario;
import cr.ac.una.icai.sicoca.dao.LogInDao;

/**
 *
 * @author Enzo Quartino Zamora
 * <github.com/enzocr || email: enzoquartino@gmail.com>
 */
public class LogInBo {

    private LogInDao dao;

    public LogInBo() {
        this.dao = new LogInDao();
    }

    public int userExists(Usuario user) {
        return getDao().userExists(user);
    }

    public LogInDao getDao() {
        return dao;
    }

    public void setDao(LogInDao dao) {
        this.dao = dao;
    }

}
