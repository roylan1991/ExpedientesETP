package com.roylan.expedientes_etp.database.repositories;

import com.roylan.expedientes_etp.database.entities.Municipio;
import com.roylan.expedientes_etp.database.entities.Provincia;
import com.roylan.expedientes_etp.database.entities.RolUsuario;
import com.roylan.expedientes_etp.database.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByIdUsuario(long idU);

    Usuario findByNombUsuario(String nombU);

    List<Usuario> findAllByRol_TipoRolIsNotAndMcpioProvOrderByRolAscMcpioAscNombUsuarioAsc(String rol, Provincia prov);

    List<Usuario> findAllByOrderByRolAscMcpioAscNombUsuarioAsc();

    @Modifying
    @Query("update Usuario set nombDescriptivo=?2, password=?3, estado=?4, mcpio=?5, rol=?6 where idUsuario=?1")
    void updateUsuario(long idU, String nomDescriptivo, String passwd, boolean estado, Municipio m, RolUsuario rol);
}
