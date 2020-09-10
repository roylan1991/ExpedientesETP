package com.roylan.expedientes_etp.business;

import com.roylan.expedientes_etp.database.entities.Provincia;
import com.roylan.expedientes_etp.database.entities.Usuario;
import com.roylan.expedientes_etp.database.services.GestionarUsuarioImpl;
import com.roylan.expedientes_etp.excepciones.RecursoNoEncontrado_Excepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Esta clase valida que se gestionen correctamente todas las operaciones referentes a los usuarios.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Component
public class ValidacionUsuarioImpl implements IValidacion<Usuario> {

    @Autowired
    private GestionarUsuarioImpl u_serv;

    public ValidacionUsuarioImpl() {
    }

    /**
     * Esta funcionalidad registra un nuevo usuario luego de ser comprobado que sus datos son correctos.
     *
     * @param u Usuario.
     * @throws Exception Si el nombre de usuario está incorrecto o fue registrado anteriormente.
     */
    public void validarAdicionar(Usuario u) throws Exception {

        if (!Pattern.matches("[a-z]+([0-9]|[a-z])*", u.getNombUsuario())) {
            throw new Exception("El nombre de usuario está conformado incorrectamente. Ejemplos: mined, mined01, m1n3d!");
        }

        if (u_serv.obtenerNomb(u.getNombUsuario()) != null) {
            throw new Exception("Este nombre de usuario ya se encuentra registrado!");
        }

        u_serv.adicionar(u);
    }

    /**
     * Esta funcionalidad devuelve un usuario luego de comprobar que se encuentra registrado.
     *
     * @param idU Identificador del usuario.
     * @return <code>Usuario</code> Usuario obtenido.
     * @throws Exception Si el usuario no se encuentra registrado.
     */
    public Usuario validarObtenerId(long idU) throws RecursoNoEncontrado_Excepcion {

        Usuario u = u_serv.obtenerId(idU);

        if (u == null) {
            throw new RecursoNoEncontrado_Excepcion();
        }
        return u;
    }

    /**
     * Esta funcionalidad devuelve un usuario luego de comprobar que se encuentra registrado.
     *
     * @param nombU Nombre del usuario.
     * @return <code>Usuario</code> Usuario obtenido.
     * @throws Exception Si el usuario no se encuentra registrado.
     */
    public Usuario validarObtenerNomb(String nombU) throws Exception {

        Usuario u = u_serv.obtenerNomb(nombU);

        if (u == null) {
            throw new Exception("Este usuario no se encuentra registrado!");
        }
        return u;
    }

    /**
     * Esta funcionalidad edita un usuario.
     *
     * @param idU     Identificador del usuario que se editará.
     * @param n_datos Nuevos datos del usuario.
     */
    public void validarActualizar(long idU, Usuario n_datos) {
        u_serv.update(idU, n_datos);
    }

    /**
     * Esta funcionalidad elimina un usuario luego de comprobar que se encuentra registrado.
     *
     * @param idU Identificador del usuario que será eliminado.
     * @throws RecursoNoEncontrado_Excepcion Si el usuario no se encuentra registrado.
     */
    public void validarEliminar(long idU) throws RecursoNoEncontrado_Excepcion {

        if (u_serv.obtenerId(idU) == null) {
            throw new RecursoNoEncontrado_Excepcion();
        }

        u_serv.eliminar(idU);
    }

    /**
     * Esta funcionalidad lista todos los usuarios que se encuentren registrados.
     *
     * @return <code>List</code> Todos los usuarios registrados.
     */
    public List<Usuario> validarListar() {
        return u_serv.listar();
    }

    /**
     * Esta funcionalidad lista todos los usuarios que se encuentren registrados en una provincia y que no presentan un determinado rol.
     *
     * @param rol  Tipo de rol que no presentarán.
     * @param prov Provincia.
     * @return <code>List</code> Todos los usuarios registrados en la provincia.
     */
    public List<Usuario> validarListarSegunProvincia(String rol, Provincia prov) {
        return u_serv.listarSegunProvincia(rol, prov);
    }
}
