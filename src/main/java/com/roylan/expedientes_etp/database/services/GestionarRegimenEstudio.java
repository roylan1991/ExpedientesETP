package com.roylan.expedientes_etp.database.services;

import com.roylan.expedientes_etp.database.entities.RegimenEstudio;
import com.roylan.expedientes_etp.database.repositories.RegimenEstudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Esta clase gestiona las operaciones referentes a un régimen de estudio.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Service
@Transactional
public class GestionarRegimenEstudio {

    @Autowired
    private RegimenEstudioRepository r_repo;

    public GestionarRegimenEstudio() {
    }

    /**
     * Esta funcionalidad permite obtener un régimen de estudio mediante su identificador.
     *
     * @param idR Identificador del régimen de estudio.
     * @return <code>RegimenEstudio</code> Régimen de estudio obtenida.
     *
     * <code>null</code> Si no existe el régimen de estudio.
     */
    public RegimenEstudio obtenerId(int idR) {
        return r_repo.findByIdRegimen(idR);
    }

    /**
     * Esta funcionalidad permite listar todos los régimenes de estudios registrados.
     *
     * @return <code>List</code> Todos los régimenes de estudios registrados.
     */
    public List<RegimenEstudio> listar() {
        return r_repo.findAll();
    }
}


