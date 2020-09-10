package com.roylan.expedientes_etp.database.entities;

import javax.persistence.*;

/**
 * Esta clase contiene la información referente al sector de un centro.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Entity
@Table(name = "sectores")
public class Sector {

    @Id
    @GeneratedValue(generator = "seq_sectores")
    @Column(name = "id_sector")
    private long idSector;
    @Column(name = "tipo_sector")
    private String tipoSector;

    public Sector(String tipoSector) {
        this.tipoSector = tipoSector;
    }

    public Sector() {
    }

    public long getIdSector() {
        return idSector;
    }

    public void setIdSector(long idSector) {
        this.idSector = idSector;
    }

    public String getTipoSector() {
        return tipoSector;
    }

    public void setTipoSector(String tipoSector) {
        this.tipoSector = tipoSector;
    }
}
