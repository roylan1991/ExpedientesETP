package com.roylan.expedientes_etp.database.services;

import com.roylan.expedientes_etp.database.entities.Especialidad;
import com.roylan.expedientes_etp.database.repositories.EspecialidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Esta clase gestiona todas las operaciones referentes a una especialidad.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Service
@Transactional
public class GestionarEspecialidadImpl implements IGestionarService<Especialidad> {

    @Autowired
    private EspecialidadRepository e_repo;

    public GestionarEspecialidadImpl() {
    }

    /**
     * Esta funcionalidad permite registar una nueva especialidad.
     *
     * @param e Nueva especialidad.
     */
    @CacheEvict(cacheNames = {"lst_esp"}, allEntries = true)
    public void adicionar(Especialidad e) {
        e_repo.save(e);
    }

    /**
     * Esta funcionalidad permite obtener una especialidad mediante su identificador.
     *
     * @param idE Identificador de la especialidad.
     * @return <code>Especialidad</code> Especialidad obtenida.
     *
     * <code>null</code> Si no existe la especialidad.
     */
    @Cacheable(cacheNames = "esp_id")
    public Especialidad obtenerId(long idE) {
        return e_repo.findByIdEspecialidad(idE);
    }

    /**
     * Esta funcionalidad permite obtener una especialidad mediante su código.
     *
     * @param codE Código de la especialidad.
     * @return <code>Especialidad</code> Especialidad obtenida.
     *
     * <code>null</code> Si no existe la especialidad.
     */
    @Cacheable(cacheNames = "esp_cod")
    public Especialidad obtenerCodigo(String codE) {
        return e_repo.findByCodEspecialidad(codE);
    }

    /**
     * Esta funcionalidad permite editar una especialidad.
     *
     * @param idE     Identificador de la especialidad a editar.
     * @param n_datos Nuevos datos de la especialidad.
     */
    @CacheEvict(cacheNames = {"lst_esp", "esp_id", "esp_cod"}, allEntries = true)
    public void update(long idE, Especialidad n_datos) {
        e_repo.updateEspecialidad(idE, n_datos.getCodEspecialidad(), n_datos.getNombEspecialidad(), n_datos.getFamilia(), n_datos.getNivel());
    }

    /**
     * Esta funcionalidad permite eliminar una especialidad.
     *
     * @param idE Identificador de la especialidad.
     */
    @CacheEvict(cacheNames = {"lst_esp", "esp_id", "esp_cod"}, allEntries = true)
    public void eliminar(long idE) {
        Especialidad e = obtenerId(idE);

        e_repo.delete(e);
    }

    /**
     * Esta funcionalidad permite listar todas las especialidades registradas.
     *
     * @return <code>List</code> Todas las especialidades registradas.
     */
    @Cacheable(cacheNames = "lst_esp")
    public List<Especialidad> listar() {
        return e_repo.findAllByOrderByNivelAscNombEspecialidadAsc();
    }
}