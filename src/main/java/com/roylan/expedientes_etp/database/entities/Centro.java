package com.roylan.expedientes_etp.database.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Esta clase contiene la información referente a un centro.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Entity
@Table(name = "centros")
public class Centro {

    @Id
    @GeneratedValue(generator = "seq_centros")
    @Column(name = "id_centro")
    private long idCentro;
    @Column(name = "cod_centro", unique = true)
    private String codCentro;
    @Column(name = "nombre_centro")
    private String nombCentro;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "progreso")
    private int progreso;
    @OneToOne
    @JoinColumn(name = "id_sector")
    private Sector sector;
    @OneToOne
    @JoinColumn(name = "id_municipio")
    private Municipio mcpio;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_caratula")
    private Caratula caratula;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_rango")
    private RangoEdad rangosEdades;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_personal")
    private ComposicionPersonal personal;
    @OneToMany(mappedBy = "centro", cascade = CascadeType.ALL)
    private List<EspecialidadCentro> especialidades;
    @OneToMany(mappedBy = "centro", cascade = CascadeType.ALL)
    private List<PlanillaDatos> planillas;

    public Centro(String codCentro, String nombCentro, String direccion, String telefono, Sector sector, Municipio mcpio, Caratula caratula, RangoEdad rangosEdades, ComposicionPersonal personal) {
        this.codCentro = codCentro;
        this.nombCentro = nombCentro;
        this.direccion = direccion;
        this.telefono = telefono;
        this.progreso = 0;
        this.sector = sector;
        this.mcpio = mcpio;
        this.caratula = caratula;
        this.rangosEdades = rangosEdades;
        this.personal = personal;
    }

    public Centro(String codCentro, String nombCentro, String direccion, String telefono, Sector sector, Municipio mcpio) {
        this.codCentro = codCentro;
        this.nombCentro = nombCentro;
        this.direccion = direccion;
        this.telefono = telefono;
        this.progreso = 0;
        this.sector = sector;
        this.mcpio = mcpio;
    }

    public Centro() {
    }

    public long getIdCentro() {
        return idCentro;
    }

    public void setIdCentro(long idCentro) {
        this.idCentro = idCentro;
    }

    public String getCodCentro() {
        return codCentro;
    }

    public void setCodCentro(String codCentro) {
        this.codCentro = codCentro;
    }

    public String getNombCentro() {
        return nombCentro;
    }

    public void setNombCentro(String nombCentro) {
        this.nombCentro = nombCentro;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Municipio getMcpio() {
        return mcpio;
    }

    public void setMcpio(Municipio mcpio) {
        this.mcpio = mcpio;
    }

    public Caratula getCaratula() {
        return caratula;
    }

    public void setCaratula(Caratula caratula) {
        this.caratula = caratula;
    }

    public RangoEdad getRangosEdades() {
        return rangosEdades;
    }

    public void setRangosEdades(RangoEdad rangosEdades) {
        this.rangosEdades = rangosEdades;
    }

    public ComposicionPersonal getComposicionPersonal() {
        return personal;
    }

    public void setComposicionPersonal(ComposicionPersonal personal) {
        this.personal = personal;
    }

    public List<EspecialidadCentro> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<EspecialidadCentro> especialidades) {
        this.especialidades = especialidades;
    }

    public List<PlanillaDatos> getPlanillas() {
        return planillas;
    }

    public void setPlanillas(List<PlanillaDatos> planillas) {
        this.planillas = planillas;
    }

    public ComposicionPersonal getPersonal() {
        return personal;
    }

    public void setPersonal(ComposicionPersonal personal) {
        this.personal = personal;
    }

    public int getProgreso() {
        return progreso;
    }

    public void setProgreso(int progreso) {
        this.progreso = progreso;
    }

    public String getCodLocalidad() {
        return getCodCentro().substring(0, 4);
    }

    public String getLocalidad() {
        return getMcpio().getNombMunicipio() + " [" + getMcpio().getProv().getNombProvincia() + "]";
    }

    @Override
    public String toString() {
        return "Centro{" +
                "idCentro=" + idCentro +
                ", codCentro='" + codCentro + '\'' +
                ", nombCentro='" + nombCentro + '\'' +
                ", direccion='" + direccion + '\'' +
                ", sector=" + sector.getTipoSector() +
                ", mcpio=" + mcpio.getNombMunicipio() +
                '}';
    }
}