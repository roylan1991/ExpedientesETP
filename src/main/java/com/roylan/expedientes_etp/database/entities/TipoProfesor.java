package com.roylan.expedientes_etp.database.entities;

import javax.persistence.*;

/**
 * Esta clase contiene la información referente al tipo de profesor.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Entity
@Table(name = "tipos_profesores")
@Inheritance(strategy = InheritanceType.JOINED)
public class TipoProfesor {

    @Id
    @GeneratedValue(generator = "seq_tipo_prof")
    @Column(name = "id_tipo_prof")
    private long idTipoProfesor;
    @Column(name = "total_prof")
    private int totalProfesores;

    public TipoProfesor(int totalProfesores) {
        this.totalProfesores = totalProfesores;
    }

    public TipoProfesor() {
        this.totalProfesores = 0;
    }

    public long getIdTipoProfesor() {
        return idTipoProfesor;
    }

    public void setIdTipoProfesor(long idTipoProfesor) {
        this.idTipoProfesor = idTipoProfesor;
    }

    public int getTotalProfesores() {
        return totalProfesores;
    }

    public void setTotalProfesores(int totalProfesores) {
        this.totalProfesores = totalProfesores;
    }
}