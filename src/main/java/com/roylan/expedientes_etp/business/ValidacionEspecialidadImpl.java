package com.roylan.expedientes_etp.business;

import com.roylan.expedientes_etp.database.entities.Especialidad;
import com.roylan.expedientes_etp.database.services.GestionarEspecialidadImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Esta clase valida que se gestionen correctamente todas las operaciones referentes a las especialidades.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Component
public class ValidacionEspecialidadImpl implements IValidacion<Especialidad> {

    @Autowired
    private GestionarEspecialidadImpl e_serv;

    public ValidacionEspecialidadImpl() {
    }

    /**
     * Esta funcionalidad registra una especialidad luego de ser comprobado que la misma no se encuentra registrada y que sus datos estén correctos.
     *
     * @param e Datos de la especialidad.
     * @throws Exception Si la especialidad ya ha sido registrada.
     */
    public void validarAdicionar(Especialidad e) throws Exception {

        validarCodigoEspecialidad(e);

        if (e_serv.obtenerCodigo(e.getCodEspecialidad()) != null) {
            throw new Exception("Esta especialidad ya se encuentra registrada!");
        }

        e_serv.adicionar(e);
    }

    /**
     * Esta funcionalidad devuelve una especialidad luego de comprobar que se encuentra registrada.
     *
     * @param idE Identificador de la especialidad.
     * @return <code>Especialidad</code> Especialidad obtenida.
     * @throws Exception Si la especialidad no se encuentra registrada.
     */
    public Especialidad validarObtenerId(long idE) throws Exception {

        Especialidad e = e_serv.obtenerId(idE);

        if (e == null) {
            throw new Exception("Esta especialidad no se encuentra registrada!");
        }

        return e;
    }

    /**
     * Esta funcionalidad edita una especialidad luego de ser comprobado que los nuevos datos son correctos.
     *
     * @param idE     Identificador de la especialidad que se editará.
     * @param n_datos Nuevos datos de la especialidad.
     * @throws Exception Si el código de la especialidad está incorrecto.
     * @throws Exception Si el código de la especialidad no pertenece al nivel seleccionado.
     * @throws Exception Si los nuevos datos de la especialidad ya están registrados en un tipo de curso.
     */
    public void validarActualizar(long idE, Especialidad n_datos) throws Exception {

        validarCodigoEspecialidad(n_datos);
        Especialidad aux = e_serv.obtenerCodigo(n_datos.getCodEspecialidad());

        if (aux != null) {
            if (aux.getIdEspecialidad() != idE) {
                throw new Exception("El código de esta especialidad ya se encuentra registrado!");
            }
        }

        e_serv.update(idE, n_datos);
    }

    /**
     * Esta funcionalidad elimina un especialidad luego de comprobar que se encuentra registrada.
     *
     * @param idE Identificador de la especialidad que será eliminada.
     * @throws Exception Si la especialidad no se encuentra registrada.
     */
    public void validarEliminar(long idE) throws Exception {

        if (e_serv.obtenerId(idE) == null) {
            throw new Exception("Esta especialidad no se encuentra registrada!");
        }
        e_serv.eliminar(idE);
    }

    /**
     * Esta funcionalidad lista todas las especialidades que se encuentren registradas.
     *
     * @return <code>List</code> Todas las especialidades registradas.
     */
    public List<Especialidad> validarListar() {
        return e_serv.listar();
    }

    /**
     * Esta funcionalidad valida el código de una especialidad.
     *
     * @param datos Especialidad.
     * @throws Exception Si el código de la especialidad no es correcto.
     */
    private void validarCodigoEspecialidad(Especialidad datos) throws Exception {
        if (!Pattern.matches("[3|6]{1}[0-9]{6}", datos.getCodEspecialidad())) {
            throw new Exception("El código de la especialidad es incorrecto, éste comienza con 3 [T.M] ó 6 [O.C], además se compone por 7 dígitos!");
        }

        if (((datos.getCodEspecialidad().charAt(0) == '3') && (!datos.getNivel().getTipoNivel().equals("T.M"))) || ((datos.getCodEspecialidad().charAt(0) == '6') && (!datos.getNivel().getTipoNivel().equals("O.C")))) {
            throw new Exception("El código de la especialidad no se corresponde con el tipo de nivel!");
        }
    }
}
