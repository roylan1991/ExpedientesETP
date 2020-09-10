package com.roylan.expedientes_etp.database.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Esta clase contiene la información referente a una provincia.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Entity
@Table(name = "provincias")
public class Provincia {

    @Id
    @GeneratedValue(generator = "seq_provincias")
    @Column(name = "id_provincia")
    private long idProvincia;
    @Column(name = "cod_provincia", unique = true)
    private String codProvincia;
    @Column(name = "nombre_provincia")
    private String nombProvincia;
    @OneToMany(mappedBy = "prov", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Municipio> mcpios;

    public Provincia(String codProvincia, String nombProvincia, List<Municipio> mcpios) {
        this.codProvincia = codProvincia;
        this.nombProvincia = nombProvincia;
        this.mcpios = mcpios;
    }

    public Provincia() {
    }

    public long getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(long idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getCodProvincia() {
        return codProvincia;
    }

    public void setCodProvincia(String codProvincia) {
        this.codProvincia = codProvincia;
    }

    public String getNombProvincia() {
        return nombProvincia;
    }

    public void setNombProvincia(String nombProvincia) {
        this.nombProvincia = nombProvincia;
    }

    public List<Municipio> getMcpios() {
        return mcpios;
    }

    public void setMcpios(List<Municipio> mcpios) {
        this.mcpios = mcpios;
    }
}
