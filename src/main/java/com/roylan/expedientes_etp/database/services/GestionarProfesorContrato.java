package com.roylan.expedientes_etp.database.services;

import com.roylan.expedientes_etp.database.entities.ProfesorContrato;
import com.roylan.expedientes_etp.database.repositories.ProfesorContratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Esta clase gestiona las operaciones referentes a los profesores contratados.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Service
@Transactional
public class GestionarProfesorContrato {

    @Autowired
    private ProfesorContratoRepository pc_repo;

    public GestionarProfesorContrato() {
    }

    /**
     * Esta funcionalidad permite registar un nuevo profesor contratado.
     *
     * @param pc Nuevo profesor contratado.
     */
    public void adicionar(ProfesorContrato pc) {
        pc_repo.save(pc);
    }

    /**
     * Esta funcionalidad permite obtener un profesor contratado mediante su identificador.
     *
     * @param idP Identificador del profesor.
     * @return <code>ProfesorContrato</code> Profesor contratado obtenido.
     *
     * <code>null</code> Si no existe el profesor.
     */
    public ProfesorContrato obtenerId(long idP) {
        return pc_repo.findByIdTipoProfesor(idP);
    }

    /**
     * Esta funcionalidad permite editar los profesores contratados del personal de un centro.
     *
     * @param idP     Identificador de los profesores contratados a editar.
     * @param n_datos Nuevos datos de los profesores contratados.
     */
    public void update(long idP, ProfesorContrato n_datos) {
        pc_repo.updateProfesorContrato(idP, n_datos.getTotalProfesores(), n_datos.getNoProfesionales(), n_datos.getNoProfesionalesEstudiando());
    }

    /**
     * Esta funcionalidad permite listar todos los profesores contratados registrados.
     *
     * @return <code>List</code> Todos los profesores contratados registrados.
     */
    public List<ProfesorContrato> listar() {
        return pc_repo.findAll();
    }
}