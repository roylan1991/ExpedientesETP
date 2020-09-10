package com.roylan.expedientes_etp.business;

import com.roylan.expedientes_etp.database.entities.Caratula;
import com.roylan.expedientes_etp.database.entities.Rango;
import com.roylan.expedientes_etp.database.entities.RangoEdad;
import com.roylan.expedientes_etp.database.services.GestionarRangoEdadImpl;
import com.roylan.expedientes_etp.excepciones.RecursoNoEncontrado_Excepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Esta clase valida que se gestionen correctamente todas las operaciones referentes a los rangos de edades.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Component
public class ValidacionRangoEdadImpl implements IValidacion<RangoEdad> {

    @Autowired
    private GestionarRangoEdadImpl r_serv;

    public ValidacionRangoEdadImpl() {
    }

    /**
     * Esta funcionalidad registra un rango de edades luego de ser comprobado que no se encuentra registrado y que sus datos estén correctos.
     *
     * @param re Datos del rango de edades.
     */
    public void validarAdicionar(RangoEdad re) {
        r_serv.adicionar(re);
    }

    /**
     * Esta funcionalidad devuelve un rango de edades luego de comprobar que se encuentra registrado.
     *
     * @param idR Identificador del rango de edades.
     * @return <code>RangoEdad</code> Rango de Edades obtenido.
     * @throws RecursoNoEncontrado_Excepcion Si el rango de edades no se encuentra registrado.
     */
    public RangoEdad validarObtenerId(long idR) throws RecursoNoEncontrado_Excepcion {

        RangoEdad r = r_serv.obtenerId(idR);

        if (r == null) {
            throw new RecursoNoEncontrado_Excepcion();
        }

        return r;
    }

    /**
     * Esta funcionalidad edita un rango de edades luego de ser comprobado que los nuevos datos son correctos.
     *
     * @param idR     Identificador del rango de edades que se editará.
     * @param n_datos Nuevos datos del rango de edades.
     * @throws Exception Si los datos del rango de edades están sintácticamente correctos.
     * @throws Exception Si los datos del rango de edades no son correctos.
     */
    public void validarActualizar(long idR, RangoEdad n_datos) throws Exception {

        if (n_datos.getIdRangoEdad() == -1) {
            throw new Exception("Estos campos de datos solo admiten números válidos!");
        }

        Caratula car = n_datos.getCentro().getCaratula();
        Rango ran = n_datos.getDiurno();

        if (ran.getTotalMenos15() < ran.getHembrasMenos15()) {
            throw new Exception("Verifique en [Diurno] las matrículas en el rango con menos de 15 años!");
        }

        if (ran.getTotalEntre15_16() < ran.getHembrasEntre15_16()) {
            throw new Exception("Verifique en [Diurno] las matrículas en el rango entre 15 y 16 años!");
        }

        if (ran.getTotalMayores16() < ran.getHembrasMayores16()) {
            throw new Exception("Verifique en [Diurno] las matrículas en el rango con más de 16 años!");
        }

        if (car.getMatriculaInicialTM().getTotal() + car.getMatriculaInicialOC().getTotal() != ran.getMatriculaRangoTotal()) {
            throw new Exception("Verifique en [Diurno], la Matrícula Total no se corresponde con la cáratula!");
        }
        if (car.getMatriculaInicialTM().getTotalHembras() + car.getMatriculaInicialOC().getTotalHembras() != ran.getMatriculaRangoTotalHembras()) {
            throw new Exception("Verifique en [Diurno], la Matrícula Total Hembras no se corresponde con la Cáratula!");
        }

        ran = n_datos.getCpt();

        if (ran.getTotalMenos15() < ran.getHembrasMenos15()) {
            throw new Exception("Verifique en [V/Nocturno] las matrículas en el rango con menos de 15 años!");
        }

        if (ran.getTotalEntre15_16() < ran.getHembrasEntre15_16()) {
            throw new Exception("Verifique en [V/Nocturno] las matrículas en el rango entre 15 y 16 años!");
        }

        if (ran.getTotalMayores16() < ran.getHembrasMayores16()) {
            throw new Exception("Verifique en [V/Nocturno] las matrículas en el rango con más de 16 años!");
        }

        if (car.getMatriculaInicialCPT_TM().getTotal() != ran.getMatriculaRangoTotal()) {
            throw new Exception("Verifique en [V/Nocturno], la Matrícula Total no se corresponde con la Cáratula!");
        }

        if (car.getMatriculaInicialCPT_TM().getTotalHembras() != ran.getMatriculaRangoTotalHembras()) {
            throw new Exception("Verifique en [V/Nocturno], la Matrícula Total Hembras no se corresponde con la Cáratula!");
        }

        r_serv.update(idR, n_datos);
    }

    /**
     * Esta funcionalidad elimina un rango de edad luego de comprobar que se encuentra registrado.
     *
     * @param idR Identificador del rango de edad que será eliminado.
     * @throws RecursoNoEncontrado_Excepcion Si el rango de edad no se encuentra registrado.
     */
    public void validarEliminar(long idR) throws RecursoNoEncontrado_Excepcion {

        if (r_serv.obtenerId(idR) == null) {
            throw new RecursoNoEncontrado_Excepcion();
        }
        r_serv.eliminar(idR);
    }

    /**
     * Esta funcionalidad lista todos los rangos de edades que se encuentren registrados.
     *
     * @return <code>List</code> Todos loss rangos de edades registrados.
     */
    public List<RangoEdad> validarListar() {
        return r_serv.listar();
    }
}