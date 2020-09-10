package com.roylan.expedientes_etp.database.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Esta clase contiene la información referente a los profesores contratados.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Entity
@Table(name = "profesores_contratados")
@PrimaryKeyJoinColumn(name = "id_tipo_prof")
public class ProfesorContrato extends TipoProfesor {

    @Column(name = "no_prof")
    private int noProfesionales;
    @Column(name = "no_prof_estud")
    private int noProfesionalesEstudiando;

    public ProfesorContrato(int totalProfesores, int noProfesionales, int noProfesionalesEstudiando) {
        super(totalProfesores);
        this.noProfesionales = noProfesionales;
        this.noProfesionalesEstudiando = noProfesionalesEstudiando;
    }

    public ProfesorContrato(int noProfesionales, int noProfesionalesEstudiando) {
        this.noProfesionales = noProfesionales;
        this.noProfesionalesEstudiando = noProfesionalesEstudiando;
    }

    public ProfesorContrato() {
        super();
        this.noProfesionales = 0;
        this.noProfesionalesEstudiando = 0;
    }

    public int getNoProfesionales() {
        return noProfesionales;
    }

    public void setNoProfesionales(int noProfesionales) {
        this.noProfesionales = noProfesionales;
    }

    public int getNoProfesionalesEstudiando() {
        return noProfesionalesEstudiando;
    }

    public void setNoProfesionalesEstudiando(int noProfesionalesEstudiando) {
        this.noProfesionalesEstudiando = noProfesionalesEstudiando;
    }

    @Override
    public String toString() {
        return "ProfesorContrato{" +
                "noProfesionales=" + noProfesionales +
                ", noProfesionalesEstudiando=" + noProfesionalesEstudiando +
                '}';
    }
}