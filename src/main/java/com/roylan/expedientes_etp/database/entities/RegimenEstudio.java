package com.roylan.expedientes_etp.database.entities;

import javax.persistence.*;

/**
 * Esta clase contiene la información referente al régimen de estudio.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Entity
@Table(name = "regimenes_estudio")
public class RegimenEstudio {

    @Id
    @GeneratedValue(generator = "seq_regimenes_estudio")
    @Column(name = "id_regimen")
    private long idRegimen;
    @Column(name = "internos")
    private int internos;
    @Column(name = "internos_hembras")
    private int internosHembras;
    @Column(name = "seminternos")
    private int seminternos;
    @Column(name = "seminternos_hembras")
    private int seminternosHembras;
    @OneToOne
    @JoinColumn(name = "id_nivel")
    private Nivel nivel;
    @OneToOne
    @JoinColumn(name = "id_caratula")
    private Caratula caratula;

    public RegimenEstudio(int internos, int internosHembras, int seminternos, int seminternosHembras, Nivel nivel) {
        this.internos = internos;
        this.internosHembras = internosHembras;
        this.seminternos = seminternos;
        this.seminternosHembras = seminternosHembras;
        this.nivel = nivel;
    }

    public RegimenEstudio(int internos, int internosHembras, int seminternos, int seminternosHembras) {
        this.internos = internos;
        this.internosHembras = internosHembras;
        this.seminternos = seminternos;
        this.seminternosHembras = seminternosHembras;
    }

    public RegimenEstudio(Nivel nivel) {
        this.internos = 0;
        this.internosHembras = 0;
        this.seminternos = 0;
        this.seminternosHembras = 0;
        this.nivel = nivel;
    }

    public RegimenEstudio() {
    }

    public long getIdRegimen() {
        return idRegimen;
    }

    public void setIdRegimen(long idRegimen) {
        this.idRegimen = idRegimen;
    }

    public int getInternos() {
        return internos;
    }

    public void setInternos(int internos) {
        this.internos = internos;
    }

    public int getInternosHembras() {
        return internosHembras;
    }

    public void setInternosHembras(int internosHembras) {
        this.internosHembras = internosHembras;
    }

    public int getSeminternos() {
        return seminternos;
    }

    public void setSeminternos(int seminternos) {
        this.seminternos = seminternos;
    }

    public int getSeminternosHembras() {
        return seminternosHembras;
    }

    public void setSeminternosHembras(int seminternosHembras) {
        this.seminternosHembras = seminternosHembras;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public Caratula getCaratula() {
        return caratula;
    }

    public void setCaratula(Caratula caratula) {
        this.caratula = caratula;
    }
}
