package com.roylan.expedientes_etp.database.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Esta clase gestiona las operaciones referentes a los indicadores.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Service
@Transactional
public class GestionarIndicador {

    @Autowired
    private GestionarEspecialidadActualImpl ea;

    @Autowired
    private GestionarEspecialidadAnteriorImpl eant;

    public GestionarIndicador() {
    }

    /**
     * Esta funcionalidad permite obtener el porciento de retención para un año de estudio según el nivel.
     *
     * @param anno  Año de estudio
     * @param nivel Identificador del Nivel.
     * @param curso Identificador del Curso.
     * @return <code>float</code> Porciento de retención.
     */
    public float porcientoRetencionAnno(long anno, long nivel, long curso) {

        int matInicial = eant.matriculasInicialesSegunAnnoNivelCurso(anno, nivel, curso);

        int matActual_Aprobados;

        if ((nivel == 1 && anno < 4) || (nivel == 2 && anno < 2)) {
            matActual_Aprobados = ea.matriculasActualesSegunAnnoNivelCurso(anno + 1, nivel, curso);
        } else {
            matActual_Aprobados = eant.aprobadosSegunAnnoNivelCurso(anno, nivel, curso);
        }

        return 100 - porcientoBajas(matInicial, matActual_Aprobados);
    }

    /**
     * Esta funcionalidad permite obtener el porciento de bajas.
     *
     * @param matInicial          Matrícula Inicial
     * @param matActual_Aprobados Matrícula actual / Aprobados
     * @return <code>float</code> Porciento de bajas.
     */
    public float porcientoBajas(int matInicial, int matActual_Aprobados) {
        float bajas;

        if (matActual_Aprobados > matInicial) {
            bajas = 0;
        } else {
            bajas = matInicial - matActual_Aprobados;
        }

        return bajas / matInicial * 100;
    }
}