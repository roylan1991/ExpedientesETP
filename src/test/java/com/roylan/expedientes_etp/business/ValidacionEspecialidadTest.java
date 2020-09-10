package com.roylan.expedientes_etp.business;

import com.roylan.expedientes_etp.database.entities.*;
import com.roylan.expedientes_etp.database.services.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ValidacionEspecialidadTest {

    @Autowired
    private ValidacionEspecialidadImpl esp;

    @Autowired
    private GestionarNivel nivel;

    @Autowired
    private GestionarFamilia fam;

    @Autowired
    private GestionarRama rama;

    private Especialidad e;

    @Before
    public void init() {
        nivel.adicionar(new Nivel("T.M"));
        nivel.adicionar(new Nivel("O.C"));

        Rama r = new Rama();
        r.setTipoRama("Informática");
        rama.adicionar(r);

        fam.adicionar(new Familia("Informática y Comunicaciones", rama.obtenerId(1)));

        e = new Especialidad();
        e.setNombEspecialidad("DesempleadoTM");
        e.setCodEspecialidad("8060111");
        e.setNivel(nivel.obtenerId(1));
        e.setFamilia(fam.obtenerId(1));
    }

    @Test
    public void crud() throws Exception {
        try {
            esp.validarAdicionar(e);
        } catch (Exception e) {
            System.out.println("Código de especialidad incorrecto: Prueba Exitosa");
        }

        e.setCodEspecialidad("6060111");

        try {
            esp.validarAdicionar(e);
        } catch (Exception e) {
            System.out.println("No coincidencia de especialidad con tipo nivel: Prueba Exitosa");
        }

        e.setCodEspecialidad("3060111");

        esp.validarAdicionar(e);

        Especialidad eBD = esp.validarObtenerId(1);
        assertEquals(eBD.getNombEspecialidad(), e.getNombEspecialidad());
        assertEquals(eBD.getNivel().getTipoNivel(), e.getNivel().getTipoNivel());

        Especialidad e2 = new Especialidad();
        e2.setNombEspecialidad("EmpleadoOC");
        e2.setCodEspecialidad("6060111");
        e2.setNivel(nivel.obtenerId(2));
        e2.setFamilia(fam.obtenerId(1));
        esp.validarAdicionar(e2);

        e.setCodEspecialidad("6060111");

        try {
            esp.validarActualizar(1, e);
        } catch (Exception e) {
            System.out.println("Actualizando con código utilizado por otra especialidad: Prueba Exitosa");
        }

        e.setCodEspecialidad("3060111");
        e.setNombEspecialidad("EmpleadoTM");

        esp.validarActualizar(1, e);

        eBD = esp.validarObtenerId(1);
        assertEquals(eBD.getNombEspecialidad(), e.getNombEspecialidad());
        assertEquals(eBD.getCodEspecialidad(), e.getCodEspecialidad());
        assertEquals(eBD.getNivel().getTipoNivel(), e.getNivel().getTipoNivel());

        mostrar(esp.validarListar());

        esp.validarEliminar(1);
        esp.validarEliminar(2);

        try {
            esp.validarObtenerId(1);
        } catch (Exception e) {
            System.out.println("Especialidad ya eliminada: Prueba Exitosa");
        }
    }

    private void mostrar(List<Especialidad> lst) {
        System.out.println("\n[ESPECIALIDADES:]");

        for (int i = 0; i < lst.size(); i++) {
            System.out.println(lst.get(i).toString());
        }
        System.out.println("\n");
    }
}