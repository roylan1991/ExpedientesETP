package com.roylan.expedientes_etp.business;

import com.roylan.expedientes_etp.database.entities.ComposicionPersonal;
import com.roylan.expedientes_etp.database.entities.ProfesorAsignatura;
import com.roylan.expedientes_etp.database.entities.ProfesorContrato;
import com.roylan.expedientes_etp.database.entities.ProfesorFijo;
import com.roylan.expedientes_etp.database.services.GestionarComposicionPersonalImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Esta clase valida que se gestionen correctamente todas las operaciones referentes al personal.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Component
public class ValidacionComposicionPersonalImpl implements IValidacion<ComposicionPersonal> {

    @Autowired
    private GestionarComposicionPersonalImpl p_serv;

    public ValidacionComposicionPersonalImpl() {
    }

    /**
     * Esta funcionalidad registra un nuevo personal.
     *
     * @param p Datos del personal.
     */
    public void validarAdicionar(ComposicionPersonal p) {
        p_serv.adicionar(p);
    }

    /**
     * Esta funcionalidad devuelve un personal luego de comprobar que se encuentra registrado.
     *
     * @param idP Identificador del personal.
     * @return <code>ComposicionPersonal</code> Personal obtenido.
     * @throws Exception Si el personal no se encuentra registrado.
     */
    public ComposicionPersonal validarObtenerId(long idP) throws Exception {

        ComposicionPersonal p = p_serv.obtenerId(idP);

        if (p == null) {
            throw new Exception("Este Personal no se encuentra registrado!");
        }

        return p;
    }

    /**
     * Esta funcionalidad edita un personal luego de ser comprobado que los nuevos datos son correctos.
     *
     * @param idP     Identificador del personal que se editará.
     * @param n_datos Nuevos datos del personal.
     * @throws Exception Si los datos del personal están sintácticamente correctos.
     * @throws Exception Si los nuevos datos no son correctos.
     */
    public void validarActualizar(long idP, ComposicionPersonal n_datos) throws Exception {

        if (n_datos.getIdPersonal() == -1) {
            throw new Exception("Estos campos de datos solo admiten números válidos!");
        }

        if (n_datos.getDocentes().getTotal() < n_datos.getDocentes().getHembras()) {
            throw new Exception("Verifique en [Docentes] el Total de Profesores Hembras!");
        }

        if (n_datos.getBibliotecarios().getTotal() < n_datos.getBibliotecarios().getHembras()) {
            throw new Exception("Verifique en [Docentes] los totales de Bibliotecarios!");
        }

        if (n_datos.getInstructoresArte().getTotal() < n_datos.getInstructoresArte().getHembras()) {
            throw new Exception("Verifique en [Docentes] los totales de Instructores de Arte!");
        }

        if (n_datos.getPsicopedagogos().getTotal() < n_datos.getPsicopedagogos().getHembras()) {
            throw new Exception("Verifique en [Docentes] los totales de Psicopedagogos!");
        }

        if (n_datos.getNoDocentes().getTotal() < n_datos.getNoDocentes().getHembras()) {
            throw new Exception("Verifique en [No Docentes] los totales de los Personales No Docentes!");
        }

        if (n_datos.getTotalInactivos() != (n_datos.getTotalProfesoresInactivos() + n_datos.getOtrosDocentesInactivos() + n_datos.getNoDocentesInactivos())) {
            throw new Exception("Verifique en [Inactivos] el Total,  no coincide con la cantidad de: Profesores, Otros Docentes y No Docentes!");
        }

        p_serv.update(idP, n_datos);
    }

    /**
     * Esta funcionalidad elimina un personal luego de comprobar que se encuentra registrado.
     *
     * @param idP Identificador del personal.
     * @throws Exception Si el personal no se encuentra registrado.
     */
    public void validarEliminar(long idP) throws Exception {

        if (p_serv.obtenerId(idP) == null) {
            throw new Exception("Este personal no se encuentra registrado!");
        }

        p_serv.eliminar(idP);
    }

    /**
     * Esta funcionalidad edita los profesores por asignaturas de un personal luego de ser comprobado que los nuevos datos son correctos.
     *
     * @param idP     Identificador del personal que se editará.
     * @param n_datos Nuevos datos de los profesores por asignaturas.
     * @throws Exception Si los datos de los profesores por asignaturas están sintácticamente correctos.
     * @throws Exception Si los datos de los profesores por asignaturas no son correctos.
     */
    public void validarActualizarProfesoresAsig(long idP, ComposicionPersonal n_datos) throws Exception {

        if (n_datos.getIdPersonal() == -1) {
            throw new Exception("Estos campos de datos solo admiten números válidos!");
        }

        ProfesorAsignatura pfa = n_datos.getProfesoresFijos();
        ProfesorFijo pf = ((ProfesorFijo) pfa.getProfesorEspanol());

        if (pf.getTotalProfesores() < pf.getNoTitulados()  || pf.getNoTitulados() < pf.getNoTituladosEstudiando()) {
            throw new Exception("Verifique en los [Profesores Fijos] los totales de profesores de Español!");
        }
        pf = ((ProfesorFijo) pfa.getProfesorLiteratura());
        if (pf.getTotalProfesores() < pf.getNoTitulados() || pf.getNoTitulados() < pf.getNoTituladosEstudiando()) {
            throw new Exception("Verifique en los [Profesores Fijos] los totales de profesores de Literatura!");
        }
        pf = ((ProfesorFijo) pfa.getProfesorMatematica());
        if (pf.getTotalProfesores() < pf.getNoTitulados() || pf.getNoTitulados() < pf.getNoTituladosEstudiando()) {
            throw new Exception("Verifique en los [Profesores Fijos] los totales de profesores de Matemática!");
        }
        pf = ((ProfesorFijo) pfa.getProfesorFisica());
        if (pf.getTotalProfesores() < pf.getNoTitulados() || pf.getNoTitulados() < pf.getNoTituladosEstudiando()) {
            throw new Exception("Verifique en los [Profesores Fijos] los totales de profesores de Física!");
        }
        pf = ((ProfesorFijo) pfa.getProfesorQuimica());
        if (pf.getTotalProfesores() < pf.getNoTitulados() || pf.getNoTitulados() < pf.getNoTituladosEstudiando()) {
            throw new Exception("Verifique en los [Profesores Fijos] los totales de profesores de Química!");
        }
        pf = ((ProfesorFijo) pfa.getProfesorBiologia());
        if (pf.getTotalProfesores() < pf.getNoTitulados() || pf.getNoTitulados() < pf.getNoTituladosEstudiando()) {
            throw new Exception("Verifique en los [Profesores Fijos] los totales de profesores de Biología!");
        }
        pf = ((ProfesorFijo) pfa.getProfesorHistoria());
        if (pf.getTotalProfesores() < pf.getNoTitulados() || pf.getNoTitulados() < pf.getNoTituladosEstudiando()) {
            throw new Exception("Verifique en los [Profesores Fijos] los totales de profesores de Historia!");
        }
        pf = ((ProfesorFijo) pfa.getProfesorIngles());
        if (pf.getTotalProfesores() < pf.getNoTitulados() || pf.getNoTitulados() < pf.getNoTituladosEstudiando()) {
            throw new Exception("Verifique en los [Profesores Fijos] los totales de profesores de Idioma Extranjero!");
        }
        pf = ((ProfesorFijo) pfa.getProfesorEducacionFisica());
        if (pf.getTotalProfesores() < pf.getNoTitulados() || pf.getNoTitulados() < pf.getNoTituladosEstudiando()) {
            throw new Exception("Verifique en los [Profesores Fijos] los totales de profesores de Educación Física!");
        }
        pf = ((ProfesorFijo) pfa.getProfesorEducacionArtistica());
        if (pf.getTotalProfesores() < pf.getNoTitulados() || pf.getNoTitulados() < pf.getNoTituladosEstudiando()) {
            throw new Exception("Verifique en los [Profesores Fijos] los totales de profesores de Educación Artística!");
        }
        pf = ((ProfesorFijo) pfa.getProfesorMarxismo());
        if (pf.getTotalProfesores() < pf.getNoTitulados() || pf.getNoTitulados() < pf.getNoTituladosEstudiando()) {
            throw new Exception("Verifique en los [Profesores Fijos] los totales de profesores de Marxismo Leninismo!");
        }
        pf = ((ProfesorFijo) pfa.getProfesorFundamento());
        if (pf.getTotalProfesores() < pf.getNoTitulados() || pf.getNoTitulados() < pf.getNoTituladosEstudiando()) {
            throw new Exception("Verifique en los [Profesores Fijos] los totales de profesores de Fundamento de los Conocimientos Políticos!");
        }
        pf = ((ProfesorFijo) pfa.getProfesorComputacion());
        if (pf.getTotalProfesores() < pf.getNoTitulados() || pf.getNoTitulados() < pf.getNoTituladosEstudiando()) {
            throw new Exception("Verifique en los [Profesores Fijos] los totales de profesores de Computacion!");
        }
        pf = ((ProfesorFijo) pfa.getProfesorMilitar());
        if (pf.getTotalProfesores() < pf.getNoTitulados() || pf.getNoTitulados() < pf.getNoTituladosEstudiando()) {
            throw new Exception("Verifique en los [Profesores Fijos] los totales de profesores de Preparación Militar!");
        }
        pf = ((ProfesorFijo) pfa.getProfesorPractica());
        if (pf.getTotalProfesores() < pf.getNoTitulados() || pf.getNoTitulados() < pf.getNoTituladosEstudiando()) {
            throw new Exception("Verifique en los [Profesores Fijos] los totales de profesores de Enseñanza Práctica!");
        }
        pf = ((ProfesorFijo) pfa.getProfesorBasica());
        if (pf.getTotalProfesores() < pf.getNoTitulados() || pf.getNoTitulados() < pf.getNoTituladosEstudiando()) {
            throw new Exception("Verifique en los [Profesores Fijos] los totales de profesores de Básicas Específicas!");
        }
        pf = ((ProfesorFijo) pfa.getProfesorEjercicio());
        if (pf.getTotalProfesores() < pf.getNoTitulados() || pf.getNoTitulados() < pf.getNoTituladosEstudiando()) {
            throw new Exception("Verifique en los [Profesores Fijos] los totales de profesores de Ejercicio de la Profesión!");
        }
        pf = ((ProfesorFijo) pfa.getProfesorInstructor());
        if (pf.getTotalProfesores() < pf.getNoTitulados() || pf.getNoTitulados() < pf.getNoTituladosEstudiando()) {
            throw new Exception("Verifique en los [Profesores Fijos] los totales de profesores de Instructor!");
        }

        ProfesorAsignatura pca = n_datos.getProfesoresContratados();
        ProfesorContrato pc = ((ProfesorContrato) pca.getProfesorEspanol());

        if (pc.getTotalProfesores() < pc.getNoProfesionales() || pc.getNoProfesionales() < pc.getNoProfesionalesEstudiando()) {
            throw new Exception("Verifique en los [Profesores Contratados] los totales de profesores de Español!");
        }
        pc = ((ProfesorContrato) pca.getProfesorLiteratura());
        if (pc.getTotalProfesores() < pc.getNoProfesionales() || pc.getNoProfesionales() < pc.getNoProfesionalesEstudiando()) {
            throw new Exception("Verifique en los [Profesores Contratados] los totales de profesores de Literatura!");
        }
        pc = ((ProfesorContrato) pca.getProfesorMatematica());
        if (pc.getTotalProfesores() < pc.getNoProfesionales() || pc.getNoProfesionales() < pc.getNoProfesionalesEstudiando()) {
            throw new Exception("Verifique en los [Profesores Contratados] los totales de profesores de Matemática!");
        }
        pc = ((ProfesorContrato) pca.getProfesorFisica());
        if (pc.getTotalProfesores() < pc.getNoProfesionales() || pc.getNoProfesionales() < pc.getNoProfesionalesEstudiando()) {
            throw new Exception("Verifique en los [Profesores Contratados] los totales de profesores de Física!");
        }
        pc = ((ProfesorContrato) pca.getProfesorQuimica());
        if (pc.getTotalProfesores() < pc.getNoProfesionales() || pc.getNoProfesionales() < pc.getNoProfesionalesEstudiando()) {
            throw new Exception("Verifique en los [Profesores Contratados] los totales de profesores de Química!");
        }
        pc = ((ProfesorContrato) pca.getProfesorBiologia());
        if (pc.getTotalProfesores() < pc.getNoProfesionales() || pc.getNoProfesionales() < pc.getNoProfesionalesEstudiando()) {
            throw new Exception("Verifique en los [Profesores Contratados] los totales de profesores de Biología!");
        }
        pc = ((ProfesorContrato) pca.getProfesorHistoria());
        if (pc.getTotalProfesores() < pc.getNoProfesionales() || pc.getNoProfesionales() < pc.getNoProfesionalesEstudiando()) {
            throw new Exception("Verifique en los [Profesores Contratados] los totales de profesores de Historia!");
        }
        pc = ((ProfesorContrato) pca.getProfesorHistoria());
        if (pc.getTotalProfesores() < pc.getNoProfesionales() || pc.getNoProfesionales() < pc.getNoProfesionalesEstudiando()) {
            throw new Exception("Verifique en los [Profesores Contratados] los totales de profesores de Idioma Extranjero!");
        }
        pc = ((ProfesorContrato) pca.getProfesorEducacionFisica());
        if (pc.getTotalProfesores() < pc.getNoProfesionales() || pc.getNoProfesionales() < pc.getNoProfesionalesEstudiando()) {
            throw new Exception("Verifique en los [Profesores Contratados] los totales de profesores de Educación Física!");
        }
        pc = ((ProfesorContrato) pca.getProfesorEducacionArtistica());
        if (pc.getTotalProfesores() < pc.getNoProfesionales() || pc.getNoProfesionales() < pc.getNoProfesionalesEstudiando()) {
            throw new Exception("Verifique en los [Profesores Contratados] los totales de profesores de Educación Artística!");
        }
        pc = ((ProfesorContrato) pca.getProfesorMarxismo());
        if (pc.getTotalProfesores() < pc.getNoProfesionales() || pc.getNoProfesionales() < pc.getNoProfesionalesEstudiando()) {
            throw new Exception("Verifique en los [Profesores Contratados] los totales de profesores de Marxismo Leninismo!");
        }
        pc = ((ProfesorContrato) pca.getProfesorFundamento());
        if (pc.getTotalProfesores() < pc.getNoProfesionales() || pc.getNoProfesionales() < pc.getNoProfesionalesEstudiando()) {
            throw new Exception("Verifique en los [Profesores Contratados] los totales de profesores de Fundamento de los Conocimientos Políticos!");
        }
        pc = ((ProfesorContrato) pca.getProfesorComputacion());
        if (pc.getTotalProfesores() < pc.getNoProfesionales() || pc.getNoProfesionales() < pc.getNoProfesionalesEstudiando()) {
            throw new Exception("Verifique en los [Profesores Contratados] los totales de profesores de Computacion!");
        }
        pc = ((ProfesorContrato) pca.getProfesorMilitar());
        if (pc.getTotalProfesores() < pc.getNoProfesionales() || pc.getNoProfesionales() < pc.getNoProfesionalesEstudiando()) {
            throw new Exception("Verifique en los [Profesores Contratados] los totales de profesores de Preparación Militar!");
        }
        pc = ((ProfesorContrato) pca.getProfesorPractica());
        if (pc.getTotalProfesores() < pc.getNoProfesionales() || pc.getNoProfesionales() < pc.getNoProfesionalesEstudiando()) {
            throw new Exception("Verifique en los [Profesores Contratados] los totales de profesores de Enseñanza Práctica!");
        }
        pc = ((ProfesorContrato) pca.getProfesorBasica());
        if (pc.getTotalProfesores() < pc.getNoProfesionales() || pc.getNoProfesionales() < pc.getNoProfesionalesEstudiando()) {
            throw new Exception("Verifique en los [Profesores Contratados] los totales de profesores de Básicas Específicas!");
        }
        pc = ((ProfesorContrato) pca.getProfesorEjercicio());
        if (pc.getTotalProfesores() < pc.getNoProfesionales() || pc.getNoProfesionales() < pc.getNoProfesionalesEstudiando()) {
            throw new Exception("Verifique en los [Profesores Contratados] los totales de profesores de Ejercicio de la Profesión!");
        }
        pc = ((ProfesorContrato) pca.getProfesorInstructor());
        if (pc.getTotalProfesores() < pc.getNoProfesionales() || pc.getNoProfesionales() < pc.getNoProfesionalesEstudiando()) {
            throw new Exception("Verifique en los [Profesores Contratados] los totales de profesores de Instructor!");
        }

        p_serv.updateProfesores(idP, n_datos);
    }

    /**
     * Esta funcionalidad lista todos los personales que se encuentren registrados.
     *
     * @return <code>List</code> Todos los personales registrados.
     */
    public List<ComposicionPersonal> validarListar() {
        return p_serv.listar();
    }
}