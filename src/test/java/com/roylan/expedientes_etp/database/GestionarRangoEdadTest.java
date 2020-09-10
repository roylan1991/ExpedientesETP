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
public class GestionarRangoEdadTest {

    @Autowired
    private GestionarRangoEdadImpl rangoEdad;

    @Autowired
    private GestionarCurso curso;

    @Before
    public void init() {
        curso.adicionar(new Curso("Diurno"));
        curso.adicionar(new Curso("CPT"));
    }

    @Test
    public void crud() {
        RangoEdad ran = new RangoEdad(new Rango(curso.obtenerId(1)), new Rango(curso.obtenerId(2)));

        rangoEdad.adicionar(ran);
        RangoEdad rBD = rangoEdad.obtenerId(1);

        assertEquals(String.valueOf(rBD.getDiurno().getTotalMenos15()), String.valueOf(0));
        assertEquals(String.valueOf(rBD.getCpt().getTotalEntre15_16()), String.valueOf(0));

        ran.setDiurno(new Rango(20, 10, 40, 30, 4, 2, curso.obtenerId(1)));
        ran.setCpt(new Rango(10, 5, 20, 15, 2, 1, curso.obtenerId(2)));

        Centro c = new Centro();
        c.setNombCentro("Pepito Tey");
        c.setCodCentro("34060000");
        c.setDireccion("La Risueña s/n");
        c.setTelefono("555555");
        ran.setCentro(c);

        rangoEdad.update(1, ran);
        rBD = rangoEdad.obtenerId(1);

        assertEquals(String.valueOf(rBD.getDiurno().getTotalMenos15()), String.valueOf(ran.getDiurno().getTotalMenos15()));
        assertEquals(String.valueOf(rBD.getCpt().getHembrasMayores16()), String.valueOf(ran.getCpt().getHembrasMayores16()));

        rangoEdad.eliminar(1);
        assertEquals(rangoEdad.obtenerId(1), null);

        mostrar(rangoEdad.listar());
    }

    private void mostrar(List<RangoEdad> lst) {
        System.out.println("\n[RANGO DE EDADES]");

        for (int i = 0; i < lst.size(); i++) {
            System.out.println(lst.get(i).toString());
        }
        System.out.println("\n");
    }
}