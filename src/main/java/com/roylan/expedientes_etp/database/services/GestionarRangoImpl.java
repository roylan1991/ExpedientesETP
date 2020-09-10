package com.roylan.expedientes_etp.database.services;

import com.roylan.expedientes_etp.database.entities.Rango;
import com.roylan.expedientes_etp.database.repositories.RangoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Esta clase gestiona las operaciones referentes al rango de edades de los estudiantes de un centro.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Service
@Transactional
public class GestionarRangoImpl implements IGestionarService<Rango> {

    @Autowired
    private RangoRepository r_repo;

    public GestionarRangoImpl() {
    }

    /**
     * Esta funcionalidad permite registar un nuevo rango de edades.
     *
     * @param r Nuevo rango de edades.
     */
    public void adicionar(Rango r) {
        r_repo.save(r);
    }

    /**
     * Esta funcionalidad permite obtener un rango de edades mediante su identificador.
     *
     * @param idR Identificador del rango de edades.
     * @return <code>Rango</code> Rango de edades obtenido.
     *
     * <code>null</code> Si no existe el rango de edades.
     */
    public Rango obtenerId(long idR) {
        return r_repo.findByIdRango(idR);
    }

    /**
     * Esta funcionalidad permite editar los datos de un rango de edades.
     *
     * @param idR     Identificador del rango de edades a editar.
     * @param n_datos Nuevos datos del rango de edades.
     */
    public void update(long idR, Rango n_datos) {
        r_repo.updateRango(idR, n_datos.getTotalMenos15(), n_datos.getHembrasMenos15(), n_datos.getTotalEntre15_16(), n_datos.getHembrasEntre15_16(), n_datos.getTotalMayores16(), n_datos.getHembrasMayores16());
    }

    /**
     * Esta funcionalidad permite eliminar un rango de edades.
     *
     * @param idR Identificador del rango de edades.
     */
    public void eliminar(long idR) {
        Rango r = obtenerId(idR);

        r_repo.delete(r);
    }

    /**
     * Esta funcionalidad permite listar todos los rangos de edades registrados.
     *
     * @return <code>List</code> Todos los rangos de edades registrados.
     */
    public List<Rango> listar() {
        return r_repo.findAll();
    }
}


