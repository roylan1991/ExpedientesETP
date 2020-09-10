package com.roylan.expedientes_etp.database.services;

import com.roylan.expedientes_etp.database.entities.Provincia;
import com.roylan.expedientes_etp.database.repositories.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Esta clase gestiona todas las operaciones referentes a una provincia.
 *
 * @author Roylan Bressler
 * @see List
 * @since 1.0
 */
@Service
@Transactional
public class GestionarProvincia {

    @Autowired
    private ProvinciaRepository p_repo;

    public GestionarProvincia() {
    }

    /**
     * Esta funcionalidad permite registar una nueva provincia.
     *
     * @param p Nueva Provincia.
     */
    public void adicionar(Provincia p) {
        p_repo.save(p);
    }

    /**
     * Esta funcionalidad permite obtener una provincia mediante su identificador.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Provincia</code> Provincia obtenida.
     *
     * <code>null</code> Si no existe la provincia.
     */
    @Cacheable(cacheNames = "prov_id")
    public Provincia obtenerId(long idP) {
        return p_repo.findByIdProvincia(idP);
    }

    /**
     * Esta funcionalidad permite eliminar una provincia.
     *
     * @param idP Identificador de la provincia.
     */
    public void eliminar(long idP) {
        p_repo.delete(obtenerId(idP));
    }

    /**
     * Esta funcionalidad permite listar todas las provincias registradas.
     *
     * @return <code>List</code> Todas las provincias registradas.
     */
    @Cacheable(cacheNames = "lst_provincias")
    public List<Provincia> listar() {
        return p_repo.findAll();
    }
}