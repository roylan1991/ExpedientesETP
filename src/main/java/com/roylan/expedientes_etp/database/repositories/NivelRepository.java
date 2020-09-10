package com.roylan.expedientes_etp.database.repositories;

import com.roylan.expedientes_etp.database.entities.Nivel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NivelRepository extends JpaRepository<Nivel, Long> {

    Nivel findByIdNivel(long idN);
}
