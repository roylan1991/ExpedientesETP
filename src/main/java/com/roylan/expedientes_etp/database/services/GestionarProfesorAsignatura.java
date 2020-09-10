package com.roylan.expedientes_etp.database.services;

import com.roylan.expedientes_etp.database.entities.ProfesorAsignatura;
import com.roylan.expedientes_etp.database.repositories.ProfesorAsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Esta clase gestiona las operaciones referentes a los profesores por asignaturas.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Service
@Transactional
public class GestionarProfesorAsignatura {

    @Autowired
    private ProfesorAsignaturaRepository pa_repo;

    public GestionarProfesorAsignatura() {
    }

    /**
     * Esta funcionalidad permite registar un nuevo profesor por asignaturas.
     *
     * @param pa Nuevo profesor por asignaturas.
     */
    public void adicionar(ProfesorAsignatura pa) {
        pa_repo.save(pa);
    }

    /**
     * Esta funcionalidad permite obtener un profesor por asignatura.
     *
     * @param idP Identificador del profesor.
     * @return <code>ProfesorAsignatura</code> Profesor por asignatura obtenido.
     *
     * <code>null</code> Si no existe el profesor.
     */
    public ProfesorAsignatura obtenerId(int idP) {
        return pa_repo.findByIdProfAsig(idP);
    }

    /**
     * Esta funcionalidad permite listar todos los profesores fijos por asignaturas registrados.
     *
     * @return <code>List</code> Todos los profesores fijos por asignaturas registrados.
     */
    public List<ProfesorAsignatura> listar() {
        return pa_repo.findAll();
    }
}