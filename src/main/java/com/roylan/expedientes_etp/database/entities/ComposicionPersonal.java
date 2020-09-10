package com.roylan.expedientes_etp.database.entities;

import javax.persistence.*;

/**
 * Esta clase contiene la información referente al personal de un centro.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Entity
@Table(name = "composicion_personal")
public class ComposicionPersonal {

    @Id
    @GeneratedValue(generator = "seq_composicion_personal")
    @Column(name = "id_personal")
    private long idPersonal;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_docentes")
    private Personal docentes;
    @Column(name = "total_profesores_fijos")
    private int totalProfesoresFijos;
    @Column(name = "profesores_fijos_notitulado")
    private int profesoresFijosNoTitulados;
    @Column(name = "profesores_fijos_notitulado_estudiando")
    private int profesoresFijosNoTituladosEstudiando;
    @Column(name = "total_profesores_contratados")
    private int totalProfesoresContratados;
    @Column(name = "profesores_contratados_noprofesionales")
    private int profesoresContratadosNoProfesionales;
    @Column(name = "profesores_contratados_noprofesionales_estudiando")
    private int profesoresContratadosNoProfesionalesEstudiando;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_bibliotecarios")
    private Personal bibliotecarios;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_instructores_arte")
    private Personal instructoresArte;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_psicopedagogo")
    private Personal psicopedagogos;
    @Column(name = "alumno_maestro")
    private int alumnosMaestros;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_no_docentes")
    private Personal noDocentes;
    @Column(name = "total_profesores_inactivo")
    private int totalProfesoresInactivos;
    @Column(name = "otros_docentes_inactivo")
    private int otrosDocentesInactivos;
    @Column(name = "no_docentes_inactivo")
    private int noDocentesInactivos;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_prof_fijo")
    private ProfesorAsignatura profesoresFijos;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_prof_contratado")
    private ProfesorAsignatura profesoresContratados;

    public ComposicionPersonal(Personal docentes, Personal bibliotecarios, Personal instructoresArte, Personal psicopedagogos, Personal noDocentes, ProfesorAsignatura profesoresFijos, ProfesorAsignatura profesoresContratados) {
        this.docentes = docentes;
        this.totalProfesoresFijos = 0;
        this.profesoresFijosNoTitulados = 0;
        this.profesoresFijosNoTituladosEstudiando = 0;
        this.totalProfesoresContratados = 0;
        this.profesoresContratadosNoProfesionales = 0;
        this.profesoresContratadosNoProfesionalesEstudiando = 0;
        this.bibliotecarios = bibliotecarios;
        this.instructoresArte = instructoresArte;
        this.psicopedagogos = psicopedagogos;
        this.alumnosMaestros = 0;
        this.noDocentes = noDocentes;
        this.totalProfesoresInactivos = 0;
        this.otrosDocentesInactivos = 0;
        this.noDocentesInactivos = 0;
        this.profesoresFijos = profesoresFijos;
        this.profesoresContratados = profesoresContratados;
    }

    public ComposicionPersonal(Personal docentes, int totalProfesoresFijos, int profesoresFijosNoTitulados, int profesoresFijosNoTituladosEstudiando, int totalProfesoresContratados, int profesoresContratadosNoProfesionales, int profesoresContratadosNoProfesionalesEstudiando, Personal bibliotecarios, Personal instructoresArte, Personal psicopedagogos, int alumnosMaestros, Personal noDocentes, int totalProfesoresInactivos, int otrosDocentesInactivos, int noDocentesInactivos, ProfesorAsignatura profesoresFijos, ProfesorAsignatura profesoresContratados) {
        this.docentes = docentes;
        this.totalProfesoresFijos = totalProfesoresFijos;
        this.profesoresFijosNoTitulados = profesoresFijosNoTitulados;
        this.profesoresFijosNoTituladosEstudiando = profesoresFijosNoTituladosEstudiando;
        this.totalProfesoresContratados = totalProfesoresContratados;
        this.profesoresContratadosNoProfesionales = profesoresContratadosNoProfesionales;
        this.profesoresContratadosNoProfesionalesEstudiando = profesoresContratadosNoProfesionalesEstudiando;
        this.bibliotecarios = bibliotecarios;
        this.instructoresArte = instructoresArte;
        this.psicopedagogos = psicopedagogos;
        this.alumnosMaestros = alumnosMaestros;
        this.noDocentes = noDocentes;
        this.totalProfesoresInactivos = totalProfesoresInactivos;
        this.otrosDocentesInactivos = otrosDocentesInactivos;
        this.noDocentesInactivos = noDocentesInactivos;
        this.profesoresFijos = profesoresFijos;
        this.profesoresContratados = profesoresContratados;
    }

    public ComposicionPersonal(Personal docentes, int totalProfesoresFijos, int profesoresFijosNoTitulados, int profesoresFijosNoTituladosEstudiando, int totalProfesoresContratados, int profesoresContratadosNoProfesionales, int profesoresContratadosNoProfesionalesEstudiando, Personal bibliotecarios, Personal instructoresArte, Personal psicopedagogos, int alumnosMaestros, Personal noDocentes, int totalProfesoresInactivos, int otrosDocentesInactivos, int noDocentesInactivos) {
        this.docentes = docentes;
        this.totalProfesoresFijos = totalProfesoresFijos;
        this.profesoresFijosNoTitulados = profesoresFijosNoTitulados;
        this.profesoresFijosNoTituladosEstudiando = profesoresFijosNoTituladosEstudiando;
        this.totalProfesoresContratados = totalProfesoresContratados;
        this.profesoresContratadosNoProfesionales = profesoresContratadosNoProfesionales;
        this.profesoresContratadosNoProfesionalesEstudiando = profesoresContratadosNoProfesionalesEstudiando;
        this.bibliotecarios = bibliotecarios;
        this.instructoresArte = instructoresArte;
        this.psicopedagogos = psicopedagogos;
        this.alumnosMaestros = alumnosMaestros;
        this.noDocentes = noDocentes;
        this.totalProfesoresInactivos = totalProfesoresInactivos;
        this.otrosDocentesInactivos = otrosDocentesInactivos;
        this.noDocentesInactivos = noDocentesInactivos;
    }

    public ComposicionPersonal(Personal docentes, Personal bibliotecarios, Personal instructoresArte, Personal psicopedagogos, int alumnosMaestros, Personal noDocentes, int totalProfesoresInactivos, int otrosDocentesInactivos, int noDocentesInactivos) {
        this.docentes = docentes;
        this.bibliotecarios = bibliotecarios;
        this.instructoresArte = instructoresArte;
        this.psicopedagogos = psicopedagogos;
        this.alumnosMaestros = alumnosMaestros;
        this.noDocentes = noDocentes;
        this.totalProfesoresInactivos = totalProfesoresInactivos;
        this.otrosDocentesInactivos = otrosDocentesInactivos;
        this.noDocentesInactivos = noDocentesInactivos;
    }

    public ComposicionPersonal(ProfesorAsignatura profesoresFijos, ProfesorAsignatura profesoresContratados) {
        this.profesoresFijos = profesoresFijos;
        this.profesoresContratados = profesoresContratados;
    }

    public ComposicionPersonal() {
    }

    public long getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(long idPersonal) {
        this.idPersonal = idPersonal;
    }

    public Personal getDocentes() {
        return docentes;
    }

    public void setDocentes(Personal docentes) {
        this.docentes = docentes;
    }

    public int getTotalProfesoresFijos() {
        return totalProfesoresFijos;
    }

    public void setTotalProfesoresFijos(int totalProfesoresFijos) {
        this.totalProfesoresFijos = totalProfesoresFijos;
    }

    public int getProfesoresFijosNoTitulados() {
        return profesoresFijosNoTitulados;
    }

    public void setProfesoresFijosNoTitulados(int profesoresFijosNoTitulados) {
        this.profesoresFijosNoTitulados = profesoresFijosNoTitulados;
    }

    public int getProfesoresFijosNoTituladosEstudiando() {
        return profesoresFijosNoTituladosEstudiando;
    }

    public void setProfesoresFijosNoTituladosEstudiando(int profesoresFijosNoTituladosEstudiando) {
        this.profesoresFijosNoTituladosEstudiando = profesoresFijosNoTituladosEstudiando;
    }

    public int getTotalProfesoresContratados() {
        return totalProfesoresContratados;
    }

    public void setTotalProfesoresContratados(int totalProfesoresContratados) {
        this.totalProfesoresContratados = totalProfesoresContratados;
    }

    public int getProfesoresContratadosNoProfesionales() {
        return profesoresContratadosNoProfesionales;
    }

    public void setProfesoresContratadosNoProfesionales(int profesoresContratadosNoProfesionales) {
        this.profesoresContratadosNoProfesionales = profesoresContratadosNoProfesionales;
    }

    public int getProfesoresContratadosNoProfesionalesEstudiando() {
        return profesoresContratadosNoProfesionalesEstudiando;
    }

    public void setProfesoresContratadosNoProfesionalesEstudiando(int profesoresContratadosNoProfesionalesEstudiando) {
        this.profesoresContratadosNoProfesionalesEstudiando = profesoresContratadosNoProfesionalesEstudiando;
    }

    public Personal getBibliotecarios() {
        return bibliotecarios;
    }

    public void setBibliotecarios(Personal bibliotecarios) {
        this.bibliotecarios = bibliotecarios;
    }

    public Personal getInstructoresArte() {
        return instructoresArte;
    }

    public void setInstructoresArte(Personal instructoresArte) {
        this.instructoresArte = instructoresArte;
    }

    public Personal getPsicopedagogos() {
        return psicopedagogos;
    }

    public void setPsicopedagogos(Personal psicopedagogos) {
        this.psicopedagogos = psicopedagogos;
    }

    public int getAlumnosMaestros() {
        return alumnosMaestros;
    }

    public void setAlumnosMaestros(int alumnosMaestros) {
        this.alumnosMaestros = alumnosMaestros;
    }

    public Personal getNoDocentes() {
        return noDocentes;
    }

    public void setNoDocentes(Personal noDocentes) {
        this.noDocentes = noDocentes;
    }

    public int getTotalProfesoresInactivos() {
        return totalProfesoresInactivos;
    }

    public void setTotalProfesoresInactivos(int totalProfesoresInactivos) {
        this.totalProfesoresInactivos = totalProfesoresInactivos;
    }

    public int getOtrosDocentesInactivos() {
        return otrosDocentesInactivos;
    }

    public void setOtrosDocentesInactivos(int otrosDocentesInactivos) {
        this.otrosDocentesInactivos = otrosDocentesInactivos;
    }

    public int getNoDocentesInactivos() {
        return noDocentesInactivos;
    }

    public void setNoDocentesInactivos(int noDocentesInactivos) {
        this.noDocentesInactivos = noDocentesInactivos;
    }

    public ProfesorAsignatura getProfesoresFijos() {
        return profesoresFijos;
    }

    public void setProfesoresFijos(ProfesorAsignatura profesoresFijos) {
        this.profesoresFijos = profesoresFijos;
    }

    public ProfesorAsignatura getProfesoresContratados() {
        return profesoresContratados;
    }

    public void setProfesoresContratados(ProfesorAsignatura profesoresContratados) {
        this.profesoresContratados = profesoresContratados;
    }

    public int getTotalInactivos() {
        return getTotalProfesoresInactivos() + getOtrosDocentesInactivos() + getNoDocentesInactivos();
    }

    public int getTotalDocentes() {
        return getDocentes().getTotal() + getOtrosDocentesTotal();
    }

    public int getTotalDocentesHembras() {
        return getDocentes().getHembras() + getOtrosDocentesHembrasTotal();
    }

    public int getOtrosDocentesTotal() {
        return getBibliotecarios().getTotal() + getInstructoresArte().getTotal() + getPsicopedagogos().getTotal();
    }

    public int getOtrosDocentesHembrasTotal() {
        return getBibliotecarios().getHembras() + getInstructoresArte().getHembras() + getPsicopedagogos().getHembras();
    }
}