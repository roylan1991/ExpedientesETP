package com.roylan.expedientes_etp.database.entities;

import javax.persistence.*;

/**
 * Esta clase contiene la información referente a los profesores por asignaturas.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Entity
@Table(name = "profesores_asignaturas")
public class ProfesorAsignatura {

    @Id
    @GeneratedValue(generator = "seq_profesores_asignaturas")
    @Column(name = "id_prof_asig")
    private long idProfAsig;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_espanol")
    private TipoProfesor profesorEspanol;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_literatura")
    private TipoProfesor profesorLiteratura;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_matematica")
    private TipoProfesor profesorMatematica;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_fisica")
    private TipoProfesor profesorFisica;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_quimica")
    private TipoProfesor profesorQuimica;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_biologia")
    private TipoProfesor profesorBiologia;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_historia")
    private TipoProfesor profesorHistoria;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_ingles")
    private TipoProfesor profesorIngles;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_educ_fisica")
    private TipoProfesor profesorEducacionFisica;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_educ_artistica")
    private TipoProfesor profesorEducacionArtistica;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_marxismo")
    private TipoProfesor profesorMarxismo;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_fundamento")
    private TipoProfesor profesorFundamento;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_computacion")
    private TipoProfesor profesorComputacion;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_militar")
    private TipoProfesor profesorMilitar;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_practica")
    private TipoProfesor profesorPractica;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_basica")
    private TipoProfesor profesorBasica;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_ejercicio")
    private TipoProfesor profesorEjercicio;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_instructor")
    private TipoProfesor profesorInstructor;

    public ProfesorAsignatura(TipoProfesor profesorEspanol, TipoProfesor profesorLiteratura, TipoProfesor profesorMatematica, TipoProfesor profesorFisica, TipoProfesor profesorQuimica, TipoProfesor profesorBiologia, TipoProfesor profesorHistoria, TipoProfesor profesorIngles, TipoProfesor profesorEducacionFisica, TipoProfesor profesorEducacionArtistica, TipoProfesor profesorMarxismo, TipoProfesor profesorFundamento, TipoProfesor profesorComputacion, TipoProfesor profesorMilitar, TipoProfesor profesorPractica, TipoProfesor profesorBasica, TipoProfesor profesorEjercicio, TipoProfesor profesorInstructor) {
        this.profesorEspanol = profesorEspanol;
        this.profesorLiteratura = profesorLiteratura;
        this.profesorMatematica = profesorMatematica;
        this.profesorFisica = profesorFisica;
        this.profesorQuimica = profesorQuimica;
        this.profesorBiologia = profesorBiologia;
        this.profesorHistoria = profesorHistoria;
        this.profesorIngles = profesorIngles;
        this.profesorEducacionFisica = profesorEducacionFisica;
        this.profesorEducacionArtistica = profesorEducacionArtistica;
        this.profesorMarxismo = profesorMarxismo;
        this.profesorFundamento = profesorFundamento;
        this.profesorComputacion = profesorComputacion;
        this.profesorMilitar = profesorMilitar;
        this.profesorPractica = profesorPractica;
        this.profesorBasica = profesorBasica;
        this.profesorEjercicio = profesorEjercicio;
        this.profesorInstructor = profesorInstructor;
    }

    public ProfesorAsignatura() {
    }

    public long getIdProfesorAsig() {
        return idProfAsig;
    }

    public void setIdProfesorAsig(long idProfAsig) {
        this.idProfAsig = idProfAsig;
    }

    public TipoProfesor getProfesorEspanol() {
        return profesorEspanol;
    }

    public void setProfesorEspanol(TipoProfesor profesorEspanol) {
        this.profesorEspanol = profesorEspanol;
    }

    public TipoProfesor getProfesorLiteratura() {
        return profesorLiteratura;
    }

    public void setProfesorLiteratura(TipoProfesor profesorLiteratura) {
        this.profesorLiteratura = profesorLiteratura;
    }

    public TipoProfesor getProfesorMatematica() {
        return profesorMatematica;
    }

    public void setProfesorMatematica(TipoProfesor profesorMatematica) {
        this.profesorMatematica = profesorMatematica;
    }

    public TipoProfesor getProfesorFisica() {
        return profesorFisica;
    }

    public void setProfesorFisica(TipoProfesor profesorFisica) {
        this.profesorFisica = profesorFisica;
    }

    public TipoProfesor getProfesorQuimica() {
        return profesorQuimica;
    }

    public void setProfesorQuimica(TipoProfesor profesorQuimica) {
        this.profesorQuimica = profesorQuimica;
    }

    public TipoProfesor getProfesorBiologia() {
        return profesorBiologia;
    }

    public void setProfesorBiologia(TipoProfesor profesorBiologia) {
        this.profesorBiologia = profesorBiologia;
    }

    public TipoProfesor getProfesorHistoria() {
        return profesorHistoria;
    }

    public void setProfesorHistoria(TipoProfesor profesorHistoria) {
        this.profesorHistoria = profesorHistoria;
    }

    public TipoProfesor getProfesorIngles() {
        return profesorIngles;
    }

    public void setProfesorIngles(TipoProfesor profesorIngles) {
        this.profesorIngles = profesorIngles;
    }

    public TipoProfesor getProfesorEducacionFisica() {
        return profesorEducacionFisica;
    }

    public void setProfesorEducacionFisica(TipoProfesor profesorEducacionFisica) {
        this.profesorEducacionFisica = profesorEducacionFisica;
    }

    public TipoProfesor getProfesorEducacionArtistica() {
        return profesorEducacionArtistica;
    }

    public void setProfesorEducacionArtistica(TipoProfesor profesorEducacionArtistica) {
        this.profesorEducacionArtistica = profesorEducacionArtistica;
    }

    public TipoProfesor getProfesorMarxismo() {
        return profesorMarxismo;
    }

    public void setProfesorMarxismo(TipoProfesor profesorMarxismo) {
        this.profesorMarxismo = profesorMarxismo;
    }

    public TipoProfesor getProfesorFundamento() {
        return profesorFundamento;
    }

    public void setProfesorFundamento(TipoProfesor profesorFundamento) {
        this.profesorFundamento = profesorFundamento;
    }

    public TipoProfesor getProfesorComputacion() {
        return profesorComputacion;
    }

    public void setProfesorComputacion(TipoProfesor profesorComputacion) {
        this.profesorComputacion = profesorComputacion;
    }

    public TipoProfesor getProfesorMilitar() {
        return profesorMilitar;
    }

    public void setProfesorMilitar(TipoProfesor profesorMilitar) {
        this.profesorMilitar = profesorMilitar;
    }

    public TipoProfesor getProfesorPractica() {
        return profesorPractica;
    }

    public void setProfesorPractica(TipoProfesor profesorPractica) {
        this.profesorPractica = profesorPractica;
    }

    public TipoProfesor getProfesorBasica() {
        return profesorBasica;
    }

    public void setProfesorBasica(TipoProfesor profesorBasica) {
        this.profesorBasica = profesorBasica;
    }

    public TipoProfesor getProfesorEjercicio() {
        return profesorEjercicio;
    }

    public void setProfesorEjercicio(TipoProfesor profesorEjercicio) {
        this.profesorEjercicio = profesorEjercicio;
    }

    public TipoProfesor getProfesorInstructor() {
        return profesorInstructor;
    }

    public void setProfesorInstructor(TipoProfesor profesorInstructor) {
        this.profesorInstructor = profesorInstructor;
    }
}
