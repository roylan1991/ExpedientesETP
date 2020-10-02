package com.roylan.expedientes_etp.database.entities;

import javax.persistence.*;

/**
 * Esta clase contiene la información referente a las  matrícula de un curso iniciado.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Entity
@Table(name = "matriculas_iniciales")
public class MatriculaInicial {

    @Id
    @GeneratedValue(generator = "seq_matricula_inicial")
    @Column(name = "id_matriculai")
    private long idMatricula;
    @Column(name = "total")
    private int total;
    @Column(name = "total_hembras")
    private int totalHembras;
    @Column(name = "n_ingreso")
    private int nuevoIngreso;
    @Column(name = "n_ingreso_hembras")
    private int nuevoIngresoHembras;
    @OneToOne
    @JoinColumn(name = "id_nivel")
    private Nivel nivel;
    @OneToOne
    @JoinColumn(name = "id_curso")
    private Curso curso;
    @OneToOne
    @JoinColumn(name = "id_caratula")
    private Caratula caratula;

    public MatriculaInicial(int total, int totalHembras, int nuevoIngreso, int nuevoIngresoHembras) {
        this.total = total;
        this.totalHembras = totalHembras;
        this.nuevoIngreso = nuevoIngreso;
        this.nuevoIngresoHembras = nuevoIngresoHembras;
    }

    public MatriculaInicial(Nivel nivel, Curso curso) {
        setParametros();
        this.nivel = nivel;
        this.curso = curso;
    }

    public MatriculaInicial() {
    }

    public long getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(long idMatricula) {
        this.idMatricula = idMatricula;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalHembras() {
        return totalHembras;
    }

    public void setTotalHembras(int totalHembras) {
        this.totalHembras = totalHembras;
    }

    public int getNuevoIngreso() {
        return nuevoIngreso;
    }

    public void setNuevoIngreso(int nuevoIngreso) {
        this.nuevoIngreso = nuevoIngreso;
    }

    public int getNuevoIngresoHembras() {
        return nuevoIngresoHembras;
    }

    public void setNuevoIngresoHembras(int nuevoIngresoHembras) {
        this.nuevoIngresoHembras = nuevoIngresoHembras;
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
        this.total = 0;
        this.totalHembras = 0;
        this.nuevoIngreso = 0;
        this.nuevoIngresoHembras = 0;
    }
}