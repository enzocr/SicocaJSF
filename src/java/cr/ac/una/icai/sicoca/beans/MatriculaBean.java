/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sicoca.beans;

import cr.ac.una.icai.sicoca.bo.CursoBo;
import cr.ac.una.icai.sicoca.bo.EmpleadoBo;
import cr.ac.una.icai.sicoca.bo.MatriculaBo;
import cr.ac.una.icai.sicoca.clases.Curso;
import cr.ac.una.icai.sicoca.clases.Empleado;
import cr.ac.una.icai.sicoca.clases.Matricula;
import cr.ac.una.icai.sicoca.utilidades.Utilidades;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author enzoq
 */
public class MatriculaBean {

    private Matricula matricula;
    private MatriculaBo bo;
    private List<Matricula> lista;
    private List<Empleado> listaEmpleadosPorCurso;
    private EmpleadoBo ebo;
    private CursoBo cbo;
    private boolean modificando;

    public MatriculaBean() {
        this.matricula = new Matricula();
        matricula.getNota();
        this.bo = new MatriculaBo();
        this.ebo = new EmpleadoBo();
        this.cbo = new CursoBo();
        this.modificando = false;
        this.lista = new ArrayList<>();
        this.listaEmpleadosPorCurso = new ArrayList<>();
    }

    public String insert() {
        if (validateData()) {
            int res = this.bo.insert(matricula);

            switch (res) {
                case 0:
                    restartBean();
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, Utilidades.obtenerMsj("msj.insertedSuccessfully"), ""));
                    break;
                case 1:

                    restartBean();
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, Utilidades.obtenerMsj("msj.cantConnBD"), ""));
                    break;

                case 2:

                    restartBean();
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, Utilidades.obtenerMsj("msj.error"), ""));
                    break;
                case 3:

                    restartBean();
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
            int res = this.bo.update(matricula);

            switch (res) {
                case 0:

                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, Utilidades.obtenerMsj("msj.updatedSuccessfully"), ""));
                    break;
                case 1:

                    restartBean();
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, Utilidades.obtenerMsj("msj.cantConnBD"), ""));
                    break;
                case 2:

                    restartBean();
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, Utilidades.obtenerMsj("msj.error"), ""));
                    break;
                case 3:

                    restartBean();
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, Utilidades.obtenerMsj("msj.noDataUpdated"), ""));
                    break;
            }
            restartBean();
        }
        return "";
    }

    public String verifyEnrroll() {
        if (validateData()) {

            Curso curso = cbo.getById(matricula.getIdCurso());
            Empleado emp = ebo.getById(matricula.getCedula());

            int result = 0;
            int i = 0;
            for (Matricula m : getLista()) {
                if (m.getIdCurso().equals(curso.getIdCurso())
                        && m.getCedula().equals(emp.getCedula())) {
                    matricula.setNota(m.getNota());
                    result = 1;
                }
                i++;
            }
            switch (result) {
                case 0:
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO,
                                    Utilidades.obtenerMsj("msj.isNotEnrrolled"), ""));
                    break;
                case 1:
                    modificando = true;

                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO,
                                    Utilidades.obtenerMsj("msj.isEnrrolled"), ""));
                    break;
            }

        }

        return "";
    }

    public List<Matricula> getByCourse() {
        List<Matricula> matriculados = new ArrayList<>();
        if (matricula.getIdCurso() == null) {
            FacesContext.getCurrentInstance().addMessage("form:cbCursos",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.courses")));
        } else {
            matriculados = bo.getByCourse(this.matricula);
            if (matriculados.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL, "", Utilidades.obtenerMsj("msj.infoNotFound")));
                matriculados = new ArrayList();
            }
        }
        return matriculados;
    }

    public String searchEmployeesByCourse() {
        this.listaEmpleadosPorCurso.clear();
        List<Matricula> matriculas = new ArrayList<>();
        int i = 0;
        for (Matricula m : this.getLista()) {
            if (lista.get(i).getIdCurso().equals(matricula.getIdCurso())) {
                matriculas.add(this.lista.get(i));
            }
            i++;
        }
        for (Matricula m : matriculas) {
            this.listaEmpleadosPorCurso.add(ebo.getById(m.getCedula()));
        }
        return "";
    }

    public String clean() {
        this.restartBean();
        return "";
    }

    private void restartBean() {
        this.matricula = new Matricula();
        this.lista = new ArrayList<>();
        this.listaEmpleadosPorCurso = new ArrayList<>();
        this.modificando = false;
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
        if (matricula.getCedula() == null || matricula.getCedula() == 0) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:cbEmpleados",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.employees")));
        }
        if (matricula.getIdCurso() == null || matricula.getIdCurso() == 0) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:cbCursos",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.courses")));
        }

        return flag;
    }

    public String goToInscriptions() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("registroMatricula.xhtml?init=true");
            clean();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "registroMatricula";
    }

    public String goToVerification() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("verificarMatricula.xhtml?init=true");
            clean();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "verificarMatricula";
    }

    public String goToEmployeesByCourse() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("empleadosPorCurso.xhtml?init=true");
            clean();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "empleadorPorCursp";
    }

    public List<Matricula> getLista() {
        if (lista.isEmpty()) {
            lista = bo.getAll();
        }
        return lista;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public MatriculaBo getBo() {
        return bo;
    }

    public void setBo(MatriculaBo bo) {
        this.bo = bo;
    }

    public List<Empleado> getListaEmpleadosPorCurso() {
        if (this.listaEmpleadosPorCurso.isEmpty()) {
            return null;
        }
        return listaEmpleadosPorCurso;
    }

    public void setListaEmpleadosPorCurso(List<Empleado> listaEmpleadosPorCurso) {
        this.listaEmpleadosPorCurso = listaEmpleadosPorCurso;
    }

    public boolean isModificando() {
        return modificando;
    }

    public void setModificando(boolean modificando) {
        this.modificando = modificando;
    }

}
