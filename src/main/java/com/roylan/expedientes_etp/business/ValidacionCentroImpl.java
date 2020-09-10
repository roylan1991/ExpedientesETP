package com.roylan.expedientes_etp.business;

import com.roylan.expedientes_etp.database.entities.Centro;
import com.roylan.expedientes_etp.database.entities.Municipio;
import com.roylan.expedientes_etp.database.entities.Provincia;
import com.roylan.expedientes_etp.database.entities.Usuario;
import com.roylan.expedientes_etp.database.services.GestionarCentroImpl;
import com.roylan.expedientes_etp.excepciones.RecursoDenegado_Excepcion;
import com.roylan.expedientes_etp.excepciones.RecursoNoEncontrado_Excepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Esta clase valida que se gestionen correctamente todas las operaciones referentes a los centros.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Component
public class ValidacionCentroImpl {

    @Autowired
    private GestionarCentroImpl e_serv;

    public ValidacionCentroImpl() {
    }

    /**
     * Esta funcionalidad registra un nuevo centro luego de ser comprobado que su código no se encuentra en uso y que sus datos restantes estén correctos.
     *
     * @param centro Datos del centro.
     * @throws Exception Si el código del centro está incorrecto.
     * @throws Exception El código del centro no corresponde con el municipio y provincia.
     * @throws Exception Si el centro fue registrado anteriormente.
     */
    public void validarAdicionar(Centro centro) throws Exception {

        validarCodigoCentro(centro);

        if (e_serv.obtenerCodigo(centro.getCodCentro()) != null) {
            throw new Exception("El código de este centro ya está registrado!");
        }

        validarCodigoTelefono(centro);

        e_serv.adicionar(centro);
    }

    /**
     * Esta funcionalidad devuelve un centro luego de comprobar que se encuentra registrado.
     *
     * @param idE                Identificador del centro.
     * @param usuarioAutenticado Usuario autenticado.
     * @return <code>Centro</code> Centro obtenido.
     * @throws RecursoNoEncontrado_Excepcion Si el centro no se encuentra registrado.
     * @throws RecursoDenegado_Excepcion     No tiene acceso a los datos del centro.
     */
    public Centro validarObtenerId(long idE, Usuario usuarioAutenticado) throws RecursoNoEncontrado_Excepcion, RecursoDenegado_Excepcion {
        Centro c = e_serv.obtenerId(idE);

        if (c == null) {
            throw new RecursoNoEncontrado_Excepcion();
        } else {
            if (usuarioAutenticado.getRol().getTipoRol().equals("Supervisor")) {
                if (c.getMcpio().getProv().getIdProvincia() != usuarioAutenticado.getMcpio().getProv().getIdProvincia()) {
                    throw new RecursoDenegado_Excepcion();
                }
            } else if (usuarioAutenticado.getRol().getTipoRol().equals("Usuario")) {
                if (c.getMcpio().getIdMunicipio() != usuarioAutenticado.getMcpio().getIdMunicipio()) {
                    throw new RecursoDenegado_Excepcion();
                }
            }
        }
        return c;
    }

    /**
     * Esta funcionalidad devuelve un centro luego de comprobar que se encuentra registrado.
     *
     * @param codE Código del centro.
     * @return <code>Centro</code> Centro obtenido.
     * @throws RecursoNoEncontrado_Excepcion Si el centro no se encuentra registrado.
     */
    public Centro validarObtenerCodigo(String codE) throws Exception {
        Centro c = e_serv.obtenerCodigo(codE);

        if (c == null) {
            throw new RecursoNoEncontrado_Excepcion();
        }
        return c;
    }

    /**
     * Esta funcionalidad edita un centro luego de ser comprobado que los nuevos datos son correctos.
     *
     * @param idE     Identificador del centro que se editará.
     * @param n_datos Nuevos datos del centro.
     * @throws Exception Si el código del centro está incorrecto.
     * @throws Exception El nuevo código no corresponde con el municipio y provincia.
     * @throws Exception Si el nuevo código fue registrado anteriormente.
     */
    public void validarActualizar(long idE, Centro n_datos) throws Exception {

        validarCodigoCentro(n_datos);

        Centro aux = e_serv.obtenerCodigo(n_datos.getCodCentro());

        if (aux != null) {
            if (aux.getIdCentro() != idE) {
                throw new Exception("El código de este centro ya está registrado!");
            }
        }

        validarCodigoTelefono(n_datos);

        e_serv.update(idE, n_datos);
    }

    /**
     * Esta funcionalidad elimina un centro luego de comprobar que se encuentra registrado.
     *
     * @param idE Identificador del centro que será eliminado.
     * @throws RecursoNoEncontrado_Excepcion Si el centro no se encuentra registrado.
     */
    public void validarEliminar(long idE) {

        e_serv.eliminar(idE);
    }

    /**
     * Esta funcionalidad lista todos los centros que se encuentren registrados.
     *
     * @return <code>List</code> Todos los centros registrados.
     */
    public List<Centro> validarListar() {
        return e_serv.listar();
    }

    /**
     * Esta funcionalidad lista todos los centros que se encuentren registrados en un municipio.
     *
     * @param mcpio Municipio.
     * @return <code>List</code> Todos los centros registrados en el municipio.
     */
    public List<Centro> validarListarSegunMunicipio(Municipio mcpio) {
        return e_serv.listarSegunMunicipio(mcpio);
    }

    /**
     * Esta funcionalidad lista todos los centros que se encuentren registrados en una provincia.
     *
     * @param prov Provincia.
     * @return <code>List</code> Todos los centros registrados en la provincia.
     */
    public List<Centro> validarListarSegunProvincia(Provincia prov) {
        return e_serv.listarSegunProvincia(prov);
    }

    /**
     * Esta funcionalidad valida el código de un centro.
     *
     * @param centro Centro.
     * @throws Exception Si el código del centro no es correcto.
     */
    private void validarCodigoCentro(Centro centro) throws Exception {
        if (!Pattern.matches("[1-9]{1}[0-9]{7}", centro.getCodCentro())) {
            throw new Exception("El código del centro es incorrecto, éste se compone por 8 dígitos!");
        }

        if (!centro.getCodLocalidad().equals(centro.getMcpio().getCodLocalidad())) {
            throw new Exception("El código del centro no se corresponde con su municipio y provincia!");
        }
    }

    /**
     * Esta funcionalidad valida el número de teléfono de un centro.
     *
     * @param centro Centro.
     * @throws Exception Si el número de teléfono no es correcto.
     */
    private void validarCodigoTelefono(Centro centro) throws Exception {
        if (centro.getTelefono() != null) {
            if (centro.getTelefono().length() != 0) {
                if (!Pattern.matches("[0-9]+", centro.getTelefono())) {
                    throw new Exception("El número de teléfono es incorrecto, éste se compone solo por dígitos!");
                }

                if (centro.getTelefono().length() < 6 || centro.getTelefono().length() > 10) {
                    throw new Exception("El número de teléfono es incorrecto, presenta incoherencias en la cantidad de dígitos!");
                }

                if (Pattern.matches("[0]+", centro.getTelefono())) {
                    throw new Exception("El número de teléfono es incorrecto!");
                }
            }
        }
    }

    /**
     * Esta funcionalidad lista todos los centros registrados según el rol del usuario autenticado.
     *
     * @param centros     Centros.
     * @param autenticado Usuario autenticado.
     * @return <code>List</code> Centros registrados.
     */
    public List<Centro> getCentrosSegunRol(ValidacionCentroImpl centros, Usuario autenticado) {

        if (autenticado.getRol().getTipoRol().equals("Administrador")) {
            return centros.validarListar();
        } else if (autenticado.getRol().getTipoRol().equals("Supervisor")) {
            return centros.validarListarSegunProvincia(autenticado.getMcpio().getProv());
        }
        return centros.validarListarSegunMunicipio(autenticado.getMcpio());
    }
}