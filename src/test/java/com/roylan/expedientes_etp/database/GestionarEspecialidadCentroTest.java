package com.roylan.expedientes_etp.database;

import static org.junit.Assert.assertEquals;

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

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GestionarEspecialidadCentroTest {

    @Autowired
    private GestionarEspecialidadCentroImpl espC;

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

    @Before
    public void init() {
        Centro c = new Centro();
        c.setNombCentro("Pepito Tey");
        c.setCodCentro("34060000");
        c.setDireccion("La Risueña s/n");
        c.setTelefono("555555");
        centros.adicionar(c);

        nivel.adicionar(new Nivel("T.M"));

        Rama r = new Rama();
        r.setTipoRama("Informática");
        rama.adicionar(r);

        familia.adicionar(new Familia("Informática y Comunicaciones", rama.obtenerId(1)));

        Especialidad e = new Especialidad();
        e.setNombEspecialidad("Invento");
        e.setCodEspecialidad("301020");
        e.setFamilia(familia.obtenerId(1));
        e.setNivel(nivel.obtenerId(1));
        esps.adicionar(e);

        curso.adicionar(new Curso("Diurno"));

        durac.adicionar(new Duracion(7));

        escola.adicionar(new Escolaridad("9no"));
    }

    @Test
    public void crud() {
        EspecialidadCentro ec = new EspecialidadCentro();
        ec.setEspecialidad(esps.obtenerId(1));
        ec.setFueCaptada(false);
        ec.setCentro(centros.obtenerCodigo("34060000"));
        ec.setCurso(curso.obtenerId(1));
        ec.setEscolaridad(escola.obtenerId(1));
        ec.setDuracion(durac.obtenerId(1));

        espC.adicionar(ec);
        EspecialidadCentro eBD = espC.obtenerId(1);

        assertEquals(esps.obtenerId(1).getNombEspecialidad(), eBD.getEspecialidad().getNombEspecialidad());
        assertEquals(ec.getCurso(), eBD.getCurso());
        assertEquals(ec.getFueCaptada(), eBD.getFueCaptada());
        assertEquals(ec.getDuracion().getTipoDuracion(), eBD.getDuracion().getTipoDuracion());

        ec.setFueCaptada(true);

        espC.update(1, ec);
        eBD = espC.obtenerId(1);

        assertEquals(eBD.getFueCaptada(), ec.getFueCaptada());

        espC.eliminar(1);
        assertEquals(espC.obtenerId(1), null);

        mostrar(espC.listar());
    }

    private void mostrar(List<EspecialidadCentro> lst) {
        System.out.println("\n[ESPECIALIDADES CENTRO]");

        for (int i = 0; i < lst.size(); i++) {
            System.out.println(lst.get(i).toString());
        }
        System.out.println("\n");
    }
}