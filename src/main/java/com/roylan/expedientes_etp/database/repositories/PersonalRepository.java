package com.roylan.expedientes_etp.database.repositories;

import com.roylan.expedientes_etp.database.entities.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalRepository extends JpaRepository<Personal, Long> {

    Personal findByIdPersonal(long idP);

    @Modifying
    @Query("update Personal set total=?2, hembras=?3 where idPersonal=?1")
    void updatePersonal(long idP, int total, int hembras);
}
