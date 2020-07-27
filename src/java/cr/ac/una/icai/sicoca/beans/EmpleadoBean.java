/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sicoca.beans;

import cr.ac.una.icai.sicoca.bo.AreaBo;
import cr.ac.una.icai.sicoca.bo.EmpleadoBo;
import cr.ac.una.icai.sicoca.clases.Area;
import cr.ac.una.icai.sicoca.clases.Empleado;
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
public class EmpleadoBean {

    private Empleado empleado;
    private EmpleadoBo bo;
    private AreaBo abo;
    private List<Empleado> lista;
    private boolean modificando;

    public EmpleadoBean() {
        this.abo = new AreaBo();
        this.empleado = new Empleado();
        this.bo = new EmpleadoBo();
        this.modificando = false;
        this.lista = new ArrayList<>();
    }

    public String insert() {
        if (validateData()) {
            int res = this.bo.insert(empleado);

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
            int res = this.bo.update(empleado);

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

    public String delete(Empleado obj) {
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

    public String select(Empleado obj) {
        this.empleado = obj;
        this.modificando = true;
        return "";
    }

    public List<SelectItem> fillEmployees() {
        List<SelectItem> itemList = new ArrayList<>();
        List<Empleado> objs = this.bo.getAll();
        itemList.add(new SelectItem(0, Utilidades.obtenerMsj("select.option")));
        for (int i = 0; i < objs.size(); i++) {
            Empleado object = objs.get(i);
            itemList.add(new SelectItem(object.getCedula(), object.getNombre()+" "+object.getApellido1()));
        }
        return itemList;
    }

    public String getById() {
        if (empleado.getCedula() == null) {
            FacesContext.getCurrentInstance().addMessage("form:txtCedula",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.cedula")));
        } else {
            Empleado obj = bo.getById(this.empleado.getCedula());
            if (obj.getCedula() == null) {
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
        if (this.empleado.getNombre().trim().length() == 0) {
            FacesContext.getCurrentInstance().addMessage("form:txtNombre",
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "", Utilidades.obtenerMsj("validate.name")));
        } else {
            this.lista = this.bo.getByName(this.empleado.getNombre());
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
        this.empleado = new Empleado();
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
        if (empleado.getCedula() == null) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:txtCedula",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.cedula")));
        }
        if (empleado.getNombre().trim().length() == 0) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:txtNombre",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.name")));
        }
        if (empleado.getApellido1().trim().length() == 0) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:txtApellido1",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.lastName1")));
        }
        if (empleado.getApellido2().trim().length() == 0) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:txtApellido2",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.lastName2")));
        }

        if (empleado.getTelefono().trim().length() == 0) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:txtTelefono",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.phone")));
        }
        if (empleado.getCorreo().trim().length() == 0) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:txtCorreo",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.email")));
        }
        if (empleado.getIdArea() == 0) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:cbArea",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.areas")));
        }

        return flag;
    }

    public String fillCollumnWithArea(int id) {
        Area area = abo.getById(id);
        return area.getIdArea() + "-" + area.getNombre();
    }

    public String goTo() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("mantEmpleado.xhtml?init=true");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "mantEmpleado";
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public EmpleadoBo getBo() {
        return bo;
    }

    public void setBo(EmpleadoBo bo) {
        this.bo = bo;
    }

    public List<Empleado> getLista() {
        if (lista.isEmpty()) {
            lista = bo.getAll();
        }
        return lista;
    }

    public void setLista(List<Empleado> lista) {
        this.lista = lista;
    }

    public boolean isModificando() {
        return modificando;
    }

    public void setModificando(boolean modificando) {
        this.modificando = modificando;
    }

}
