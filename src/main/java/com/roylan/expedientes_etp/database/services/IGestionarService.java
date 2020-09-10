package com.roylan.expedientes_etp.database.services;

import java.util.List;

/**
 * @author Roylan Bressler
 * @see List
 * @since 1.0
 */
public interface IGestionarService<T> {

    /**
     * Esta funcionalidad agrega una nuevo objeto;
     *
     * @param obj Datos del objeto.
     */
    void adicionar(T obj);

    /**
     * Esta funcionalidad devuelve un objeto por su id;
     *
     * @param id Identificador del objeto que será mostrado.
     * @return <code>Object</code> Objeto.
     */
    T obtenerId(long id);

    /**
     * Esta funcionalidad edita un objeto;
     *
     * @param idO Identificador del objeto a editar.
     * @param obj Nuevos datos del objeto.
     */
    void update(long idO, T obj);

    /**
     * Esta funcionalidad elimina un objeto;
     *
     * @param idO Identificador del objeto que será eliminado.
     */
    void eliminar(long idO);

    /**
     * Esta funcionalidad lista todos los objetos;
     *
     * @return <code>List</code> Todos los objetos existentes.
     */
    List<T> listar();
}