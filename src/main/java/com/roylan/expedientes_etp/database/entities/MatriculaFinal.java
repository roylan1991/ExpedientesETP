package com.roylan.expedientes_etp.database.entities;

import javax.persistence.*;

/**
 * Esta clase contiene la información referente a la  matrícula final de un curso.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Entity
@Table(name = "matriculas_finales")
public class MatriculaFinal {

    @Id
    @GeneratedValue(generator = "seq_matricula_final")
    @Column(name = "id_matriculaf")
    private long idMatricula;
    @Column(name = "matricula_final")
    private int matriculaFinal;
    @Column(name = "aprobados")
    private int aprobados;
    @Column(name = "graduados")
    private int graduados;
    @OneToOne
    @JoinColumn(name = "id_nivel")
    private Nivel nivel;
    @OneToOne
    @JoinColumn(name = "id_curso")
    private Curso curso;
    @OneToOne
    @JoinColumn(name = "id_caratula")
    private Caratula caratula;

    public MatriculaFinal(int matriculaFinal, int aprobados, int graduados) {
        this.matriculaFinal = matriculaFinal;
        this.aprobados = aprobados;
        this.graduados = graduados;
    }

    public MatriculaFinal(Nivel nivel, Curso curso) {
        setParametros();
        this.nivel = nivel;
        this.curso = curso;
    }

    public MatriculaFinal() {
    }

    public long getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(long idMatricula) {
        this.idMatricula = idMatricula;
    }

    public int getMatriculaFinal() {
        return matriculaFinal;
    }

    public void setMatriculaFinal(int matriculaFinal) {
        this.matriculaFinal = matriculaFinal;
    }

    public int getAprobados() {
        return aprobados;
    }

    public void setAprobados(int aprobados) {
        this.aprobados = aprobados;
    }

    public int getGraduados() {
        return graduados;
    }

    public void setGraduados(int graduados) {
        this.graduados = graduados;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Caratula getCaratula() {
        return caratula;
    }

    public void setCaratula(Caratula caratula) {
        this.caratula = caratula;
    }

    public void setParametros() {
        this.matriculaFinal = 0;
        this.aprobados = 0;
        this.graduados = 0;
    }
}
