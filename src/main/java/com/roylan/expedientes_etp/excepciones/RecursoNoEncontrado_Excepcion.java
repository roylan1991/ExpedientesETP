package com.roylan.expedientes_etp.excepciones;

public class RecursoNoEncontrado_Excepcion extends Exception {

    public RecursoNoEncontrado_Excepcion() {
        super("Este recurso no se encuentra");
    }
}