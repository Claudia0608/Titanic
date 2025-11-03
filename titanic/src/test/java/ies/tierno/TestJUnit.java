package ies.tierno;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;


import ies.tierno.Bote.Bote;


public class TestJUnit {


    //Tests para Bote


   @Test
    void contarPasajerosGeneraTotalValido() {
        Bote bote = new Bote("B01");
        bote.contarPasajeros();
        String[] partes = bote.obtenerResultado().split(",");


        int mujeres = Integer.parseInt(partes[1]);
        int varones = Integer.parseInt(partes[2]);
        int niños = Integer.parseInt(partes[3]);
        int total = mujeres + varones + niños;


        assertTrue(total >= 1 && total <= 100);
    }

    @Test
    void formatoResultadoCorrecto() {
        Bote bote = new Bote("B02");
        bote.contarPasajeros();
        String resultado = bote.obtenerResultado();


        assertTrue(resultado.matches("B02,\\d+,\\d+,\\d+"));
    }


    @Test
    void idSeMantieneEnResultado() {
        Bote bote = new Bote("B03");
        bote.contarPasajeros();
        String resultado = bote.obtenerResultado();


        assertTrue(resultado.startsWith("B03,"));
    }


}