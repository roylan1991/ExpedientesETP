package com.roylan.expedientes_etp.database.entities;

import javax.persistence.*;

/**
 * Esta clase contiene la información referente a un año de estudio.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Entity
@Table(name = "anno_estudio")
public class AnnoEstudio {

    @Id
    @GeneratedValue(generator = "seq_anno_estudio")
    @Column(name = "id_anno")
    private long idAnno;
    @Column(name = "tipo_anno")
    private String tipoAnno;

    public AnnoEstudio(String tipoAnno) {
        this.tipoAnno = tipoAnno;
    }

    public AnnoEstudio(long idAnno) {
        this.idAnno = idAnno;
        this.tipoAnno = "VACIO";
    }

    public AnnoEstudio() {
    }

    public long getIdAnno() {
        return idAnno;
    }

    public void setIdAnno(long idAnno) {
        this.idAnno = idAnno;
    }

    public String getTipoAnno() {
        return tipoAnno;
    }

    public void setTipoAnno(String tipoAnno) {
        this.tipoAnno = tipoAnno;
    }
}