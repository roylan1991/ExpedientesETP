package com.roylan.expedientes_etp.business;

import java.util.List;

/**
 * @author Roylan Bressler
 * @see List
 * @since 1.0
 */
public interface IValidacion<T> {

	/**
	 * Esta funcionalidad valida y agrega un nuevo objeto;
	 *
	 * @param o Datos del objeto
	 * @throws Exception Si no existe el objeto.
	 */
	void validarAdicionar(T o) throws Exception;

	/**
	 * Esta funcionalidad valida y devuelve un objeto;
	 *
	 * @param idO Identificador del objeto que será mostrado.
	 * @throws Exception Si no existe el objeto.
	 * @return <code>Object</code> Objeto.
	 */
	T validarObtenerId(long idO) throws Exception;

	/**
	 * Esta funcionalidad valida y actualiza un objeto;
	 *
	 * @param idO          Identificador del objeto que será editado.
	 * @param datos_nuevos Nuevos datos del objeto.
	 * @throws Exception Si no existe el objeto.
	 */
	void validarActualizar(long idO, T datos_nuevos) throws Exception;

	/**
	 * Esta funcionalidad valida y elimina un objeto;
	 *
	 * @param idO Identificador del objeto que será eliminado.
	 * @throws Exception Si no existe el objeto.
	 */
	void validarEliminar(long idO) throws Exception;

	/**
	 * Esta funcionalidad lista todos los objetos;
	 *
	 * @return <code>List</code> Todos los objetos existentes.
	 */
	List<T> validarListar();
}
