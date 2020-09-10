package com.roylan.expedientes_etp.database;

import com.roylan.expedientes_etp.database.entities.*;
import com.roylan.expedientes_etp.database.services.GestionarAnnoEstudio;
import com.roylan.expedientes_etp.database.services.GestionarEspecialidadActualImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GestionarEspecialidadActualTest {

    @Autowired
    private GestionarEspecialidadActualImpl espA;

    @Autowired
    private GestionarAnnoEstudio annoEstudio;

    @Before
    public void init() {
        annoEstudio.adicionar(new AnnoEstudio("I Año"));
    }

    @Test
    public void crud() {
        EspecialidadActual ea = new EspecialidadActual();
        ea.setAnnoEstudio(annoEstudio.obtenerId(1));
        ea.setCantGrupos(5);
        ea.setnIngPromovidosTotal(3);
        ea.setnIngPromovidosPorTraslado(1);
        ea.setMatriculaActualTotal(200);
        ea.setMatriculaActualHembras(100);
        ea.setRepitentesTotal(2);
        ea.setRepitentesPorTraslado(2);
        ea.setReingresos(1);

        espA.adicionar(ea);
        EspecialidadActual eaBD = espA.obtenerId(1);

        assertEquals(eaBD.getAnnoEstudio().getTipoAnno(), "I Año");
        assertEquals(String.valueOf(eaBD.getCantGrupos()), String.valueOf(ea.getCantGrupos()));

        ea.setCantGrupos(10);
        ea.setMatriculaActualTotal(300);

        espA.update(1, ea);
        eaBD = espA.obtenerId(1);

        assertEquals(eaBD.getAnnoEstudio().getTipoAnno(), "I Año");
        assertEquals(String.valueOf(eaBD.getCantGrupos()), String.valueOf(ea.getCantGrupos()));
        assertEquals(eaBD.getMatriculaActualTotal(), ea.getMatriculaActualTotal());

        espA.eliminar(1);
        assertEquals(espA.obtenerId(1), null);

        mostrar(espA.listar());
    }

    private void mostrar(List<EspecialidadActual> lst) {
        System.out.println("\n[ESPECIALIDADES ACTUALES]");

        for (int i = 0; i < lst.size(); i++) {
            System.out.println(lst.get(i).toString());
        }
        System.out.println("\n");
    }
}