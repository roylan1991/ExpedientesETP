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
public class ValidacionEspecialidadCentroTest {

    @Autowired
    private ValidacionEspecialidadCentroImpl espC;

    @Autowired
    private GestionarEspecialidadImpl esps;

    @Autowired
    private GestionarCentroImpl centros;

    @Autowired
    private GestionarCurso curso;

    @Autowired
    private GestionarDuracion durac;

    @Autowired
    private GestionarEscolaridad escola;

    @Autowired
    private GestionarFamilia familia;

    @Autowired
    private GestionarRama rama;

    @Autowired
    private GestionarNivel nivel;

    private EspecialidadCentro ec;

    @Before
    public void init() {

        escola.adicionar(new Escolaridad("9no"));
        curso.adicionar(new Curso("Diurno"));
        curso.adicionar(new Curso("CPT"));
        durac.adicionar(new Duracion(4));
        durac.adicionar(new Duracion(7));
        nivel.adicionar(new Nivel("T.M"));

        Rama r = new Rama();
        r.setTipoRama("Informática");
        rama.adicionar(r);

        familia.adicionar(new Familia("Informática y Comunicaciones", rama.obtenerId(1)));

        Centro c = new Centro();
        c.setNombCentro("Pepito Tey");
        c.setCodCentro("34060000");
        c.setDireccion("La Risueña s/n");
        c.setTelefono("555555");
        centros.adicionar(c);

        Especialidad e = new Especialidad();
        e.setNombEspecialidad("Invento");
        e.setCodEspecialidad("301020");
        e.setFamilia(familia.obtenerId(1));
        e.setNivel(nivel.obtenerId(1));
        esps.adicionar(e);

        ec = new EspecialidadCentro();
        ec.setEspecialidad(esps.obtenerId(1));
        ec.setDuracion(durac.obtenerId(1));
        ec.setFueCaptada(false);
        ec.setEscolaridad(escola.obtenerId(1));
        ec.setCurso(curso.obtenerId(1));
        ec.setDuracion(durac.obtenerId(1));
        ec.setCentro(centros.obtenerId(1));
    }

    @Test
    public void crud() throws Exception {
        try {
            espC.validarAdicionar(ec);
        } catch (Exception e) {
            System.out.println("La duración no se corresponde con el nivel: Prueba Exitosa");
        }

        ec.setDuracion(durac.obtenerId(2));

        espC.validarAdicionar(ec);

        try {
            espC.validarAdicionar(ec);
        } catch (Exception e) {
            System.out.println("Especialidad ya registrada: Prueba Exitosa");
        }

        EspecialidadCentro ecBD = espC.validarObtenerId(1);
        assertEquals(ecBD.getFueCaptada(), ec.getFueCaptada());
        assertEquals(ecBD.getEspecialidad().getCodEspecialidad(), ec.getEspecialidad().getCodEspecialidad());

        ec.setFueCaptada(true);
        ec.setCurso(curso.obtenerId(2));

        try {
            espC.validarActualizar(1, ec);
        } catch (Exception e) {
            System.out.println("La duración/escolaridad no se corresponde con el curso: Prueba Exitosa");
        }

        ec.setCurso(curso.obtenerId(1));
        espC.validarActualizar(1, ec);

        ecBD = espC.validarObtenerId(1);
        assertEquals(ecBD.getFueCaptada(), ec.getFueCaptada());
        assertEquals(ecBD.getEspecialidad().getCodEspecialidad(), ec.getEspecialidad().getCodEspecialidad());
        assertEquals(ecBD.getCurso().getTipoCurso(), ec.getCurso().getTipoCurso());

        mostrar(espC.validarListar());

        espC.validarEliminar(1);

        try {
            espC.validarObtenerId(1);
        } catch (Exception e) {
            System.out.println("Especialidad de centro ya eliminada: Prueba Exitosa");
        }
    }

    private void mostrar(List<EspecialidadCentro> lst) {
        System.out.println("\n[ESPECIALIDADES CENTRO]");

        for (int i = 0; i < lst.size(); i++) {
            System.out.println(lst.get(i).toString());
        }
        System.out.println("\n");
    }
}