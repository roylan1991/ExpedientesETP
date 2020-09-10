package com.roylan.expedientes_etp.database.entities;

import javax.persistence.*;

/**
 * Esta clase contiene la información referente a la duración de una especialidad.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Entity
@Table(name = "duraciones")
public class Duracion {

    @Id
    @GeneratedValue(generator = "seq_duraciones")
    @Column(name = "id_duracion")
    private long idDuracion;
    @Column(name = "tipo_duracion")
    private int tipoDuracion;

    public Duracion(int tipoDuracion) {
        this.tipoDuracion = tipoDuracion;
    }

    public Duracion() {
    }

    public long getIdDuracion() {
        return idDuracion;
    }

    public void setIdDuracion(long idDuracion) {
        this.idDuracion = idDuracion;
    }

    public int getTipoDuracion() {
        return tipoDuracion;
    }

    public void setTipoDuracion(int tipoDuracion) {
        this.tipoDuracion = tipoDuracion;
    }
}
