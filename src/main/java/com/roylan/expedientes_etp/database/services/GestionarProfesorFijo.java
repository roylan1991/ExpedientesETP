package com.roylan.expedientes_etp.database.services;

import com.roylan.expedientes_etp.database.entities.ProfesorFijo;
import com.roylan.expedientes_etp.database.repositories.ProfesorFijoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Esta clase gestiona las operaciones referentes a los profesores fijos.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Service
@Transactional
public class GestionarProfesorFijo {

    @Autowired
    private ProfesorFijoRepository pf_repo;

    public GestionarProfesorFijo() {
    }

    /**
     * Esta funcionalidad permite registar un nuevo profesor fijo.
     *
     * @param pf Nuevo profesor fijo.
     */
    public void adicionar(ProfesorFijo pf) {
        pf_repo.save(pf);
    }

    /**
     * Esta funcionalidad permite obtener un profesor fijo mediante su identificador.
     *
     * @param idP Identificador del profesor.
     * @return <code>ProfesorFijo</code> Profesor fijo obtenido.
     *
     * <code>null</code> Si no existe el profesor.
     */
    public ProfesorFijo obtenerId(long idP) {
        return pf_repo.findByIdTipoProfesor(idP);
    }

    /**
     * Esta funcionalidad permite editar los profesores fijos del personal de un centro.
     *
     * @param idP     Identificador de los profesores fijos a editar.
     * @param n_datos Nuevos datos de los profesores fijos.
     */
    public void update(long idP, ProfesorFijo n_datos) {
        pf_repo.updateProfesorFijo(idP, n_datos.getTotalProfesores(), n_datos.getNoTitulados(), n_datos.getNoTituladosEstudiando());
    }

    /**
     * Esta funcionalidad permite listar todos los profesores fijos registrados.
     *
     * @return <code>List</code> Todos los profesores fijos registrados.
     */
    public List<ProfesorFijo> listar() {
        return pf_repo.findAll();
    }
}