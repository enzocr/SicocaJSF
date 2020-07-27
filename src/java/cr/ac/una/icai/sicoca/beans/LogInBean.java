/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sicoca.beans;

import cr.ac.una.icai.sicoca.bo.LogInBo;
import cr.ac.una.icai.sicoca.clases.Curso;
import cr.ac.una.icai.sicoca.clases.Usuario;
import cr.ac.una.icai.sicoca.utilidades.Utilidades;
import java.io.IOException;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author enzoq
 */
public class LogInBean {

    private Usuario user;
    private LogInBo bo;
    private boolean modificando;

    public LogInBean() {
        this.user = new Usuario();
        this.bo = new LogInBo();
        this.modificando = false;
    }

    public String userExists() {
        if (validateData()) {
            int res = getBo().userExists(user);
            switch (res) {
                case 1:
                    return goToIndex();
                case 2:
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, Utilidades.obtenerMsj("msj.infoIncorrect"), ""));
                    break;
                case 3:
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, Utilidades.obtenerMsj("msj.infoNotFound"), ""));
                    break;
                case 4:
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, Utilidades.obtenerMsj("msj.cantConnBD"), ""));
                    break;
                case 5:
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, Utilidades.obtenerMsj("msj.error"), ""));
                    break;
                default:
                    break;
            }

        }

        return "";
    }

    public String goToIndex() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml?init=true");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "index";
    }

    public boolean validateData() {
        boolean flag = true;
        if (user.getNombreUsuario().trim().length() == 0) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:txtUsername",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.username")));
        }
        if (user.getPassword().trim().length() == 0) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:txtPass",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.pass")));
        }

        return flag;
    }
    
    public String clean() {
        this.restartBean();
        return "";
    }

    private void restartBean() {
        this.user = new Usuario();
        this.modificando = false;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public LogInBo getBo() {
        return bo;
    }

    public void setBo(LogInBo bo) {
        this.bo = bo;
    }

    public boolean isModificando() {
        return modificando;
    }

    public void setModificando(boolean modificando) {
        this.modificando = modificando;
    }

}
