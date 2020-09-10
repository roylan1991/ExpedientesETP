package com.roylan.expedientes_etp.database.repositories;

import com.roylan.expedientes_etp.database.entities.Rama;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RamaRepository extends JpaRepository<Rama, Long> {

    Rama findByIdRama(long idR);

    List<Rama> findAllByOrderByIdRamaAsc();
}
