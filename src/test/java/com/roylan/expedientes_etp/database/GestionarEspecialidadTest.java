package com.roylan.expedientes_etp.database;

import static org.junit.Assert.assertEquals;

import com.roylan.expedientes_etp.database.entities.Familia;
import com.roylan.expedientes_etp.database.entities.Nivel;
import com.roylan.expedientes_etp.database.entities.Rama;
import com.roylan.expedientes_etp.database.services.GestionarRama;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import com.roylan.expedientes_etp.database.entities.Especialidad;
import com.roylan.expedientes_etp.database.services.GestionarEspecialidadImpl;
import com.roylan.expedientes_etp.database.services.GestionarFamilia;
import com.roylan.expedientes_etp.database.services.GestionarNivel;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GestionarEspecialidadTest {

    @Autowired
    private GestionarEspecialidadImpl esps;

    @Autowired
    private GestionarNivel nivel;

    @Autowired
    private GestionarFamilia fam;

    @Autowired
    private GestionarRama rama;

    @Before
    public void init() {
        nivel.adicionar(new Nivel("T.M"));
        nivel.adicionar(new Nivel("O.C"));

        Rama r = new Rama();
        r.setTipoRama("Informática");
        rama.adicionar(r);

        fam.adicionar(new Familia("Informática y Comunicaciones", rama.obtenerId(1)));
    }

    @Test
    public void crud() {
        Especialidad e = new Especialidad();
        e.setNombEspecialidad("Invento");
        e.setCodEspecialidad("301020");
        e.setFamilia(fam.obtenerId(1));
        e.setNivel(nivel.obtenerId(1));

        esps.adicionar(e);

        Especialidad eBD = esps.obtenerCodigo("301020");

        assertEquals(eBD.getNombEspecialidad(), e.getNombEspecialidad());
        assertEquals(eBD.getNivel().getTipoNivel(), e.getNivel().getTipoNivel());

        e.setNombEspecialidad("Invento2");
        e.setCodEspecialidad("601020");
       e.setNivel(nivel.obtenerId(2));

        esps.update(1, e);
        eBD = esps.obtenerId(1);

        assertEquals(eBD.getNombEspecialidad(), e.getNombEspecialidad());
        assertEquals(eBD.getCodEspecialidad(), e.getCodEspecialidad());
        assertEquals(eBD.getNivel().getTipoNivel(), e.getNivel().getTipoNivel());

        esps.eliminar(1);
        assertEquals(esps.obtenerId(1), null);

        mostrar(esps.listar());
    }

    private void mostrar(List<Especialidad> lst) {
        System.out.println("\n[ESPECIALIDADES]");

        for (int i = 0; i < lst.size(); i++) {
            System.out.println(lst.get(i).toString());
        }
        System.out.println("\n");
    }
}