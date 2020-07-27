/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sicoca.beans;

import cr.ac.una.icai.sicoca.bo.InstructorBo;
import cr.ac.una.icai.sicoca.clases.Instructor;
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
public class InstructorBean {

    private Instructor instructor;
    private InstructorBo bo;
    private List<Instructor> lista;
    private boolean modificando;

    public InstructorBean() {
        this.instructor = new Instructor();
        this.bo = new InstructorBo();
        this.modificando = false;
        this.lista = new ArrayList<>();
    }

    public String insert() {
        if (validateData()) {
            int res = this.bo.insert(instructor);

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
            int res = this.bo.update(instructor);

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

    public String delete(Instructor obj) {
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

    public String select(Instructor obj) {
        this.instructor = obj;
        this.modificando = true;
        return "";
    }

    public List<SelectItem> fillInstructors() {
        List<SelectItem> itemList = new ArrayList<>();
        List<Instructor> objs = this.bo.getAll();
        itemList.add(new SelectItem(0, Utilidades.obtenerMsj("select.option")));
        for (int i = 0; i < objs.size(); i++) {
            Instructor object = objs.get(i);
            itemList.add(new SelectItem(object.getIdInstructor(), object.getIdInstructor() + " " + object.getNombre()));
        }
        return itemList;
    }

    public String getById() {
        if (instructor.getIdInstructor() == null) {
            FacesContext.getCurrentInstance().addMessage("form:txtIdInstructor",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.id")));
        } else {
            Instructor obj = bo.getById(this.instructor.getIdInstructor());
            if (obj.getIdInstructor() == null) {
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
        if (this.instructor.getNombre().trim().length() == 0) {
            FacesContext.getCurrentInstance().addMessage("form:txtNombre",
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "", Utilidades.obtenerMsj("validate.name")));
        } else {
            this.lista = this.bo.getByName(this.instructor.getNombre());
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
        this.instructor = new Instructor();
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
        if (instructor.getIdInstructor() == null) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:txtIdInstructor",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.id")));
        }
        if (instructor.getNombre().trim().length() == 0) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:txtNombre",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.name")));
        }
        if (instructor.getGradoAcademico().trim().length() == 0) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:txtGrado",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.grade")));
        }
        if (instructor.getAnnosExperiencia() == 0) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:txtAnnosExperiencia",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.years")));
        }
        if (instructor.getTelefono().trim().length() == 0) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:txtTelefono",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.phone")));
        }
        if (instructor.getCorreo().trim().length() == 0) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:txtCorreo",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.email")));
        }

        return flag;
    }

    public String goTo() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("mantInstructor.xhtml?init=true");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "mantInstructor";
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public InstructorBo getBo() {
        return bo;
    }

    public void setBo(InstructorBo bo) {
        this.bo = bo;
    }

    public List<Instructor> getLista() {
        if (lista.isEmpty()) {
            lista = this.bo.getAll();
        }
        return lista;
    }

    public void setLista(List<Instructor> lista) {
        this.lista = lista;
    }

    public boolean isModificando() {
        return modificando;
    }

    public void setModificando(boolean modificando) {
        this.modificando = modificando;
    }

}
