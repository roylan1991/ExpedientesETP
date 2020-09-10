package com.roylan.expedientes_etp.database.repositories;

import com.roylan.expedientes_etp.database.entities.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long> {

    Sector findByIdSector(long idS);
}
