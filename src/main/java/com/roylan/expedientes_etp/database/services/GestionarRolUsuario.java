package com.roylan.expedientes_etp.database.services;

import com.roylan.expedientes_etp.database.entities.RolUsuario;
import com.roylan.expedientes_etp.database.repositories.RolUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Esta clase gestiona las operaciones referentes a los roles de los usuarios.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Service
@Transactional
public class GestionarRolUsuario {

    @Autowired
    private RolUsuarioRepository r_repo;

    public GestionarRolUsuario() {
    }

    /**
     * Esta funcionalidad permite registar un nuevo rol.
     *
     * @param r Nuevo rol.
     */
    public void adicionar(RolUsuario r) {
        r_repo.save(r);
    }

    /**
     * Esta funcionalidad permite obtener un rol de usuario mediante su identificador.
     *
     * @param idR Identificador del rol de usuario.
     * @return <code>RolUsuario</code> Rol usuario obtenido.
     *
     * <code>null</code> Si no existe el rol.
     */
    @Cacheable(cacheNames = "rol_id")
    public RolUsuario obtenerId(int idR) {
        return r_repo.findByIdRol(idR);
    }

    /**
     * Esta funcionalidad permite listar todos los roles de usuarios registrados.
     *
     * @return <code>List</code> Todas los roles de usuarios registrados.
     */
    @Cacheable(cacheNames = "lst_roles")
    public List<RolUsuario> listar() {
        return r_repo.findAll();
    }
}