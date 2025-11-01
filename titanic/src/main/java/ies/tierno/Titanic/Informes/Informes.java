package ies.tierno.Titanic.Informes;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class Informes implements IInformes {

    // Constantes de formato
    private static final String ENCABEZADO = """
        # SERVICIO DE EMERGENCIAS

        Ejecución realizada el día %s

        """;

    private static final String BLOQUE_DATOS = """
        ## %s

        - Total Salvados %d
          - Mujeres %d
          - Varones %d
          - Niños %d

        """;

    private static final String FORMATO_ID_BOTE = "B%02d";
    private static final String NOMBRE_ARCHIVO = "Informe_%s.md";
    private static final String FORMATO_FECHA_ARCHIVO = "yyyyMMdd_HHmmss";
    private static final String FORMATO_FECHA_TEXTO = "dd/MM/yyyy 'a las' HH:mm:ss";
    private static final String PERSONAS_TOTAL = "Total";

    @Override
    public void generar(Map<String, int[]> datosBotes, LocalDateTime fecha) {
        int totalMujeres = 0, totalVarones = 0, totalNiños = 0;

        String nombreArchivo = String.format(NOMBRE_ARCHIVO,
                fecha.format(DateTimeFormatter.ofPattern(FORMATO_FECHA_ARCHIVO)));

        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            writer.write(String.format(ENCABEZADO,
                    fecha.format(DateTimeFormatter.ofPattern(FORMATO_FECHA_TEXTO))));

            for (int i = 0; i < 20; i++) {
                String id = String.format(FORMATO_ID_BOTE, i);
                int[] datos = datosBotes.getOrDefault(id, new int[]{0, 0, 0});
                int total = datos[0] + datos[1] + datos[2];
                totalMujeres += datos[0];
                totalVarones += datos[1];
                totalNiños += datos[2];

                writer.write(String.format(BLOQUE_DATOS, id, total, datos[0], datos[1], datos[2]));
            }

            int totalSalvados = totalMujeres + totalVarones + totalNiños;
            writer.write(String.format(BLOQUE_DATOS, PERSONAS_TOTAL,
                    totalSalvados, totalMujeres, totalVarones, totalNiños));

        } catch (IOException ignored) {}
    }
}
