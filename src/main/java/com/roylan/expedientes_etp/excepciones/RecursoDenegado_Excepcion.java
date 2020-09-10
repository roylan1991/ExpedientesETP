package com.roylan.expedientes_etp.excepciones;

public class RecursoDenegado_Excepcion extends Exception {

    public RecursoDenegado_Excepcion() {
        super("No se permite el acceso a este recurso");
    }
}