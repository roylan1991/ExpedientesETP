package com.roylan.expedientes_etp.database.entities;

import javax.persistence.*;

/**
 * Esta clase contiene la información referente a el nivel de estudio de una especialidad.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Entity
@Table(name = "niveles")
public class Nivel {

    @Id
    @GeneratedValue(generator = "seq_niveles")
    @Column(name = "id_nivel")
    private long idNivel;
    @Column(name = "tipo_nivel")
    private String tipoNivel;

    public Nivel(String tipoNivel) {
        this.tipoNivel = tipoNivel;
    }

    public Nivel() {
    }

    public long getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(long idNivel) {
        this.idNivel = idNivel;
    }

    public String getTipoNivel() {
        return tipoNivel;
    }

    public void setTipoNivel(String tipoNivel) {
        this.tipoNivel = tipoNivel;
    }
}
