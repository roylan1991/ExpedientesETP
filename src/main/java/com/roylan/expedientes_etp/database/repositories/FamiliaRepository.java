package com.roylan.expedientes_etp.database.repositories;

import com.roylan.expedientes_etp.database.entities.Familia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamiliaRepository extends JpaRepository<Familia, Long> {

    Familia findByIdFamilia(long idF);

    List<Familia> findAllByOrderByIdFamiliaAsc();
}
