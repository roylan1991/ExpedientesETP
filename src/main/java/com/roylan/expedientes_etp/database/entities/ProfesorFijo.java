package com.roylan.expedientes_etp.database.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Esta clase contiene la información referente a los profesores fijos.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Entity
@Table(name = "profesores_fijos")
@PrimaryKeyJoinColumn(name = "id_tipo_prof")
public class ProfesorFijo extends TipoProfesor {

    @Column(name = "no_tit")
    private int noTitulados;
    @Column(name = "no_tit_estud")
    private int noTituladosEstudiando;

    public ProfesorFijo(int totalProfesores, int noTitulados, int noTituladosEstudiando) {
        super(totalProfesores);
        this.noTitulados = noTitulados;
        this.noTituladosEstudiando = noTituladosEstudiando;
    }

    public ProfesorFijo(int noTitulados, int noTituladosEstudiando) {
        this.noTitulados = noTitulados;
        this.noTituladosEstudiando = noTituladosEstudiando;
    }

    public ProfesorFijo() {
        super();
        this.noTitulados = 0;
        this.noTituladosEstudiando = 0;
    }

    public int getNoTitulados() {
        return noTitulados;
    }

    public void setNoTitulados(int noTitulados) {
        this.noTitulados = noTitulados;
    }

    public int getNoTituladosEstudiando() {
        return noTituladosEstudiando;
    }

    public void setNoTituladosEstudiando(int noTituladosEstudiando) {
        this.noTituladosEstudiando = noTituladosEstudiando;
    }
}