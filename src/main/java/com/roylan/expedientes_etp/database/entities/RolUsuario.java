package com.roylan.expedientes_etp.database.entities;

import javax.persistence.*;

/**
 * Esta clase contiene la información referente a el rol de un usuario.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Entity
@Table(name = "roles_usuarios")
public class RolUsuario {

    @Id
    @GeneratedValue(generator = "seq_roles_usuarios")
    @Column(name = "id_rol")
    private long idRol;
    @Column(name = "tipo_rol", unique = true)
    private String tipoRol;

    public RolUsuario(String tipoRol) {
        this.tipoRol = tipoRol;
    }

    public RolUsuario() {
    }

    public long getIdRol() {
        return idRol;
    }

    public void setIdRol(long idRol) {
        this.idRol = idRol;
    }

    public String getTipoRol() {
        return tipoRol;
    }

    public void setTipoRol(String tipoRol) {
        this.tipoRol = tipoRol;
    }
}
