package com.roylan.expedientes_etp.database.entities;

import javax.persistence.*;

/**
 * Esta clase contiene la información referente a una especialidad actual.
 *
 * @author Roylan Bressler
 * @since 1.0
 */

@Entity
@Table(name = "especialidades_actuales")
public class EspecialidadActual {

    @Id
    @GeneratedValue(generator = "seq_especialidades_actuales")
    @Column(name = "id_esp_actual")
    private long idEspActual;
    @OneToOne
    @JoinColumn(name = "id_anno")
    private AnnoEstudio annoEstudio;
    @Column(name = "cant_grupos")
    private int cantGrupos;
    @Column(name = "ni_promovidos_total")
    private int nIngPromovidosTotal;
    @Column(name = "ni_promovidos_por_traslado")
    private int nIngPromovidosPorTraslado;
    @Column(name = "repitentes_total")
    private int repitentesTotal;
    @Column(name = "repitentes_por_traslado")
    private int repitentesPorTraslado;
    @Column(name = "reingresos")
    private int reingresos;
    @Column(name = "matricula_actual_total")
    private int matriculaActualTotal;
    @Column(name = "matricula_actual_hembras")
    private int matriculaActualHembras;
    @ManyToOne
    @JoinColumn(name = "id_planilla")
    private PlanillaDatos planilla;

    public EspecialidadActual(AnnoEstudio annoEstudio, int cantGrupos, int nIngPromovidosTotal, int nIngPromovidosPorTraslado, int repitentesTotal, int repitentesPorTraslado, int reingresos, int matriculaActualTotal, int matriculaActualHembras, PlanillaDatos planilla) {
        this.annoEstudio = annoEstudio;
        this.cantGrupos = cantGrupos;
        this.nIngPromovidosTotal = nIngPromovidosTotal;
        this.nIngPromovidosPorTraslado = nIngPromovidosPorTraslado;
        this.repitentesTotal = repitentesTotal;
        this.repitentesPorTraslado = repitentesPorTraslado;
        this.reingresos = reingresos;
        this.matriculaActualTotal = matriculaActualTotal;
        this.matriculaActualHembras = matriculaActualHembras;
        this.planilla = planilla;
    }

    public EspecialidadActual(AnnoEstudio annoEstudio, int cantGrupos, int nIngPromovidosTotal, int nIngPromovidosPorTraslado, int repitentesTotal, int repitentesPorTraslado, int reingresos, int matriculaActualTotal, int matriculaActualHembras) {
        this.annoEstudio = annoEstudio;
        this.cantGrupos = cantGrupos;
        this.nIngPromovidosTotal = nIngPromovidosTotal;
        this.nIngPromovidosPorTraslado = nIngPromovidosPorTraslado;
        this.repitentesTotal = repitentesTotal;
        this.repitentesPorTraslado = repitentesPorTraslado;
        this.reingresos = reingresos;
        this.matriculaActualTotal = matriculaActualTotal;
        this.matriculaActualHembras = matriculaActualHembras;
    }

    public EspecialidadActual() {
    }

    public long getIdEspActual() {
        return idEspActual;
    }

    public void setIdEspActual(long idEspActual) {
        this.idEspActual = idEspActual;
    }

    public AnnoEstudio getAnnoEstudio() {
        return annoEstudio;
    }

    public void setAnnoEstudio(AnnoEstudio annoEstudio) {
        this.annoEstudio = annoEstudio;
    }

    public int getCantGrupos() {
        return cantGrupos;
    }

    public void setCantGrupos(int cantGrupos) {
        this.cantGrupos = cantGrupos;
    }

    public int getnIngPromovidosTotal() {
        return nIngPromovidosTotal;
    }

    public void setnIngPromovidosTotal(int nIngPromovidosTotal) {
        this.nIngPromovidosTotal = nIngPromovidosTotal;
    }

    public int getnIngPromovidosPorTraslado() {
        return nIngPromovidosPorTraslado;
    }

    public void setnIngPromovidosPorTraslado(int nIngPromovidosPorTraslado) {
        this.nIngPromovidosPorTraslado = nIngPromovidosPorTraslado;
    }

    public int getRepitentesTotal() {
        return repitentesTotal;
    }

    public void setRepitentesTotal(int repitentesTotal) {
        this.repitentesTotal = repitentesTotal;
    }

    public int getRepitentesPorTraslado() {
        return repitentesPorTraslado;
    }

    public void setRepitentesPorTraslado(int repitentesPorTraslado) {
        this.repitentesPorTraslado = repitentesPorTraslado;
    }

    public int getReingresos() {
        return reingresos;
    }

    public void setReingresos(int reingresos) {
        this.reingresos = reingresos;
    }

    public int getMatriculaActualTotal() {
        return matriculaActualTotal;
    }

    public void setMatriculaActualTotal(int matriculaActualTotal) {
        this.matriculaActualTotal = matriculaActualTotal;
    }

    public int getMatriculaActualHembras() {
        return matriculaActualHembras;
    }

    public void setMatriculaActualHembras(int matriculaActualHembras) {
        this.matriculaActualHembras = matriculaActualHembras;
    }

    public PlanillaDatos getPlanilla() {
        return planilla;
    }

    public void setPlanilla(PlanillaDatos planilla) {
        this.planilla = planilla;
    }

    public void setParametros(int cantGrupos, int nIngPromovidosTotal, int nIngPromovidosPorTraslado, int repitentesTotal, int repitentesPorTraslado, int reingresos, int matriculaActualTotal, int matriculaActualHembras) {
        this.cantGrupos = cantGrupos;
        this.nIngPromovidosTotal = nIngPromovidosTotal;
        this.nIngPromovidosPorTraslado = nIngPromovidosPorTraslado;
        this.repitentesTotal = repitentesTotal;
        this.repitentesPorTraslado = repitentesPorTraslado;
        this.reingresos = reingresos;
        this.matriculaActualTotal = matriculaActualTotal;
        this.matriculaActualHembras = matriculaActualHembras;
    }

    @Override
    public String toString() {
        return "EspecialidadActual{" +
                "idEspActual=" + idEspActual +
                ", annoEstudio=" + annoEstudio.getTipoAnno() +
                ", cantGrupos=" + cantGrupos +
                ", matriculaActualTotal=" + matriculaActualTotal +
                ", nIngPromovidosTotal=" + nIngPromovidosTotal +
                ", repitentesTotal=" + repitentesTotal +
                ", reingresos=" + reingresos +
                '}';
    }
}