package com.roylan.expedientes_etp.business;

import com.roylan.expedientes_etp.database.entities.Caratula;
import com.roylan.expedientes_etp.database.services.GestionarCaratulaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Esta clase valida que se gestionen correctamente todas las operaciones referentes a las carátulas de los centros.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Component
public class ValidacionCaratulaImpl implements IValidacion<Caratula> {

    @Autowired
    private GestionarCaratulaImpl c_serv;

    public ValidacionCaratulaImpl() {
    }

    /**
     * Esta funcionalidad registra una nueva carátula.
     *
     * @param c Datos de la carátula.
     */
    public void validarAdicionar(Caratula c) {
        c_serv.adicionar(c);
    }

    /**
     * Esta funcionalidad devuelve una carátula luego de comprobar que se encuentra registrada.
     *
     * @param idC Identificador de la carátula.
     * @return <code>Caratula</code> Carátula obtenida.
     * @throws Exception Si la carátula no se encuentra registrada.
     */
    public Caratula validarObtenerId(long idC) throws Exception {

        Caratula c = c_serv.obtenerId(idC);

        if (c == null) {
            throw new Exception("Esta carátula no se encuentra registrada!");
        }
        return c;
    }

    /**
     * Esta funcionalidad edita una carátula luego de ser comprobado que los nuevos datos son correctos.
     *
     * @param idC     Identificador de la carátula que se editará.
     * @param n_datos Nuevos datos de la carátula.
     * @throws Exception Si los datos de la carátula están sintácticamente correctos.
     * @throws Exception Si los datos de la carátula no son correctos.
     */
    public void validarActualizar(long idC, Caratula n_datos) throws Exception {

        if (n_datos.getIdCaratula() == -1) {
            throw new Exception("Estos campos de datos solo admiten números válidos!");
        }

        if (n_datos.getRegimenTM().getInternos() < n_datos.getRegimenTM().getInternosHembras()) {
            throw new Exception("Verifique en el [Año Escolar Iniciado], las Matrículas de Internos del Curso Diurno para T.M!");
        }

        if (n_datos.getRegimenTM().getSeminternos() < n_datos.getRegimenTM().getSeminternosHembras()) {
            throw new Exception("Verifique en el [Año Escolar Iniciado], las Matrículas de Seminternos del Curso Diurno para T.M!");
        }

        if (n_datos.getRegimenTM().getInternos() + n_datos.getRegimenTM().getSeminternos() > n_datos.getMatriculaInicialTM().getTotal()) {
            throw new Exception("Verifique en el [Año Escolar Iniciado], las Matrículas: internos y seminternos del Curso Diurno para T.M!");
        }

        if (n_datos.getRegimenOC().getInternos() < n_datos.getRegimenOC().getInternosHembras()) {
            throw new Exception("Verifique en el [Año Escolar Iniciado], las Matrículas de Internos del Curso Diurno para O.C!");
        }

        if (n_datos.getRegimenOC().getSeminternos() < n_datos.getRegimenOC().getSeminternosHembras()) {
            throw new Exception("Verifique en el [Año Escolar Iniciado], las Matrículas de Seminternos del Curso Diurno para O.C!");
        }

        if (n_datos.getRegimenOC().getInternos() + n_datos.getRegimenOC().getSeminternos() > n_datos.getMatriculaInicialOC().getTotal()) {
            throw new Exception("Verifique en el [Año Escolar Iniciado], las Matrículas: internos y seminternos del Curso Diurno para O.C!");
        }

        c_serv.updateRegimenCaratula(idC, n_datos);
    }

    /**
     * Esta funcionalidad elimina una carátula.
     *
     * @param idC Identificador de la carátula que será eliminada.
     */
    public void validarEliminar(long idC) {
        c_serv.eliminar(idC);
    }

    /**
     * Esta funcionalidad lista todas las carátulas que se encuentren registradas.
     *
     * @return <code>List</code> Todas las carátulas registradas.
     */
    public List<Caratula> validarListar() {
        return c_serv.listar();
    }
}