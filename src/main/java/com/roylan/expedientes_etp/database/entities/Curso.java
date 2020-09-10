package com.roylan.expedientes_etp.database.entities;

import javax.persistence.*;

/**
 * Esta clase contiene la información referente a un curso.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(generator = "seq_cursos")
    @Column(name = "id_curso")
    private long idCurso;
    @Column(name = "tipo_curso")
    private String tipoCurso;

    public Curso(String tipoCurso) {
        this.tipoCurso = tipoCurso;
    }

    public Curso() {
    }

    public long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(long idCurso) {
        this.idCurso = idCurso;
    }

    public String getTipoCurso() {
        return tipoCurso;
    }

    public void setTipoCurso(String tipoCurso) {
        this.tipoCurso = tipoCurso;
    }
}