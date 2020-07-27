/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sicoca.beans;

import cr.ac.una.icai.sicoca.bo.AreaBo;
import cr.ac.una.icai.sicoca.clases.Area;
import cr.ac.una.icai.sicoca.utilidades.Utilidades;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author enzoq
 */
public class AreaBean {

    private Area area;
    private AreaBo bo;
    private List<Area> lista;
    private boolean modificando;

    public AreaBean() {
        this.area = new Area();
        this.bo = new AreaBo();
        this.modificando = false;
        this.lista = new ArrayList<>();
    }

    public String insert() {
        if (validateData()) {
            int res = this.bo.insert(area);

            switch (res) {
                case 0:
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, Utilidades.obtenerMsj("msj.insertedSuccessfully"), ""));
                    break;
                case 1:
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, Utilidades.obtenerMsj("msj.cantConnBD"), ""));
                    break;
                case 2:
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, Utilidades.obtenerMsj("msj.error"), ""));
                    break;
                case 3:
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, Utilidades.obtenerMsj("msj.alreadyExists"), ""));
                    break;
            }
            restartBean();

        }
        return "";
    }

    public String update() {
        if (validateData()) {
            int res = this.bo.update(area);

            switch (res) {
                case 0:
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, Utilidades.obtenerMsj("msj.updatedSuccessfully"), ""));
                    break;
                case 1:
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, Utilidades.obtenerMsj("msj.cantConnBD"), ""));
                    break;
                case 2:
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, Utilidades.obtenerMsj("msj.error"), ""));
                    break;
                case 3:
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, Utilidades.obtenerMsj("msj.noDataUpdated"), ""));
                    break;
            }
            restartBean();
        }
        return "";
    }

    public String delete(Area obj) {
        int res = this.bo.delete(obj);
        switch (res) {
            case 0:
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Utilidades.obtenerMsj("msj.deletedSuccessfully"), ""));
                break;
            case 1:
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Utilidades.obtenerMsj("msj.cantConnBD"), ""));
                break;
            case 2:
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Utilidades.obtenerMsj("msj.error"), ""));
                break;
        }
        restartBean();
        return "";
    }

    public String select(Area obj) {
        this.area = obj;
        this.modificando = true;
        return "";
    }

    public List<SelectItem> fillAreas() {
        List<SelectItem> itemList = new ArrayList<>();
        List<Area> objs = this.bo.getAll();
        itemList.add(new SelectItem(0, Utilidades.obtenerMsj("select.option")));
        for (int i = 0; i < objs.size(); i++) {
            Area object = objs.get(i);
            itemList.add(new SelectItem(object.getIdArea(), object.getNombre()));
        }
        return itemList;
    }

    public String getById() {
        if (area.getIdArea() == null) {
            FacesContext.getCurrentInstance().addMessage("form:txtIdArea",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.id")));
        } else {
            Area obj = bo.getById(this.area.getIdArea());
            if (obj == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL, "", Utilidades.obtenerMsj("msj.infoNotFound")));
            } else {
                this.lista.clear();
                this.lista.add(obj);
            }
        }
        return "";
    }

    public String getByName() {
        if (this.area.getNombre().trim().length() == 0) {
            FacesContext.getCurrentInstance().addMessage("form:txtNombre",
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "", Utilidades.obtenerMsj("validate.name")));
        } else {
            this.lista = this.bo.getByName(this.area.getNombre());
            if (this.lista.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL, Utilidades.obtenerMsj("msj.infoNotFound"), ""));
                this.lista = new ArrayList();
            }
        }
        return "";
    }

    public String clean() {
        this.restartBean();
        return "";
    }

    private void restartBean() {
        this.area = new Area();
        this.modificando = false;
        this.lista = new ArrayList<>();
    }

    public String changeLanguage() {
        Locale loc = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        if (loc.getLanguage().equals("es")) {
            FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("en"));
        } else {
            FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("es"));
        }
        return "";
    }

    public boolean validateData() {
        boolean flag = true;
        if (area.getIdArea() == null) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:txtIdArea",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.id")));
        }
        if (area.getNombre().trim().length() == 0) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:txtNombre",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.name")));
        }
        if (area.getUbicacion().trim().length() == 0) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:txtUbicacion",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.location")));
        }

        return flag;
    }
    
   
    
     public String goTo() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("mantArea.xhtml?init=true");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "mantArea";
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public AreaBo getBo() {
        return bo;
    }

    public void setBo(AreaBo bo) {
        this.bo = bo;
    }

    public List<Area> getLista() {
        if (lista.isEmpty()) {
            lista = bo.getAll();
        }
        return lista;
    }

    public void setLista(List<Area> lista) {
        this.lista = lista;
    }

    public boolean isModificando() {
        return modificando;
    }

    public void setModificando(boolean modificando) {
        this.modificando = modificando;
    }

}
