package com.roylan.expedientes_etp.database.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Esta clase contiene la información referente a la rama de una familia de especialidades.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Entity
@Table(name = "ramas")
public class Rama {

    @Id
    @GeneratedValue(generator = "seq_ramas")
    @Column(name = "id_rama")
    private long idRama;
    @Column(name = "tipo_rama")
    private String tipoRama;
    @OneToMany(mappedBy = "rama", cascade = CascadeType.ALL)
    private List<Familia> familias;

    public Rama(String tipoRama, List<Familia> familias) {
        this.tipoRama = tipoRama;
        this.familias = familias;
    }

    public Rama() {
    }

    public long getIdRama() {
        return idRama;
    }

    public void setIdRama(long idRama) {
        this.idRama = idRama;
    }

    public String getTipoRama() {
        return tipoRama;
    }

    public void setTipoRama(String tipoRama) {
        this.tipoRama = tipoRama;
    }

    public List<Familia> getFamilias() {
        return familias;
    }

    public void setFamilias(List<Familia> familias) {
        this.familias = familias;
    }
}
