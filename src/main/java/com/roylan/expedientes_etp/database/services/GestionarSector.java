package com.roylan.expedientes_etp.database.services;

import com.roylan.expedientes_etp.database.entities.Sector;
import com.roylan.expedientes_etp.database.repositories.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Esta clase gestiona las operaciones referentes al sector de un centro.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Service
@Transactional
public class GestionarSector {

    @Autowired
    private SectorRepository s_repo;

    public GestionarSector() {
    }

    /**
     * Esta funcionalidad permite registar un nuevo sector.
     *
     * @param s Nuevo sector.
     */
    public void adicionar(Sector s) {
        s_repo.save(s);
    }

    /**
     * Esta funcionalidad permite obtener un sector mediante su identificador.
     *
     * @param idS Identificador del sector.
     * @return <code>Sector</code> Sector obtenido.
     *
     * <code>null</code> Si no existe el sector.
     */
    @Cacheable(cacheNames = "sector_id")
    public Sector obtenerId(long idS) {
        return s_repo.findByIdSector(idS);
    }

    /**
     * Esta funcionalidad permite eliminar un sector.
     *
     * @param idS Identificador del sector.
     */
    public void eliminar(long idS) {
        s_repo.delete(obtenerId(idS));
    }

    /**
     * Esta funcionalidad permite listar todos los sectores registrados.
     *
     * @return <code>List</code> Todos los sectores registrados.
     */
    @Cacheable(cacheNames = "lst_sectores")
    public List<Sector> listar() {
        return s_repo.findAll();
    }
}