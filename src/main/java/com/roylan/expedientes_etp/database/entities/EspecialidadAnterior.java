package com.roylan.expedientes_etp.database.entities;

import javax.persistence.*;

/**
 * Esta clase contiene la información referente a una especialidad anterior.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Entity
@Table(name = "especialidades_anteriores")
public class EspecialidadAnterior {

    @Id
    @GeneratedValue(generator = "seq_especialidades_anteriores")
    @Column(name = "id_esp_anterior")
    private long idEspAnterior;
    @OneToOne
    @JoinColumn(name = "id_anno")
    private AnnoEstudio annoEstudio;
    @Column(name = "matricula_inicial")
    private int matriculaInicial;
    @Column(name = "altas_total")
    private int altasTotal;
    @Column(name = "altasPorTraslado")
    private int altasPorTraslado;
    @Column(name = "matricula_inicial_aj")
    private int matricInicialAjustada;
    @Column(name = "matricula_final")
    private int matriculaFinal;
    @Column(name = "aprobados")
    private int aprobados;
    @Column(name = "bajas_total")
    private int bajasTotal;
    @Column(name = "bajasPorTraslado")
    private int bajasPorTraslado;
    @ManyToOne
    @JoinColumn(name = "id_planilla")
    private PlanillaDatos planilla;

    public EspecialidadAnterior(AnnoEstudio annoEstudio, int matriculaInicial, int altasTotal, int altasPorTraslado, int matricInicialAjustada, int matriculaFinal, int aprobados, int bajasTotal, int bajasPorTraslado, PlanillaDatos planilla) {
        this.annoEstudio = annoEstudio;
        this.matriculaInicial = matriculaInicial;
        this.altasTotal = altasTotal;
        this.altasPorTraslado = altasPorTraslado;
        this.matricInicialAjustada = matricInicialAjustada;
        this.matriculaFinal = matriculaFinal;
        this.aprobados = aprobados;
        this.bajasTotal = bajasTotal;
        this.bajasPorTraslado = bajasPorTraslado;
        this.planilla = planilla;
    }

    public EspecialidadAnterior(AnnoEstudio annoEstudio, int matriculaInicial, int altasTotal, int altasPorTraslado, int matricInicialAjustada, int matriculaFinal, int aprobados, int bajasTotal, int bajasPorTraslado) {
        this.annoEstudio = annoEstudio;
        this.matriculaInicial = matriculaInicial;
        this.altasTotal = altasTotal;
        this.altasPorTraslado = altasPorTraslado;
        this.matricInicialAjustada = matricInicialAjustada;
        this.matriculaFinal = matriculaFinal;
        this.aprobados = aprobados;
        this.bajasTotal = bajasTotal;
        this.bajasPorTraslado = bajasPorTraslado;
    }

    public EspecialidadAnterior() {
    }

    public long getIdEspAnterior() {
        return idEspAnterior;
    }

    public void setIdEspAnterior(long idEspAnterior) {
        this.idEspAnterior = idEspAnterior;
    }

    public AnnoEstudio getAnnoEstudio() {
        return annoEstudio;
    }

    public void setAnnoEstudio(AnnoEstudio annoEstudio) {
        this.annoEstudio = annoEstudio;
    }

    public int getMatriculaInicial() {
        return matriculaInicial;
    }

    public void setMatriculaInicial(int matriculaInicial) {
        this.matriculaInicial = matriculaInicial;
    }

    public int getAltasTotal() {
        return altasTotal;
    }

    public void setAltasTotal(int altasTotal) {
        this.altasTotal = altasTotal;
    }

    public int getAltasPorTraslado() {
        return altasPorTraslado;
    }

    public void setAltasPorTraslado(int altasPorTraslado) {
        this.altasPorTraslado = altasPorTraslado;
    }

    public int getMatricInicialAjustada() {
        return matricInicialAjustada;
    }

    public void setMatricInicialAjustada(int matricInicialAjustada) {
        this.matricInicialAjustada = matricInicialAjustada;
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

    public int getBajasTotal() {
        return bajasTotal;
    }

    public void setBajasTotal(int bajasTotal) {
        this.bajasTotal = bajasTotal;
    }

    public int getBajasPorTraslado() {
        return bajasPorTraslado;
    }

    public void setBajasPorTraslado(int bajasPorTraslado) {
        this.bajasPorTraslado = bajasPorTraslado;
    }

    public PlanillaDatos getPlanilla() {
        return planilla;
    }

    public void setPlanilla(PlanillaDatos planilla) {
        this.planilla = planilla;
    }

    public void setParametros(int matriculaInicial, int altasTotal, int altasPorTraslado, int matricInicialAjustada, int matriculaFinal, int aprobados, int bajasTotal, int bajasPorTraslado) {
        this.matriculaInicial = matriculaInicial;
        this.matriculaInicial = matriculaInicial;
        this.altasTotal = altasTotal;
        this.altasPorTraslado = altasPorTraslado;
        this.matricInicialAjustada = matricInicialAjustada;
        this.matriculaFinal = matriculaFinal;
        this.aprobados = aprobados;
        this.bajasTotal = bajasTotal;
        this.bajasPorTraslado = bajasPorTraslado;
    }
}
