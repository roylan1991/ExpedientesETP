package com.roylan.expedientes_etp.database.entities;

import javax.persistence.*;

/**
 * Esta clase contiene la información referente a la familia de una especialidad.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Entity
@Table(name = "familias")
public class Familia {

    @Id
    @GeneratedValue(generator = "seq_familias")
    @Column(name = "id_familia")
    private long idFamilia;
    @Column(name = "tipo_familia")
    private String tipoFamilia;
    @ManyToOne
    @JoinColumn(name = "id_rama")
    private Rama rama;

    public Familia(String tipoFamilia, Rama rama) {
        this.tipoFamilia = tipoFamilia;
        this.rama = rama;
    }

    public Familia() {
    }

    public long getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(long idFamilia) {
        this.idFamilia = idFamilia;
    }

    public String getTipoFamilia() {
        return tipoFamilia;
    }

    public void setTipoFamilia(String tipoFamilia) {
        this.tipoFamilia = tipoFamilia;
    }

    public Rama getRama() {
        return rama;
    }

    public void setRama(Rama rama) {
        this.rama = rama;
    }
}
