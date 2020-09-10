package com.roylan.expedientes_etp.database.entities;

import javax.persistence.*;

/**
 * Esta clase contiene la información referente a la escolaridad de ingreso de una especialidad.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Entity
@Table(name = "escolaridades")
public class Escolaridad {

    @Id
    @GeneratedValue(generator = "seq_escolaridades")
    @Column(name = "id_escolaridad")
    private long idEscolaridad;
    @Column(name = "tipo_escolaridad")
    private String tipoEscolaridad;

    public Escolaridad(String tipoEscolaridad) {
        this.tipoEscolaridad = tipoEscolaridad;
    }

    public Escolaridad() {
    }

    public long getIdEscolaridad() {
        return idEscolaridad;
    }

    public void setIdEscolaridad(long idEscolaridad) {
        this.idEscolaridad = idEscolaridad;
    }

    public String getTipoEscolaridad() {
        return tipoEscolaridad;
    }

    public void setTipoEscolaridad(String tipoEscolaridad) {
        this.tipoEscolaridad = tipoEscolaridad;
    }
}