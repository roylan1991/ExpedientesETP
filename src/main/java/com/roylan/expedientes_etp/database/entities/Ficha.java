package com.roylan.expedientes_etp.database.entities;

/**
 * Esta clase contiene la información referente a la ficha.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
public class Ficha {

    private String concepto;
    private int total;
    private int hembras;
    private int internos;
    private int seminternos;
    private int nuevosIngresos;
    private int docentesTotal;
    private int docentesHembras;
    private int frenteAula;
    private float relacion_alum_prof;

    public Ficha(String concepto, int total, int hembras, int internos, int seminternos, int nuevosIngresos, int docentesTotal, int docentesHembras, int frenteAula, float relacion_alum_prof) {
        this.concepto = concepto;
        this.total = total;
        this.hembras = hembras;
        this.internos = internos;
        this.seminternos = seminternos;
        this.nuevosIngresos = nuevosIngresos;
        this.docentesTotal = docentesTotal;
        this.docentesHembras = docentesHembras;
        this.frenteAula = frenteAula;
        this.relacion_alum_prof = relacion_alum_prof;
    }

    public Ficha() {
    }

    public String getConcepto() {
        return concepto;
    }

    public int getTotal() {
        return total;
    }

    public int getHembras() {
        return hembras;
    }

    public int getInternos() {
        return internos;
    }

    public int getSeminternos() {
        return seminternos;
    }

    public int getNuevosIngresos() {
        return nuevosIngresos;
    }

    public int getDocentesTotal() {
        return docentesTotal;
    }

    public int getDocentesHembras() {
        return docentesHembras;
    }

    public int getFrenteAula() {
        return frenteAula;
    }

    public float getRelacionAlumProf() {
        return relacion_alum_prof;
    }
}
