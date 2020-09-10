package com.roylan.expedientes_etp.database.entities;

import javax.persistence.*;

@Entity
@Table(name = "personal")
public class Personal {

    @Id
    @GeneratedValue(generator = "seq_personal")
    @Column(name = "id_personal")
    private long idPersonal;
    @Column(name = "total")
    private int total;
    @Column(name = "hembras")
    private int hembras;

    public Personal(int total, int hembras) {
        this.total = total;
        this.hembras = hembras;
    }

    public Personal() {
        this.total = 0;
        this.hembras = 0;
    }

    public long getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(long idPersonal) {
        this.idPersonal = idPersonal;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getHembras() {
        return hembras;
    }

    public void setHembras(int hembras) {
        this.hembras = hembras;
    }

    @Override
    public String toString() {
        return "Personal{" +
                "idPersonal=" + idPersonal +
                ", total=" + total +
                ", hembras=" + hembras +
                '}';
    }
}