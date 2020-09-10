package com.roylan.expedientes_etp.database.entities;

import javax.persistence.*;

/**
 * Esta clase contiene la información referente al rango de edades de un tipo de curso.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Entity
@Table(name = "rangos_edades")
public class RangoEdad {

    @Id
    @GeneratedValue(generator = "seq_rangos_edades")
    @Column(name = "id_rango_edad")
    private long idRangoEdad;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_rango_d")
    private Rango diurno;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_rango_cpt")
    private Rango cpt;
    @OneToOne(mappedBy = "rangosEdades")
    private Centro centro;

    public RangoEdad(Rango diurno, Rango cpt) {
        this.diurno = diurno;
        this.cpt = cpt;
    }

    public RangoEdad() {
    }

    public long getIdRangoEdad() {
        return idRangoEdad;
    }

    public void setIdRangoEdad(long idRangoEdad) {
        this.idRangoEdad = idRangoEdad;
    }

    public Rango getDiurno() {
        return diurno;
    }

    public void setDiurno(Rango diurno) {
        this.diurno = diurno;
    }

    public Rango getCpt() {
        return cpt;
    }

    public void setCpt(Rango cpt) {
        this.cpt = cpt;
    }

    public Centro getCentro() {
        return centro;
    }

    public void setCentro(Centro centro) {
        this.centro = centro;
    }

    @Override
    public String toString() {
        return "RangoEdad{" +
                "idRangoEdad=" + idRangoEdad +
                ", diurno=" + diurno.getMatriculaRangoTotal() +
                ", cpt=" + cpt.getMatriculaRangoTotal() +
                ", centro=" + centro.getNombCentro() +
                '}';
    }
}