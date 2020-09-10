package com.roylan.expedientes_etp.database.entities;

import javax.persistence.*;

/**
 * Esta clase contiene la información referente a la especialidad de un centro.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Entity
@Table(name = "especialidades_centros")
public class EspecialidadCentro {

    @Id
    @GeneratedValue(generator = "seq_especialidades_centros")
    @Column(name = "id_especialidad_centro")
    private long idEspecialidad;
    @OneToOne
    @JoinColumn(name = "id_especialidad")
    private Especialidad especialidad;
    @OneToOne
    @JoinColumn(name = "id_curso")
    private Curso curso;
    @OneToOne
    @JoinColumn(name = "id_duracion")
    private Duracion duracion;
    @OneToOne
    @JoinColumn(name = "id_escolaridad")
    private Escolaridad escolaridad;
    @Column(name = "especialidad_captada")
    private boolean fueCaptada;
    @ManyToOne
    @JoinColumn(name = "id_centro")
    private Centro centro;

    public EspecialidadCentro(Especialidad especialidad, Curso curso, Duracion duracion, Escolaridad escolaridad, boolean fueCaptada, Centro centro) {
        this.especialidad = especialidad;
        this.curso = curso;
        this.duracion = duracion;
        this.escolaridad = escolaridad;
        this.fueCaptada = fueCaptada;
        this.centro = centro;
    }

    public EspecialidadCentro(Especialidad especialidad, Curso curso, Duracion duracion, Escolaridad escolaridad, Centro centro) {
        this.especialidad = especialidad;
        this.curso = curso;
        this.duracion = duracion;
        this.escolaridad = escolaridad;
        this.fueCaptada = false;
        this.centro = centro;
    }

    public EspecialidadCentro() {
    }

    public long getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(long idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Duracion getDuracion() {
        return duracion;
    }

    public void setDuracion(Duracion duracion) {
        this.duracion = duracion;
    }

    public Escolaridad getEscolaridad() {
        return escolaridad;
    }

    public void setEscolaridad(Escolaridad escolaridad) {
        this.escolaridad = escolaridad;
    }

    public boolean getFueCaptada() {
        return fueCaptada;
    }

    public void setFueCaptada(boolean fueCaptada) {
        this.fueCaptada = fueCaptada;
    }

    public Centro getCentro() {
        return centro;
    }

    public void setCentro(Centro centro) {
        this.centro = centro;
    }

    public String getDescripcion() {
        return "[" + getEspecialidad().getNivel().getTipoNivel() + "] " + getEspecialidad().getNombEspecialidad() + " [" + getCurso().getTipoCurso() + "] [" + getDuracion().getTipoDuracion() + " Semestres]";
    }

    public String getDescripcionAlCrear() {
        return getEspecialidad().getCodEspecialidad() + " - " + getEspecialidad().getNombEspecialidad() + " [" + getEspecialidad().getNivel().getTipoNivel() + "]";
    }

    @Override
    public String toString() {
        return "EspecialidadCentro{" +
                "idEspecialidad=" + idEspecialidad +
                ", especialidad=" + especialidad.getNombEspecialidad() +
                ", curso=" + curso.getTipoCurso() +
                ", duracion=" + duracion.getTipoDuracion() +
                ", escolaridad=" + escolaridad.getTipoEscolaridad() +
                ", fueCaptada=" + fueCaptada +
                ", centro=" + centro.getIdCentro() +
                '}';
    }
}