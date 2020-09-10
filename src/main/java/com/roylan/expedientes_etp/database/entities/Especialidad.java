package com.roylan.expedientes_etp.database.entities;

import javax.persistence.*;

/**
 * Esta clase contiene la información referente a una especialidad.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Entity
@Table(name = "especialidades")
public class Especialidad {

    @Id
    @GeneratedValue(generator = "seq_especialidades")
    @Column(name = "id_especialidad")
    private long idEspecialidad;
    @Column(name = "cod_especialidad", unique = true)
    private String codEspecialidad;
    @Column(name = "nomb_especialidad")
    private String nombEspecialidad;
    @OneToOne
    @JoinColumn(name = "id_familia")
    private Familia familia;
    @OneToOne
    @JoinColumn(name = "id_nivel")
    private Nivel nivel;

    public Especialidad(String codEspecialidad, String nombEspecialidad, Familia familia, Nivel nivel) {
        this.codEspecialidad = codEspecialidad;
        this.nombEspecialidad = nombEspecialidad;
        this.familia = familia;
        this.nivel = nivel;
    }

    public Especialidad() {
    }

    public long getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(long idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getCodEspecialidad() {
        return codEspecialidad;
    }

    public void setCodEspecialidad(String codEspecialidad) {
        this.codEspecialidad = codEspecialidad;
    }

    public String getNombEspecialidad() {
        return nombEspecialidad;
    }

    public void setNombEspecialidad(String nombEspecialidad) {
        this.nombEspecialidad = nombEspecialidad;
    }

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public String getDescripcion() {
        return getCodEspecialidad() + " - " + getNombEspecialidad() + " [" + getNivel().getTipoNivel() + "]";
    }

    @Override
    public String toString() {
        return "Especialidad{" +
                "idEspecialidad=" + idEspecialidad +
                ", codEspecialidad='" + codEspecialidad + '\'' +
                ", nombEspecialidad='" + nombEspecialidad + '\'' +
                ", familia=" + familia.getTipoFamilia() +
                ", nivel=" + nivel.getTipoNivel() +
                '}';
    }
}