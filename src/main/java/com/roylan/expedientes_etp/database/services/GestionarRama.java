package com.roylan.expedientes_etp.database.services;

import com.roylan.expedientes_etp.database.entities.Rama;
import com.roylan.expedientes_etp.database.repositories.RamaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Esta clase gestiona las operaciones referentes a la rama de una especialidad.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Service
@Transactional
public class GestionarRama {

    @Autowired
    private RamaRepository r_repo;

    public GestionarRama() {
    }

    /**
     * Esta funcionalidad permite registar una nueva rama.
     *
     * @param r Nueva rama.
     */
    public void adicionar(Rama r) {
        r_repo.save(r);
    }

    /**
     * Esta funcionalidad permite obtener una rama mediante su identificador.
     *
     * @param idR Identificador de la rama.
     * @return <code>Rama</code> Rama obtenida.
     *
     * <code>null</code> Si no existe la rama.
     */
    @Cacheable(cacheNames = "rama_id")
    public Rama obtenerId(long idR) {
        return r_repo.findByIdRama(idR);
    }

    /**
     * Esta funcionalidad permite listar todas las ramas registradas.
     *
     * @return <code>List</code> Todas las ramas registradas.
     */
    @Cacheable(cacheNames = "lst_ramas")
    public List<Rama> listar() {
        return r_repo.findAllByOrderByIdRamaAsc();
    }
}