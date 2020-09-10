package com.roylan.expedientes_etp.exportacion;

import com.lowagie.text.DocumentException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Roylan Bressler
 * @since 1.0
 */
public interface IGenerarDocumento {

	/**
	 * Esta funcionalidad configura el estilo que tendrá cada elemento(celda) del
	 * título de la tabla(1ra fila).
	 */
	void crearEncabezadoTabla();

	/**
	 * Esta funcionalidad configura el estilo que tendrá cada elemento(celda) del
	 * cuerpo de la tabla.
	 */
	void crearCuerpoTabla();

	/**
	 * Esta funcionalidad muestra el cuadro de diálogo que permitirá: Abrir o
	 * Descargar el documento.
	 *
	 * @param response Respuesta HTTP.
	 * @throws IOException       Si se ha producido un error en la entrada/salida.
	 * @throws DocumentException Si el documento no se encuentra.
	 */
	void cuadroDialogo(HttpServletResponse response) throws IOException, DocumentException;
}