package com.roylan.expedientes_etp.business;

import com.roylan.expedientes_etp.database.entities.Usuario;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.regex.Pattern;

/**
 * Esta clase valida algunas funciones de importancia en el sistema.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Component
public class Validaciones {

	public static boolean ValoresIncorrectos = false;

	/**
	 * Esta funcionalidad comprueba si dos parejas de contraseñas son idénticas.
	 *
	 * @param passw1 Contraseña 1.
	 * @param passw2 Contraseña 2.
	 * @param opcion Opción de verificación.
	 * @return <code>true</code> Si ambas son iguales.
	 * @throws Exception Si no lo son.
	 */
	public boolean passwordIgual(String passw1, String passw2, int opcion) throws Exception {

		if (!passw1.equals(passw2)) {
			if (opcion == 1) {
				throw new Exception("No existen coincidencias entre ambas contraseñas!");
			} else {
				throw new Exception("La contraseña de este usuario es incorrecta!");
			}
		}
		return true;
	}

	/**
	 * Esta funcionalidad comprueba si un texto contiene un número positivo válido.
	 *
	 * @param num Número.
	 * @return El mismo número si es válido.
	 */
	public int numeroValido(String num) {

		if (!Pattern.matches("[0-9]+", num)) {
			ValoresIncorrectos = true;
			return 0;
		}
		return Integer.parseInt(num);
	}

	/**
	 * Esta funcionalidad oculta algunas opciones en las vistas, a las personas que no tienen un rol específico.
	 *
	 * @param u   Usuario autenticado.
	 * @param rol Rol del usuario que no se le ocultarán las opciones.
	 * @param m   Datos del modelo que se enviará a la vista.
	 */
	public void ocultarOpciones(Usuario u, String rol, Model m) {
		if (!u.getRol().getTipoRol().contains(rol)) {
			m.addAttribute("ocultar", true);
		} else {
			m.addAttribute("ocultar", false);
		}
	}
}
