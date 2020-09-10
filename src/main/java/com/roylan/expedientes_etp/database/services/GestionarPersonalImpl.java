package com.roylan.expedientes_etp.database.services;

import com.roylan.expedientes_etp.database.entities.Personal;
import com.roylan.expedientes_etp.database.repositories.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Esta clase gestiona las operaciones referentes al personal.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Service
@Transactional
public class GestionarPersonalImpl implements IGestionarService<Personal> {

    @Autowired
    private PersonalRepository p_repo;

    public GestionarPersonalImpl() {
    }

    /**
     * Esta funcionalidad permite registar un nuevo personal.
     *
     * @param p Nuevo personal.
     */
    public void adicionar(Personal p) {
        p_repo.save(p);
    }

    /**
     * Esta funcionalidad permite obtener un personal mediante su identificador.
     *
     * @param idP Identificador del personal.
     * @return <code>Personal</code> Personal obtenido.
     *
     * <code>null</code> Si no existe el personal.
     */
    public Personal obtenerId(long idP) {
        return p_repo.findByIdPersonal(idP);
    }

    /**
     * Esta funcionalidad permite editar el personal de un centro.
     *
     * @param idP     Identificador del personal a editar.
     * @param n_datos Nuevos datos del personal.
     */
    public void update(long idP, Personal n_datos) {
        p_repo.updatePersonal(idP, n_datos.getTotal(), n_datos.getHembras());
    }

    /**
     * Esta funcionalidad permite eliminar un personal.
     *
     * @param p Identificador del personal.
     */
    public void eliminar(long p) {
        Personal pe = obtenerId(p);

        p_repo.delete(pe);
    }

    /**
     * Esta funcionalidad permite listar todos los personales registrados.
     *
     * @return <code>List</code> Todos los personales registrados.
     */
    public List<Personal> listar() {
        return p_repo.findAll();
    }
}