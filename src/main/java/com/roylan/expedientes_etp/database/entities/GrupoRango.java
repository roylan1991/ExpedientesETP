package com.roylan.expedientes_etp.database.entities;

import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

/**
 * Esta clase contiene la información referente a un grupo por rango.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Component
public class GrupoRango {

    private int[] grupos, matrAtendida;
    private float[] alumnosGrupo, porcMatr;
    private DecimalFormat df = new DecimalFormat("0.0");

    public GrupoRango(int[] grupos, int[] matrAtendida, float[] alumnosGrupo, float[] porcMatr) {
        this.grupos = grupos;
        this.matrAtendida = matrAtendida;
        this.alumnosGrupo = alumnosGrupo;
        this.porcMatr = porcMatr;
    }

    public GrupoRango() {
    }

    public GrupoRango grupoRango(int[] grupos, int[] matri) {
        this.grupos = grupos;
        this.matrAtendida = matri;

        this.alumnosGrupo = new float[6];
        rellenarAlumnosGrupo();

        this.porcMatr = new float[6];
        rellenarPorcMatricula();

        return new GrupoRango(grupos, matrAtendida, alumnosGrupo, porcMatr);
    }

    private void rellenarAlumnosGrupo() {
        for (int i = 0; i < 6; i++) {
            if (grupos[i] != 0) {
                alumnosGrupo[i] = Float.parseFloat(df.format((float) matrAtendida[i] / (float) grupos[i]).replace(',', '.'));
            } else {
                alumnosGrupo[i] = 0;
            }
        }
    }

    private void rellenarPorcMatricula() {

        for (int i = 1; i < 6; i++) {
            if (matrAtendida[0] != 0) {
                porcMatr[i] = Float.parseFloat(df.format((float) matrAtendida[i] / (float) matrAtendida[0] * 100).replace(',', '.'));
            } else {
                porcMatr[i] = 0;
            }
        }
        porcMatr[0] = 100;
    }

    public int[] getGrupos() {
        return grupos;
    }

    public int[] getMatrAtendida() {
        return matrAtendida;
    }

    public float[] getAlumnosGrupo() {
        return alumnosGrupo;
    }

    public float[] getPorcMatr() {
        return porcMatr;
    }
}