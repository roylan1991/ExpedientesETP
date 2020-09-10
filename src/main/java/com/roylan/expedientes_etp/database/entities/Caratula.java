package com.roylan.expedientes_etp.database.entities;

import javax.persistence.*;

/**
 * Esta clase contiene la información referente a la carátula de un centro.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Entity
@Table(name = "caratulas")
public class Caratula {

    @Id
    @GeneratedValue(generator = "seq_caratulas")
    @Column(name = "id_caratula")
    private long idCaratula;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_matriculaInicial_TM")
    private MatriculaInicial matriculaInicialTM;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_regimenTM")
    private RegimenEstudio regimenTM;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_matriculaInicial_OC")
    private MatriculaInicial matriculaInicialOC;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_regimenOC")
    private RegimenEstudio regimenOC;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_matriculaInicial_CPT_TM")
    private MatriculaInicial matriculaInicialCPT_TM;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_matriculaFinal_TM")
    private MatriculaFinal matriculaFinalTM;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_matriculaFinal_OC")
    private MatriculaFinal matriculaFinalOC;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_matriculaFinalCPT_TM")
    private MatriculaFinal matriculaFinalCPT_TM;

    public Caratula(MatriculaInicial matriculaInicialTM, RegimenEstudio regimenTM, MatriculaInicial matriculaInicialOC, RegimenEstudio regimenOC, MatriculaInicial matriculaInicialCPT_TM, MatriculaFinal matriculaFinalTM, MatriculaFinal matriculaFinalOC, MatriculaFinal matriculaFinalCPT_TM) {
        this.matriculaInicialTM = matriculaInicialTM;
        this.regimenTM = regimenTM;
        this.matriculaInicialOC = matriculaInicialOC;
        this.regimenOC = regimenOC;
        this.matriculaInicialCPT_TM = matriculaInicialCPT_TM;
        this.matriculaFinalTM = matriculaFinalTM;
        this.matriculaFinalOC = matriculaFinalOC;
        this.matriculaFinalCPT_TM = matriculaFinalCPT_TM;
    }

    public Caratula(MatriculaInicial matriculaInicialTM, MatriculaInicial matriculaInicialOC, MatriculaInicial matriculaInicialCPT_TM, MatriculaFinal matriculaFinalTM, MatriculaFinal matriculaFinalOC, MatriculaFinal matriculaFinalCPT_TM) {
        this.matriculaInicialTM = matriculaInicialTM;
        this.matriculaInicialOC = matriculaInicialOC;
        this.matriculaInicialCPT_TM = matriculaInicialCPT_TM;
        this.matriculaFinalTM = matriculaFinalTM;
        this.matriculaFinalOC = matriculaFinalOC;
        this.matriculaFinalCPT_TM = matriculaFinalCPT_TM;
    }

    public Caratula(RegimenEstudio regimenTM, RegimenEstudio regimenOC) {
        this.regimenTM = regimenTM;
        this.regimenOC = regimenOC;
    }

    public Caratula() {
    }

    public long getIdCaratula() {
        return idCaratula;
    }

    public void setIdCaratula(long idCaratula) {
        this.idCaratula = idCaratula;
    }

    public MatriculaInicial getMatriculaInicialTM() {
        return matriculaInicialTM;
    }

    public void setMatriculaInicialTM(MatriculaInicial matriculaInicialTM) {
        this.matriculaInicialTM = matriculaInicialTM;
    }

    public RegimenEstudio getRegimenTM() {
        return regimenTM;
    }

    public void setRegimenTM(RegimenEstudio regimenTM) {
        this.regimenTM = regimenTM;
    }

    public MatriculaInicial getMatriculaInicialOC() {
        return matriculaInicialOC;
    }

    public void setMatriculaInicialOC(MatriculaInicial matriculaInicialOC) {
        this.matriculaInicialOC = matriculaInicialOC;
    }

    public RegimenEstudio getRegimenOC() {
        return regimenOC;
    }

    public void setRegimenOC(RegimenEstudio regimenOC) {
        this.regimenOC = regimenOC;
    }

    public MatriculaInicial getMatriculaInicialCPT_TM() {
        return matriculaInicialCPT_TM;
    }

    public void setMatriculaInicialCPT_TM(MatriculaInicial matriculaInicialCPT_TM) {
        this.matriculaInicialCPT_TM = matriculaInicialCPT_TM;
    }

    public MatriculaFinal getMatriculaFinalTM() {
        return matriculaFinalTM;
    }

    public void setMatriculaFinalTM(MatriculaFinal matriculaFinalTM) {
        this.matriculaFinalTM = matriculaFinalTM;
    }

    public MatriculaFinal getMatriculaFinalOC() {
        return matriculaFinalOC;
    }

    public void setMatriculaFinalOC(MatriculaFinal matriculaFinalOC) {
        this.matriculaFinalOC = matriculaFinalOC;
    }

    public MatriculaFinal getMatriculaFinalCPT_TM() {
        return matriculaFinalCPT_TM;
    }

    public void setMatriculaFinalCPT_TM(MatriculaFinal matriculaFinalCPT_TM) {
        this.matriculaFinalCPT_TM = matriculaFinalCPT_TM;
    }
}