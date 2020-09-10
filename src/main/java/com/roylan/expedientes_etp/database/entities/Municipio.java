package com.roylan.expedientes_etp.database.entities;

import javax.persistence.*;

/**
 * Esta clase contiene la información referente a un municipio.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Entity
@Table(name = "municipios")
public class Municipio {

    @Id
    @GeneratedValue(generator = "seq_municipios")
    @Column(name = "id_municipio")
    private long idMunicipio;
    @Column(name = "cod_municipio")
    private String codMunicipio;
    @Column(name = "nombre_municipio")
    private String nombMunicipio;
    @ManyToOne
    @JoinColumn(name = "id_provincia")
    private Provincia prov;

    public Municipio(String codMunicipio, String nombMunicipio, Provincia prov) {
        this.nombMunicipio = nombMunicipio;
        this.codMunicipio = codMunicipio;
        this.prov = prov;
    }

    public Municipio() {
    }

    public long getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(long idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getNombMunicipio() {
        return nombMunicipio;
    }

    public void setNombMunicipio(String nombMunicipio) {
        this.nombMunicipio = nombMunicipio;
    }

    public Provincia getProv() {
        return prov;
    }

    public void setProv(Provincia prov) {
        this.prov = prov;
    }

    public String getCodMunicipio() {
        return codMunicipio;
    }

    public void setCodMunicipio(String codMunicipio) {
        this.codMunicipio = codMunicipio;
    }

    public String getCodLocalidad() {
        return getProv().getCodProvincia() + getCodMunicipio();
    }
}

