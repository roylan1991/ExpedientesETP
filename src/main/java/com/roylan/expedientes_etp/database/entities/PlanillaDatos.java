package com.roylan.expedientes_etp.database.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Esta clase contiene la información referente a las planillas de datos de un centro.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Entity
@Table(name = "planillas_datos")
public class PlanillaDatos {

    @Id
    @GeneratedValue(generator = "seq_planillas_datos")
    @Column(name = "id_planilla")
    private long idPlanilla;
    @ManyToOne
    @JoinColumn(name = "id_centro")
    private Centro centro;
    @OneToOne
    @JoinColumn(name = "id_especialidad_centro")
    private EspecialidadCentro especialidadCentro;
    @OneToMany(mappedBy = "planilla", cascade = CascadeType.ALL)
    private List<EspecialidadAnterior> especialidadesAnteriores;
    @OneToMany(mappedBy = "planilla", cascade = CascadeType.ALL)
    private List<EspecialidadActual> especialidadesActuales;
    @Column(name = "procd_graduados")
    private int procdGraduados;
    @Column(name = "hemb_procd_graduados")
    private int hembProcdGraduados;
    @Column(name = "procd_posibles_graduados")
    private int procdPosiblesGraduados;
    @Column(name = "hemb_procd_posibles_graduados")
    private int hembProcdPosiblesGraduados;
    @Column(name = "procd_nuevos_ingresos")
    private int procdNuevosIngresos;
    @Column(name = "hemb_procd_nuevos_ingresos")
    private int hembProcdNuevosIngresos;

    public PlanillaDatos(Centro centro, EspecialidadCentro especialidadCentro, List<EspecialidadAnterior> especialidadesAnteriores, List<EspecialidadActual> especialidadesActuales, int procdGraduados, int hembProcdGraduados, int procdPosiblesGraduados, int hembProcdPosiblesGraduados, int procdNuevosIngresos, int hembProcdNuevosIngresos) {
        this.centro = centro;
        this.especialidadCentro = especialidadCentro;
        this.especialidadesAnteriores = especialidadesAnteriores;
        this.especialidadesActuales = especialidadesActuales;
        this.procdGraduados = procdGraduados;
        this.hembProcdGraduados = hembProcdGraduados;
        this.procdPosiblesGraduados = procdPosiblesGraduados;
        this.hembProcdPosiblesGraduados = hembProcdPosiblesGraduados;
        this.procdNuevosIngresos = procdNuevosIngresos;
        this.hembProcdNuevosIngresos = hembProcdNuevosIngresos;
    }

    public PlanillaDatos(List<EspecialidadAnterior> especialidadesAnteriores, List<EspecialidadActual> especialidadesActuales, int procdGraduados, int hembProcdGraduados, int procdPosiblesGraduados, int hembProcdPosiblesGraduados, int procdNuevosIngresos, int hembProcdNuevosIngresos) {
        this.especialidadesAnteriores = especialidadesAnteriores;
        this.especialidadesActuales = especialidadesActuales;
        this.procdGraduados = procdGraduados;
        this.hembProcdGraduados = hembProcdGraduados;
        this.procdPosiblesGraduados = procdPosiblesGraduados;
        this.hembProcdPosiblesGraduados = hembProcdPosiblesGraduados;
        this.procdNuevosIngresos = procdNuevosIngresos;
        this.hembProcdNuevosIngresos = hembProcdNuevosIngresos;
    }

    public PlanillaDatos() {
    }

    public long getIdPlanilla() {
        return idPlanilla;
    }

    public void setIdPlanilla(long idPlanilla) {
        this.idPlanilla = idPlanilla;
    }

    public Centro getCentro() {
        return centro;
    }

    public void setCentro(Centro centro) {
        this.centro = centro;
    }

    public EspecialidadCentro getEspecialidadCentro() {
        return especialidadCentro;
    }

    public void setEspecialidadCentro(EspecialidadCentro especialidadCentro) {
        this.especialidadCentro = especialidadCentro;
    }

    public List<EspecialidadAnterior> getEspecialidadesAnteriores() {
        return especialidadesAnteriores;
    }

    public void setEspecialidadesAnteriores(List<EspecialidadAnterior> especialidadesAnteriores) {
        this.especialidadesAnteriores = especialidadesAnteriores;
    }

    public List<EspecialidadActual> getEspecialidadesActuales() {
        return especialidadesActuales;
    }

    public void setEspecialidadesActuales(List<EspecialidadActual> especialidadesActuales) {
        this.especialidadesActuales = especialidadesActuales;
    }

    public int getProcdGraduados() {
        return procdGraduados;
    }

    public void setProcdGraduados(int procdGraduados) {
        this.procdGraduados = procdGraduados;
    }

    public int getHembProcdGraduados() {
        return hembProcdGraduados;
    }

    public void setHembProcdGraduados(int hembProcdGraduados) {
        this.hembProcdGraduados = hembProcdGraduados;
    }

    public int getProcdPosiblesGraduados() {
        return procdPosiblesGraduados;
    }

    public void setProcdPosiblesGraduados(int procdPosiblesGraduados) {
        this.procdPosiblesGraduados = procdPosiblesGraduados;
    }

    public int getHembProcdPosiblesGraduados() {
        return hembProcdPosiblesGraduados;
    }

    public void setHembProcdPosiblesGraduados(int hembProcdPosiblesGraduados) {
        this.hembProcdPosiblesGraduados = hembProcdPosiblesGraduados;
    }

    public int getProcdNuevosIngresos() {
        return procdNuevosIngresos;
    }

    public void setProcdNuevosIngresos(int procdNuevosIngresos) {
        this.procdNuevosIngresos = procdNuevosIngresos;
    }

    public int getHembProcdNuevosIngresos() {
        return hembProcdNuevosIngresos;
    }

    public void setHembProcdNuevosIngresos(int hembProcdNuevosIngresos) {
        this.hembProcdNuevosIngresos = hembProcdNuevosIngresos;
    }
}
