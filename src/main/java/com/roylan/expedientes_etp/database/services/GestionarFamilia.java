package com.roylan.expedientes_etp.database.services;

import com.roylan.expedientes_etp.database.entities.Familia;
import com.roylan.expedientes_etp.database.repositories.FamiliaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Esta clase gestiona las operaciones referentes a la familia de una especialidad.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Service
@Transactional
public class GestionarFamilia {

    @Autowired
    private FamiliaRepository f_repo;

    public GestionarFamilia() {
    }

    /**
     * Esta funcionalidad permite registar una nueva familia.
     *
     * @param f Nueva familia.
     */
    public void adicionar(Familia f) {
        f_repo.save(f);
    }

    /**
     * Esta funcionalidad permite obtener una familia mediante su identificador.
     *
     * @param idF Identificador de la familia.
     * @return <code>Familia</code> Familia obtenida.
     *
     * <code>null</code> Si no existe la familia.
     */
    @Cacheable(cacheNames = "familia_id")
    public Familia obtenerId(long idF) {
        return f_repo.findByIdFamilia(idF);
    }

    /**
     * Esta funcionalidad permite listar todas las familias registradas.
     *
     * @return <code>List</code> Todas las familias registradas.
     */
    @Cacheable(cacheNames = "lst_familias")
    public List<Familia> listar() {
        return f_repo.findAllByOrderByIdFamiliaAsc();
    }
}