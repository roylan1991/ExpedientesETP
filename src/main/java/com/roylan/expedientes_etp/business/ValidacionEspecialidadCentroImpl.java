package com.roylan.expedientes_etp.business;

import com.roylan.expedientes_etp.database.entities.Centro;
import com.roylan.expedientes_etp.database.entities.Especialidad;
import com.roylan.expedientes_etp.database.entities.EspecialidadCentro;
import com.roylan.expedientes_etp.database.services.GestionarEspecialidadCentroImpl;
import com.roylan.expedientes_etp.excepciones.RecursoNoEncontrado_Excepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Esta clase valida que se gestionen correctamente todas las operaciones referentes a las especialidades de un centro.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Component
public class ValidacionEspecialidadCentroImpl implements IValidacion<EspecialidadCentro> {

    @Autowired
    private GestionarEspecialidadCentroImpl e_serv;

    public ValidacionEspecialidadCentroImpl() {
    }

    /**
     * Esta funcionalidad registra una especialidad a un centro luego de ser comprobado que la misma no se agregó anteriormente y que las duraciones se corresponden con el nivel de estudio.
     *
     * @param e Datos de la especialidad.
     * @throws Exception Si la especialidad fue registrada anteriormente.
     */
    public void validarAdicionar(EspecialidadCentro e) throws Exception {

        EspecialidadCentro espC = e_serv.obtenerEspecialidadCentroSegunDuracionEscolaridad(e.getCentro(), e.getEspecialidad().getCodEspecialidad(), e.getDuracion(), e.getEscolaridad());

        if (espC != null) {
            throw new Exception("Esta especialidad ya ha sido registrada con estas características en este centro!");
        }

        validarEspecialidadCentro(e);
        e_serv.adicionar(e);
    }

    /**
     * Esta funcionalidad devuelve la especialidad de un centro luego de comprobar que se encuentra registrada.
     *
     * @param idE Identificador de la especialidad.
     * @return <code>EspecialidadCentro</code> Especialidad obtenida.
     * @throws RecursoNoEncontrado_Excepcion Si la especialidad no se encuentra registrada.
     */
    public EspecialidadCentro validarObtenerId(long idE) throws RecursoNoEncontrado_Excepcion {

        EspecialidadCentro e = e_serv.obtenerId(idE);

        if (e == null) {
            throw new RecursoNoEncontrado_Excepcion();
        }
        return e;
    }

    /**
     * Esta funcionalidad edita la especialidad de un centro luego de ser comprobado que los nuevos datos son correctos.
     *
     * @param n_datos Especialidad de un centro.
     */
    public void validarActualizar(long idE, EspecialidadCentro n_datos) throws Exception {

        validarEspecialidadCentro(n_datos);

        e_serv.update(idE, n_datos);
    }

    /**
     * Esta funcionalidad elimina la especialidad de un centro luego de comprobar que se encuentra registrada.
     *
     * @param idE Identificador de la especialidad que será eliminada.
     * @throws RecursoNoEncontrado_Excepcion Si la especialidad no se encuentra registrada.
     */
    public void validarEliminar(long idE) throws RecursoNoEncontrado_Excepcion {

        if (e_serv.obtenerId(idE) == null) {
            throw new RecursoNoEncontrado_Excepcion();
        }
        e_serv.eliminar(idE);
    }

    /**
     * Esta funcionalidad lista todas las especialidades de los centros que se encuentren registradas.
     *
     * @return <code>List</code> Todas las especialidades registradas.
     */
    public List<EspecialidadCentro> validarListar() {
        return e_serv.listar();
    }

    /**
     * Esta funcionalidad lista todas las especialidades registradas de un centro.
     *
     * @param centro Centro.
     * @return <code>List</code> Todas las especialidades registradas.
     */
    public List<EspecialidadCentro> validarListarEspecialidadesDeCentro(Centro centro) {
        return e_serv.listarEspecialidadesDeUnCentro(centro);
    }

    /**
     * Esta funcionalidad lista todas las especialidades de un centro que se aún no han sido captadas en la Planilla de Datos.
     *
     * @param idC Identificador del centro.
     * @return <code>List</code> Todas las especialidades registradas.
     */
    public List<EspecialidadCentro> validarListarSinCaptar(long idC) {
        return e_serv.listarEspecialidadesSegunCaptacion(idC, false);
    }

    /**
     * Esta funcionalidad valida los datos de la especialidad de un centro.
     *
     * @param datos Especialidad de un centro.
     * @throws Exception Si la duración o escolaridad de la especialidad no son correctas.
     */
    private void validarEspecialidadCentro(EspecialidadCentro datos) throws Exception {

        Especialidad esp = datos.getEspecialidad();
        String niv = esp.getNivel().getTipoNivel();
        int dur = datos.getDuracion().getTipoDuracion();
        String esc = datos.getEscolaridad().getTipoEscolaridad();

        if (datos.getCurso().getTipoCurso().equals("Diurno")) {
            if (esc.equals("9no")) {
                if ((niv.equals("T.M") && dur != 7) || (niv.equals("O.C") && dur != 4)) {
                    throw new Exception("La duración no se corresponde con las características de esta especialidad!");
                }
            } else {
                throw new Exception("Esta escolaridad no se encuentra disponible en este Tipo de Curso!");
            }
        } else {
            // V/Nocturno
            if (niv.equals("O.C")) {
                throw new Exception("Este nivel de estudio no se encuentra disponible para este Tipo de Curso!");
            }
            if ((esc.equals("9no") && dur != 5) || (esc.equals("12mo") && (dur != 2 && dur != 3 && dur != 4))) {
                throw new Exception("La duración/escolaridad no se corresponde con las características de esta especialidad!");
            }
        }
    }
}