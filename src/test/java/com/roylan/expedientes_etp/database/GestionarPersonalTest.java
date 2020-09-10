package com.roylan.expedientes_etp.database;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.roylan.expedientes_etp.database.entities.Personal;
import com.roylan.expedientes_etp.database.services.GestionarPersonalImpl;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GestionarPersonalTest {

    @Autowired
    private GestionarPersonalImpl personal;

    @Test
    public void crud() {
        Personal p = new Personal();
        p.setTotal(100);
        p.setHembras(80);

        personal.adicionar(p);
        Personal eaBD = personal.obtenerId(1);

        assertEquals(String.valueOf(eaBD.getTotal()), String.valueOf(p.getTotal()));
        assertEquals(String.valueOf(eaBD.getHembras()), String.valueOf(p.getHembras()));

        p.setTotal(200);
        p.setHembras(160);

        personal.update(1, p);
        eaBD = personal.obtenerId(1);

        assertEquals(String.valueOf(eaBD.getTotal()), String.valueOf(p.getTotal()));
        assertEquals(String.valueOf(eaBD.getHembras()), String.valueOf(p.getHembras()));

        personal.eliminar(1);
        assertEquals(personal.obtenerId(1), null);

        mostrar(personal.listar());
    }

    private void mostrar(List<Personal> lst) {
        System.out.println("\n[PERSONAL]");

        for (int i = 0; i < lst.size(); i++) {
            System.out.println(lst.get(i).toString());
        }
        System.out.println("\n");
    }
}