package com.roylan.expedientes_etp.database.services;

import com.roylan.expedientes_etp.database.entities.*;
import com.roylan.expedientes_etp.database.repositories.ComposicionPersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Esta clase gestiona todas las operaciones referentes al personal de un centro.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Service
@Transactional
public class GestionarComposicionPersonalImpl implements IGestionarService<ComposicionPersonal> {

    @Autowired
    private ComposicionPersonalRepository p_repo;

    @Autowired
    private IGestionarService<Personal> personales;

    @Autowired
    private GestionarProfesorFijo pf;

    @Autowired
    private GestionarProfesorContrato pc;

    public GestionarComposicionPersonalImpl() {
    }

    /**
     * Esta funcionalidad permite registar un nuevo personal.
     *
     * @param p Nuevo personal.
     */
    public void adicionar(ComposicionPersonal p) {
        p_repo.save(p);
    }

    /**
     * Esta funcionalidad permite obtener el personal de un centro mediante su identificador.
     *
     * @param idP Identificador del personal.
     * @return <code>ComposicionPersonal</code> Personal obtenido.
     *
     * <code>null</code> Si no existe el personal.
     */
    public ComposicionPersonal obtenerId(long idP) {
        return p_repo.findByIdPersonal(idP);
    }

    /**
     * Esta funcionalidad permite editar el personal de un centro.
     *
     * @param idP     Identificador del personal a editar.
     * @param n_datos Nuevos datos del personal.
     */
    public void update(long idP, ComposicionPersonal n_datos) {
        p_repo.updatePersonal(idP, n_datos.getAlumnosMaestros(), n_datos.getTotalProfesoresInactivos(), n_datos.getOtrosDocentesInactivos(), n_datos.getNoDocentesInactivos());

        ComposicionPersonal cp = obtenerId(idP);

        personales.update(cp.getDocentes().getIdPersonal(), n_datos.getDocentes());
        personales.update(cp.getBibliotecarios().getIdPersonal(), n_datos.getBibliotecarios());
        personales.update(cp.getInstructoresArte().getIdPersonal(), n_datos.getInstructoresArte());
        personales.update(cp.getPsicopedagogos().getIdPersonal(), n_datos.getPsicopedagogos());
        personales.update(cp.getNoDocentes().getIdPersonal(), n_datos.getNoDocentes());
    }

    /**
     * Esta funcionalidad permite editar los profesores por asignaturas del personal de un centro.
     *
     * @param idP     Identificador del personal a editar.
     * @param n_datos Nuevos datos de los profesores por asignaturas.
     */
    public void updateProfesores(long idP, ComposicionPersonal n_datos) {
        ComposicionPersonal cp = obtenerId(idP);

        ProfesorAsignatura pF = n_datos.getProfesoresFijos();
        ProfesorAsignatura pC = n_datos.getProfesoresContratados();

        pf.update(cp.getProfesoresFijos().getProfesorEspanol().getIdTipoProfesor(), (ProfesorFijo) pF.getProfesorEspanol());
        pf.update(cp.getProfesoresFijos().getProfesorLiteratura().getIdTipoProfesor(), (ProfesorFijo) pF.getProfesorLiteratura());
        pf.update(cp.getProfesoresFijos().getProfesorMatematica().getIdTipoProfesor(), (ProfesorFijo) pF.getProfesorMatematica());
        pf.update(cp.getProfesoresFijos().getProfesorFisica().getIdTipoProfesor(), (ProfesorFijo) pF.getProfesorFisica());
        pf.update(cp.getProfesoresFijos().getProfesorQuimica().getIdTipoProfesor(), (ProfesorFijo) pF.getProfesorQuimica());
        pf.update(cp.getProfesoresFijos().getProfesorBiologia().getIdTipoProfesor(), (ProfesorFijo) pF.getProfesorBiologia());
        pf.update(cp.getProfesoresFijos().getProfesorHistoria().getIdTipoProfesor(), (ProfesorFijo) pF.getProfesorHistoria());
        pf.update(cp.getProfesoresFijos().getProfesorIngles().getIdTipoProfesor(), (ProfesorFijo) pF.getProfesorIngles());
        pf.update(cp.getProfesoresFijos().getProfesorEducacionFisica().getIdTipoProfesor(), (ProfesorFijo) pF.getProfesorEducacionFisica());
        pf.update(cp.getProfesoresFijos().getProfesorEducacionArtistica().getIdTipoProfesor(), (ProfesorFijo) pF.getProfesorEducacionArtistica());
        pf.update(cp.getProfesoresFijos().getProfesorMarxismo().getIdTipoProfesor(), (ProfesorFijo) pF.getProfesorMarxismo());
        pf.update(cp.getProfesoresFijos().getProfesorFundamento().getIdTipoProfesor(), (ProfesorFijo) pF.getProfesorFundamento());
        pf.update(cp.getProfesoresFijos().getProfesorComputacion().getIdTipoProfesor(), (ProfesorFijo) pF.getProfesorComputacion());
        pf.update(cp.getProfesoresFijos().getProfesorMilitar().getIdTipoProfesor(), (ProfesorFijo) pF.getProfesorMilitar());
        pf.update(cp.getProfesoresFijos().getProfesorPractica().getIdTipoProfesor(), (ProfesorFijo) pF.getProfesorPractica());
        pf.update(cp.getProfesoresFijos().getProfesorBasica().getIdTipoProfesor(), (ProfesorFijo) pF.getProfesorBasica());
        pf.update(cp.getProfesoresFijos().getProfesorEjercicio().getIdTipoProfesor(), (ProfesorFijo) pF.getProfesorEjercicio());
        pf.update(cp.getProfesoresFijos().getProfesorInstructor().getIdTipoProfesor(), (ProfesorFijo) pF.getProfesorInstructor());

        pc.update(cp.getProfesoresContratados().getProfesorEspanol().getIdTipoProfesor(), (ProfesorContrato) pC.getProfesorEspanol());
        pc.update(cp.getProfesoresContratados().getProfesorLiteratura().getIdTipoProfesor(), (ProfesorContrato) pC.getProfesorLiteratura());
        pc.update(cp.getProfesoresContratados().getProfesorMatematica().getIdTipoProfesor(), (ProfesorContrato) pC.getProfesorMatematica());
        pc.update(cp.getProfesoresContratados().getProfesorFisica().getIdTipoProfesor(), (ProfesorContrato) pC.getProfesorFisica());
        pc.update(cp.getProfesoresContratados().getProfesorQuimica().getIdTipoProfesor(), (ProfesorContrato) pC.getProfesorQuimica());
        pc.update(cp.getProfesoresContratados().getProfesorBiologia().getIdTipoProfesor(), (ProfesorContrato) pC.getProfesorBiologia());
        pc.update(cp.getProfesoresContratados().getProfesorHistoria().getIdTipoProfesor(), (ProfesorContrato) pC.getProfesorHistoria());
        pc.update(cp.getProfesoresContratados().getProfesorIngles().getIdTipoProfesor(), (ProfesorContrato) pC.getProfesorIngles());
        pc.update(cp.getProfesoresContratados().getProfesorEducacionFisica().getIdTipoProfesor(), (ProfesorContrato) pC.getProfesorEducacionFisica());
        pc.update(cp.getProfesoresContratados().getProfesorEducacionArtistica().getIdTipoProfesor(), (ProfesorContrato) pC.getProfesorEducacionArtistica());
        pc.update(cp.getProfesoresContratados().getProfesorMarxismo().getIdTipoProfesor(), (ProfesorContrato) pC.getProfesorMarxismo());
        pc.update(cp.getProfesoresContratados().getProfesorFundamento().getIdTipoProfesor(), (ProfesorContrato) pC.getProfesorFundamento());
        pc.update(cp.getProfesoresContratados().getProfesorComputacion().getIdTipoProfesor(), (ProfesorContrato) pC.getProfesorComputacion());
        pc.update(cp.getProfesoresContratados().getProfesorMilitar().getIdTipoProfesor(), (ProfesorContrato) pC.getProfesorMilitar());
        pc.update(cp.getProfesoresContratados().getProfesorPractica().getIdTipoProfesor(), (ProfesorContrato) pC.getProfesorPractica());
        pc.update(cp.getProfesoresContratados().getProfesorBasica().getIdTipoProfesor(), (ProfesorContrato) pC.getProfesorBasica());
        pc.update(cp.getProfesoresContratados().getProfesorEjercicio().getIdTipoProfesor(), (ProfesorContrato) pC.getProfesorEjercicio());
        pc.update(cp.getProfesoresContratados().getProfesorInstructor().getIdTipoProfesor(), (ProfesorContrato) pC.getProfesorInstructor());

        int totalfijos = pF.getProfesorEspanol().getTotalProfesores() + pF.getProfesorLiteratura().getTotalProfesores() + pF.getProfesorMatematica().getTotalProfesores() + pF.getProfesorFisica().getTotalProfesores() + pF.getProfesorQuimica().getTotalProfesores() + pF.getProfesorBiologia().getTotalProfesores() + pF.getProfesorHistoria().getTotalProfesores() + pF.getProfesorIngles().getTotalProfesores() + pF.getProfesorEducacionFisica().getTotalProfesores() + pF.getProfesorEducacionArtistica().getTotalProfesores() + pF.getProfesorMarxismo().getTotalProfesores() + pF.getProfesorFundamento().getTotalProfesores() + pF.getProfesorComputacion().getTotalProfesores() + pF.getProfesorMilitar().getTotalProfesores() + pF.getProfesorPractica().getTotalProfesores() + pF.getProfesorBasica().getTotalProfesores() + pF.getProfesorEjercicio().getTotalProfesores() + pF.getProfesorInstructor().getTotalProfesores();
        int totalfijosNoTitulados = ((ProfesorFijo) pF.getProfesorEspanol()).getNoTitulados() + ((ProfesorFijo) pF.getProfesorLiteratura()).getNoTitulados() + ((ProfesorFijo) pF.getProfesorMatematica()).getNoTitulados() + ((ProfesorFijo) pF.getProfesorFisica()).getNoTitulados() + ((ProfesorFijo) pF.getProfesorQuimica()).getNoTitulados() + ((ProfesorFijo) pF.getProfesorBiologia()).getNoTitulados() + ((ProfesorFijo) pF.getProfesorHistoria()).getNoTitulados() + ((ProfesorFijo) pF.getProfesorIngles()).getNoTitulados() + ((ProfesorFijo) pF.getProfesorEducacionFisica()).getNoTitulados() + ((ProfesorFijo) pF.getProfesorEducacionArtistica()).getNoTitulados() + ((ProfesorFijo) pF.getProfesorMarxismo()).getNoTitulados() + ((ProfesorFijo) pF.getProfesorFundamento()).getNoTitulados() + ((ProfesorFijo) pF.getProfesorComputacion()).getNoTitulados() + ((ProfesorFijo) pF.getProfesorMilitar()).getNoTitulados() + ((ProfesorFijo) pF.getProfesorPractica()).getNoTitulados() + ((ProfesorFijo) pF.getProfesorBasica()).getNoTitulados() + ((ProfesorFijo) pF.getProfesorEjercicio()).getNoTitulados() + ((ProfesorFijo) pF.getProfesorInstructor()).getTotalProfesores();
        int totalfijosNoTituladosEstudiando = ((ProfesorFijo) pF.getProfesorEspanol()).getNoTituladosEstudiando() + ((ProfesorFijo) pF.getProfesorLiteratura()).getNoTituladosEstudiando() + ((ProfesorFijo) pF.getProfesorMatematica()).getNoTituladosEstudiando() + ((ProfesorFijo) pF.getProfesorFisica()).getNoTituladosEstudiando() + ((ProfesorFijo) pF.getProfesorQuimica()).getNoTituladosEstudiando() + ((ProfesorFijo) pF.getProfesorBiologia()).getNoTituladosEstudiando() + ((ProfesorFijo) pF.getProfesorHistoria()).getNoTituladosEstudiando() + ((ProfesorFijo) pF.getProfesorIngles()).getNoTituladosEstudiando() + ((ProfesorFijo) pF.getProfesorEducacionFisica()).getNoTituladosEstudiando() + ((ProfesorFijo) pF.getProfesorEducacionArtistica()).getNoTituladosEstudiando() + ((ProfesorFijo) pF.getProfesorMarxismo()).getNoTituladosEstudiando() + ((ProfesorFijo) pF.getProfesorFundamento()).getNoTituladosEstudiando() + ((ProfesorFijo) pF.getProfesorComputacion()).getNoTituladosEstudiando() + ((ProfesorFijo) pF.getProfesorMilitar()).getNoTituladosEstudiando() + ((ProfesorFijo) pF.getProfesorPractica()).getNoTituladosEstudiando() + ((ProfesorFijo) pF.getProfesorBasica()).getNoTituladosEstudiando() + ((ProfesorFijo) pF.getProfesorEjercicio()).getNoTituladosEstudiando() + ((ProfesorFijo) pF.getProfesorInstructor()).getTotalProfesores();

        int totalContratados = pC.getProfesorEspanol().getTotalProfesores() + pC.getProfesorLiteratura().getTotalProfesores() + pC.getProfesorMatematica().getTotalProfesores() + pC.getProfesorFisica().getTotalProfesores() + pC.getProfesorQuimica().getTotalProfesores() + pC.getProfesorBiologia().getTotalProfesores() + pC.getProfesorHistoria().getTotalProfesores() + pC.getProfesorIngles().getTotalProfesores() + pC.getProfesorEducacionFisica().getTotalProfesores() + pC.getProfesorEducacionArtistica().getTotalProfesores() + pC.getProfesorMarxismo().getTotalProfesores() + pC.getProfesorFundamento().getTotalProfesores() + pC.getProfesorComputacion().getTotalProfesores() + pC.getProfesorMilitar().getTotalProfesores() + pC.getProfesorPractica().getTotalProfesores() + pC.getProfesorBasica().getTotalProfesores() + pC.getProfesorEjercicio().getTotalProfesores() + pC.getProfesorInstructor().getTotalProfesores();
        int totalContratadosNoProfesionales = ((ProfesorContrato) pC.getProfesorEspanol()).getNoProfesionales() + ((ProfesorContrato) pC.getProfesorLiteratura()).getNoProfesionales() + ((ProfesorContrato) pC.getProfesorMatematica()).getNoProfesionales() + ((ProfesorContrato) pC.getProfesorFisica()).getNoProfesionales() + ((ProfesorContrato) pC.getProfesorQuimica()).getNoProfesionales() + ((ProfesorContrato) pC.getProfesorBiologia()).getNoProfesionales() + ((ProfesorContrato) pC.getProfesorHistoria()).getNoProfesionales() + ((ProfesorContrato) pC.getProfesorIngles()).getNoProfesionales() + ((ProfesorContrato) pC.getProfesorEducacionFisica()).getNoProfesionales() + ((ProfesorContrato) pC.getProfesorEducacionArtistica()).getNoProfesionales() + ((ProfesorContrato) pC.getProfesorMarxismo()).getNoProfesionales() + ((ProfesorContrato) pC.getProfesorFundamento()).getNoProfesionales() + ((ProfesorContrato) pC.getProfesorComputacion()).getNoProfesionales() + ((ProfesorContrato) pC.getProfesorMilitar()).getNoProfesionales() + ((ProfesorContrato) pC.getProfesorPractica()).getNoProfesionales() + ((ProfesorContrato) pC.getProfesorBasica()).getNoProfesionales() + ((ProfesorContrato) pC.getProfesorEjercicio()).getNoProfesionales() + ((ProfesorContrato) pC.getProfesorInstructor()).getTotalProfesores();
        int totalContratadosNoProfesionalesEstudiando = ((ProfesorContrato) pC.getProfesorEspanol()).getNoProfesionalesEstudiando() + ((ProfesorContrato) pC.getProfesorLiteratura()).getNoProfesionalesEstudiando() + ((ProfesorContrato) pC.getProfesorMatematica()).getNoProfesionalesEstudiando() + ((ProfesorContrato) pC.getProfesorFisica()).getNoProfesionalesEstudiando() + ((ProfesorContrato) pC.getProfesorQuimica()).getNoProfesionalesEstudiando() + ((ProfesorContrato) pC.getProfesorBiologia()).getNoProfesionalesEstudiando() + ((ProfesorContrato) pC.getProfesorHistoria()).getNoProfesionalesEstudiando() + ((ProfesorContrato) pC.getProfesorIngles()).getNoProfesionalesEstudiando() + ((ProfesorContrato) pC.getProfesorEducacionFisica()).getNoProfesionalesEstudiando() + ((ProfesorContrato) pC.getProfesorEducacionArtistica()).getNoProfesionalesEstudiando() + ((ProfesorContrato) pC.getProfesorMarxismo()).getNoProfesionalesEstudiando() + ((ProfesorContrato) pC.getProfesorFundamento()).getNoProfesionalesEstudiando() + ((ProfesorContrato) pC.getProfesorComputacion()).getNoProfesionalesEstudiando() + ((ProfesorContrato) pC.getProfesorMilitar()).getNoProfesionalesEstudiando() + ((ProfesorContrato) pC.getProfesorPractica()).getNoProfesionalesEstudiando() + ((ProfesorContrato) pC.getProfesorBasica()).getNoProfesionalesEstudiando() + ((ProfesorContrato) pC.getProfesorEjercicio()).getNoProfesionalesEstudiando() + ((ProfesorContrato) pC.getProfesorInstructor()).getTotalProfesores();

        n_datos.setTotalProfesoresFijos(totalfijos);
        n_datos.setProfesoresFijosNoTitulados(totalfijosNoTitulados);
        n_datos.setProfesoresFijosNoTituladosEstudiando(totalfijosNoTituladosEstudiando);
        n_datos.setTotalProfesoresContratados(totalContratados);
        n_datos.setProfesoresContratadosNoProfesionales(totalContratadosNoProfesionales);
        n_datos.setProfesoresContratadosNoProfesionalesEstudiando(totalContratadosNoProfesionalesEstudiando);

        Personal docentes = cp.getDocentes();
        docentes.setTotal(totalfijos + totalContratados);

        personales.update(docentes.getIdPersonal(), docentes);
        p_repo.updatePersonalTotalizado(idP, n_datos.getTotalProfesoresFijos(), n_datos.getProfesoresFijosNoTitulados(), n_datos.getProfesoresFijosNoTituladosEstudiando(), n_datos.getTotalProfesoresContratados(), n_datos.getProfesoresContratadosNoProfesionales(), n_datos.getProfesoresContratadosNoProfesionalesEstudiando());
    }

    /**
     * Esta funcionalidad permite eliminar el personal de un centro.
     *
     * @param idP Identificador del personal.
     */
    public void eliminar(long idP) {
        ComposicionPersonal p = obtenerId(idP);
        p_repo.delete(p);
    }

    /**
     * Esta funcionalidad permite listar todos los personales de centros registrados.
     *
     * @return <code>List</code> Todos los personales registrados.
     */
    public List<ComposicionPersonal> listar() {
        return p_repo.findAll();
    }
}