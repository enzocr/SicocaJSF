/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sicoca.beans;

import cr.ac.una.icai.sicoca.bo.CursoBo;
import cr.ac.una.icai.sicoca.bo.InstructorBo;
import cr.ac.una.icai.sicoca.clases.Curso;
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
public class CursoBean {

    private Curso curso;
    private CursoBo bo;
    private InstructorBo ibo;
    private List<Curso> lista;
    private boolean modificando;

    public CursoBean() {
        this.curso = new Curso();
        this.ibo = new InstructorBo();
        this.bo = new CursoBo();
        this.modificando = false;
        this.lista = new ArrayList<>();
    }

    public String insert() {
        if (validateData()) {
            int res = this.bo.insert(curso);

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
            int res = this.bo.update(curso);

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

    public String delete(Curso obj) {
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

    public String select(Curso obj) {
        this.curso = obj;
        this.modificando = true;
        return "";
    }

    public List<SelectItem> fillCourses() {
        List<SelectItem> itemList = new ArrayList<>();
        List<Curso> objs = this.bo.getAll();
        itemList.add(new SelectItem(0, Utilidades.obtenerMsj("select.option")));
        for (int i = 0; i < objs.size(); i++) {
            Curso object = objs.get(i);
            itemList.add(new SelectItem(object.getIdCurso(), object.getNombre()));
        }
        return itemList;
    }

    public String getById() {
        if (curso.getIdCurso() == null) {
            FacesContext.getCurrentInstance().addMessage("form:txtIdCurso",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.id")));
        } else {
            Curso obj = bo.getById(this.curso.getIdCurso());
            if (obj.getIdCurso() == null) {
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
        if (this.curso.getNombre().trim().length() == 0) {
            FacesContext.getCurrentInstance().addMessage("form:txtNombre",
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "", Utilidades.obtenerMsj("validate.name")));
        } else {
            this.lista = this.bo.getByName(this.curso.getNombre());
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
        this.curso = new Curso();
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
        if (curso.getIdCurso() == null) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:txtIdCurso",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.id")));
        }
        if (curso.getNombre().trim().length() == 0) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:txtNombre",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.name")));
        }
        if (curso.getHorasDuracion() == null) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:txtHoras",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.hours")));
        }
        if (curso.getFechaInicio() == null) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:txtFechaInicio",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.initialDate")));
        }
        if (curso.getFechaFinalizacion() == null) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:txtFechaFinal",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.finalDate")));
        }
        if (curso.getPrecio() == null) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:txtPrecio",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.price")));
        }
        if (curso.getIdInstructor() == 0) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:cbInstructors",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.instructors")));
        }

        return flag;
    }

    public String fillCollumnWithIntructorsCourse(int id) {
        Instructor instructor = new Instructor();
        instructor = ibo.getById(id);
        return instructor.getIdInstructor() + "-" + instructor.getNombre();
    }

    public String goTo() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("mantCurso.xhtml?init=true");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "mantCurso";
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public CursoBo getBo() {
        return bo;
    }

    public void setBo(CursoBo bo) {
        this.bo = bo;
    }

    public List<Curso> getLista() {
        if (lista.isEmpty()) {
            lista = this.bo.getAll();
        }
        return lista;
    }

    public void setLista(List<Curso> lista) {
        this.lista = lista;
    }

    public boolean isModificando() {
        return modificando;
    }

    public void setModificando(boolean modificando) {
        this.modificando = modificando;
    }

}
