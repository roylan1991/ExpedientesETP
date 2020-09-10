package com.roylan.expedientes_etp.database.services;

import com.roylan.expedientes_etp.database.entities.RangoEdad;
import com.roylan.expedientes_etp.database.repositories.RangoEdadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Esta clase gestiona las operaciones referentes al rango de edades de un tipo de curso.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Service
@Transactional
public class GestionarRangoEdadImpl implements IGestionarService<RangoEdad> {

    @Autowired
    private RangoEdadRepository re_repo;

    @Autowired
    private GestionarPlanillaDatosImpl planillas_serv;

    public GestionarRangoEdadImpl() {
    }

    /**
     * Esta funcionalidad permite registar un nuevo rango de edades.
     *
     * @param r Nuevo rango de edades.
     */
    public void adicionar(RangoEdad r) {
        re_repo.save(r);
    }

    /**
     * Esta funcionalidad permite obtener un rango de edades mediante su identificador.
     *
     * @param idR Identificador del rango de edades.
     * @return <code>RangoEdad</code> Rango de edades obtenido.
     *
     * <code>null</code> Si no existe el rango de edades.
     */
    public RangoEdad obtenerId(long idR) {
        return re_repo.findByIdRangoEdad(idR);
    }

    /**
     * Esta funcionalidad permite editar los datos de un rango de edades.
     *
     * @param idR     Identificador del rango de edades a editar.
     * @param n_datos Nuevos datos del rango de edades.
     */
    @CacheEvict(cacheNames = {"lst_centro", "lst_centro_mcpio", "lst_centro_prov"}, allEntries = true)
    public void update(long idR, RangoEdad n_datos) {
        RangoEdad re = obtenerId(idR);
        re.getDiurno().setTotalMenos15(n_datos.getDiurno().getTotalMenos15());
        re.getDiurno().setHembrasMenos15(n_datos.getDiurno().getHembrasMenos15());
        re.getDiurno().setTotalEntre15_16(n_datos.getDiurno().getTotalEntre15_16());
        re.getDiurno().setHembrasEntre15_16(n_datos.getDiurno().getHembrasEntre15_16());
        re.getDiurno().setTotalMayores16(n_datos.getDiurno().getTotalMayores16());
        re.getDiurno().setHembrasMayores16(n_datos.getDiurno().getHembrasMayores16());

        re.getCpt().setTotalMenos15(n_datos.getCpt().getTotalMenos15());
        re.getCpt().setHembrasMenos15(n_datos.getCpt().getHembrasMenos15());
        re.getCpt().setTotalEntre15_16(n_datos.getCpt().getTotalEntre15_16());
        re.getCpt().setHembrasEntre15_16(n_datos.getCpt().getHembrasEntre15_16());
        re.getCpt().setTotalMayores16(n_datos.getCpt().getTotalMayores16());
        re.getCpt().setHembrasMayores16(n_datos.getCpt().getHembrasMayores16());

        re_repo.updateRangoEdad(idR, re.getDiurno(), re.getCpt());

        planillas_serv.actualizarProgreso(n_datos.getCentro());
    }

    /**
     * Esta funcionalidad permite eliminar un rango de edades.
     *
     * @param idR Identificador del rango de edades.
     */
    public void eliminar(long idR) {
        RangoEdad r = obtenerId(idR);

        re_repo.delete(r);
    }

    /**
     * Esta funcionalidad permite listar todos los rangos de edades registrados.
     *
     * @return <code>List</code> Todos los rangos de edades registrados.
     */
    public List<RangoEdad> listar() {
        return re_repo.findAll();
    }
}