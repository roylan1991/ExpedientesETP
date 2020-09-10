package com.roylan.expedientes_etp.database.repositories;

import com.roylan.expedientes_etp.database.entities.RolUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolUsuarioRepository extends JpaRepository<RolUsuario, Long> {

    RolUsuario findByIdRol(long idR);
}
