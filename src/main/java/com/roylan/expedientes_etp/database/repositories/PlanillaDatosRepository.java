package com.roylan.expedientes_etp.database.repositories;

import com.roylan.expedientes_etp.database.entities.PlanillaDatos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanillaDatosRepository extends JpaRepository<PlanillaDatos, Long> {

    PlanillaDatos findByIdPlanilla(long idP);

    @Modifying
    @Query("update PlanillaDatos set procdGraduados=?2, hembProcdGraduados=?3, procdPosiblesGraduados=?4, hembProcdPosiblesGraduados=?5, procdNuevosIngresos=?6, hembProcdNuevosIngresos=?7 where idPlanilla=?1")
    void updatePlanilla(long idP, int procdGraduados, int hembProcdGraduados, int procdPosiblesGraduados, int hembProcdPosiblesGraduados, int procdNuevosIngresos, int hembProcdNuevosIngresos);

    List<PlanillaDatos> findAllByCentroIdCentroOrderByIdPlanilla(long idC);

    void deleteAllByCentroIdCentro(long idC);

    PlanillaDatos findByEspecialidadCentroIdEspecialidad(long idEsp);
}