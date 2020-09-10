package com.roylan.expedientes_etp.database.entities;

import javax.persistence.*;

/**
 * Esta clase contiene la información referente a un rango de edad.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Entity
@Table(name = "rangos")
public class Rango {

    @Id
    @GeneratedValue(generator = "seq_rangos")
    @Column(name = "id_rango")
    private long idRango;
    @Column(name = "total_mn15")
    private int totalMenos15;
    @Column(name = "hembras_mn15")
    private int hembrasMenos15;
    @Column(name = "total_en15_16")
    private int totalEntre15_16;
    @Column(name = "hembras_en15_16")
    private int hembrasEntre15_16;
    @Column(name = "total_my16")
    private int totalMayores16;
    @Column(name = "hembras_my16")
    private int hembrasMayores16;
    @OneToOne
    @JoinColumn(name = "id_curso")
    private Curso curso;
    @OneToOne
    @JoinColumn(name = "id_rango_edad")
    private RangoEdad rangoEdad;

    public Rango(Curso curso) {
        this.totalMenos15 = 0;
        this.hembrasMenos15 = 0;
        this.totalEntre15_16 = 0;
        this.hembrasEntre15_16 = 0;
        this.totalMayores16 = 0;
        this.hembrasMayores16 = 0;
        this.curso = curso;
    }

    public Rango(int totalMenos15, int hembrasMenos15, int totalEntre15_16, int hembrasEntre15_16, int totalMayores16, int hembrasMayores16) {
        this.totalMenos15 = totalMenos15;
        this.hembrasMenos15 = hembrasMenos15;
        this.totalEntre15_16 = totalEntre15_16;
        this.hembrasEntre15_16 = hembrasEntre15_16;
        this.totalMayores16 = totalMayores16;
        this.hembrasMayores16 = hembrasMayores16;
    }

    public Rango(int totalMenos15, int hembrasMenos15, int totalEntre15_16, int hembrasEntre15_16, int totalMayores16, int hembrasMayores16, Curso curso) {
        this.totalMenos15 = totalMenos15;
        this.hembrasMenos15 = hembrasMenos15;
        this.totalEntre15_16 = totalEntre15_16;
        this.hembrasEntre15_16 = hembrasEntre15_16;
        this.totalMayores16 = totalMayores16;
        this.hembrasMayores16 = hembrasMayores16;
        this.curso = curso;
    }

    public Rango() {
    }

    public long getIdRango() {
        return idRango;
    }

    public void setIdRango(long idRango) {
        this.idRango = idRango;
    }

    public int getTotalMenos15() {
        return totalMenos15;
    }

    public void setTotalMenos15(int totalMenos15) {
        this.totalMenos15 = totalMenos15;
    }

    public int getHembrasMenos15() {
        return hembrasMenos15;
    }

    public void setHembrasMenos15(int hembrasMenos15) {
        this.hembrasMenos15 = hembrasMenos15;
    }

    public int getTotalEntre15_16() {
        return totalEntre15_16;
    }

    public void setTotalEntre15_16(int totalEntre15_16) {
        this.totalEntre15_16 = totalEntre15_16;
    }

    public int getHembrasEntre15_16() {
        return hembrasEntre15_16;
    }

    public void setHembrasEntre15_16(int hembrasEntre15_16) {
        this.hembrasEntre15_16 = hembrasEntre15_16;
    }

    public int getTotalMayores16() {
        return totalMayores16;
    }

    public void setTotalMayores16(int totalMayores16) {
        this.totalMayores16 = totalMayores16;
    }

    public int getHembrasMayores16() {
        return hembrasMayores16;
    }

    public void setHembrasMayores16(int hembrasMayores16) {
        this.hembrasMayores16 = hembrasMayores16;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public RangoEdad getRangoEdad() {
        return rangoEdad;
    }

    public void setRangoEdad(RangoEdad rangoEdad) {
        this.rangoEdad = rangoEdad;
    }

    public int getMatriculaRangoTotal() {
        return getTotalMenos15() + getTotalEntre15_16() + getTotalMayores16();
    }

    public int getMatriculaRangoTotalHembras() {
        return getHembrasMenos15() + getHembrasEntre15_16() + getHembrasMayores16();
    }
}