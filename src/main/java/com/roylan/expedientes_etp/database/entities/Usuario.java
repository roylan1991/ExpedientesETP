package com.roylan.expedientes_etp.database.entities;

import javax.persistence.*;

/**
 * Esta clase contiene la información referente a un usuario.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(generator = "seq_usuarios")
    @Column(name = "id_usuario")
    private long idUsuario;
    @Column(name = "nombre_usuario", unique = true)
    private String nombUsuario;
    @Column(name = "nombre_descriptivo")
    private String nombDescriptivo;
    @Column(name = "password")
    private String password;
    @Column(name = "estado")
    private boolean estado;
    @OneToOne
    @JoinColumn(name = "id_municipio")
    private Municipio mcpio;
    @OneToOne
    @JoinColumn(name = "id_rol")
    private RolUsuario rol;

    public Usuario(String nombUsuario, String nombDescriptivo, String password, boolean estado, Municipio mcpio, RolUsuario rol) {
        this.nombUsuario = nombUsuario;
        this.nombDescriptivo = nombDescriptivo;
        this.password = password;
        this.estado = estado;
        this.mcpio = mcpio;
        this.rol = rol;
    }

    public Usuario() {
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombUsuario() {
        return nombUsuario;
    }

    public void setNombUsuario(String nombUsuario) {
        this.nombUsuario = nombUsuario;
    }

    public String getNombDescriptivo() {
        return nombDescriptivo;
    }

    public void setNombDescriptivo(String nombDescriptivo) {
        this.nombDescriptivo = nombDescriptivo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Municipio getMcpio() {
        return mcpio;
    }

    public void setMcpio(Municipio mcpio) {
        this.mcpio = mcpio;
    }

    public RolUsuario getRol() {
        return rol;
    }

    public void setRol(RolUsuario rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombUsuario='" + nombUsuario + '\'' +
                ", nombDescriptivo='" + nombDescriptivo + '\'' +
                ", estado=" + estado +
                ", mcpio=" + mcpio.getNombMunicipio() +
                ", rol=" + rol.getTipoRol() +
                '}';
    }
}