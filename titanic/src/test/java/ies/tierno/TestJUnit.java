package ies.tierno;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;


import ies.tierno.Bote.Bote;
import ies.tierno.Titanic.ServicioEmergencia.ServicioEmergencia;


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

//Tests ServicioEmergencia

    @Test
    void generarIdsDevuelve20ConFormatoCorrecto() {
        ServicioEmergencia servicio = new ServicioEmergencia();
        List<String> ids = servicio.generarIds();


        assertEquals(20, ids.size());
        assertEquals("B00", ids.get(0));
        assertEquals("B19", ids.get(19));
        assertTrue(ids.stream().allMatch(id -> id.matches("B\\d{2}")));
    }


    @Test
    void recibirBotesAsignadosGuardaDatosCorrectamente() {
        ServicioEmergencia servicio = new ServicioEmergencia();
        servicio.recibirBotesAsignados("B05", 10, 5, 3);


        Map<String, int[]> datos = servicio.getDatosBotes(); // Método auxiliar para test
        assertTrue(datos.containsKey("B05"));
        assertArrayEquals(new int[]{10, 5, 3}, datos.get("B05"));
    }

}