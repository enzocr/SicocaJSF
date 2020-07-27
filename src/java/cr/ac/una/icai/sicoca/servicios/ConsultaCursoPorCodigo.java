/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sicoca.servicios;

import cr.ac.una.icai.sicoca.bo.CursoBo;
import cr.ac.una.icai.sicoca.bo.InstructorBo;
import cr.ac.una.icai.sicoca.clases.Curso;
import cr.ac.una.icai.sicoca.clases.Instructor;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author enzoq
 */
@WebService(serviceName = "ConsultaCursoPorCodigo")
public class ConsultaCursoPorCodigo {

    /**
     * This is a sample web service operation
     *
     * @param codigo
     * @return curso y su instructor
     */
    @WebMethod(operationName = "consultaCursoPorCodigo")
    public String hello(@WebParam(name = "code") Integer codigo) {
        CursoBo bo = new CursoBo();
        Curso curso = bo.getById(codigo);
        InstructorBo ibo = new InstructorBo();
        Instructor instructor = ibo.getById(curso.getIdInstructor());
        return curso.toString() + "\n"
                + instructor.toString()+ "\n";
    }
}
