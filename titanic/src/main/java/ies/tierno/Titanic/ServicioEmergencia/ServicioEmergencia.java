package ies.tierno.Titanic.ServicioEmergencia;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ies.tierno.Titanic.Informes.IInformes;
import ies.tierno.Titanic.Informes.Informes;

public class ServicioEmergencia implements IServicioEmergencia {

    //Constantes
    private static final int NUMERO_BOTES = 20;
    private static final String FORMATO_ID_BOTE = "B%02d";
    private static final String COMANDO_JAVA = "java";
    private static final String OPCION_CLASSPATH = "-cp";
    private static final String CLASE_BOTE = "ies.tierno.Bote.Bote";
    private static final String COMA = ",";

    private final Map<String, int[]> datosBotes = new HashMap<>();

    @Override
    public List<String> generarIds() {
        List<String> ids = new ArrayList<>();
        for (int i = 0; i < NUMERO_BOTES; i++) {
            ids.add(String.format(FORMATO_ID_BOTE, i));
        }
        return ids;
    }

    @Override
    public void enviarId() {
        String classpath = System.getProperty("java.class.path");
        for (String id : generarIds()) {
            try {
                Process process = Runtime.getRuntime().exec(new String[]{
                    COMANDO_JAVA, OPCION_CLASSPATH, classpath, CLASE_BOTE, id
                });

                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] partes = line.split(COMA);
                    recibirBotesAsignados(partes[0],
                        Integer.parseInt(partes[1]),
                        Integer.parseInt(partes[2]),
                        Integer.parseInt(partes[3]));
                }
            } catch (Exception ignored) {}
        }
    }

    @Override
    public void recibirBotesAsignados(String id, int mujeres, int varones, int niños) {
        datosBotes.put(id, new int[]{mujeres, varones, niños});
    }

    @Override
    public void generarInforme() {
        IInformes informe = new Informes();
        informe.generar(datosBotes, LocalDateTime.now());
    }

    public Map<String, int[]> getDatosBotes() {
    return datosBotes;
}


    public static void main(String[] args) {
        ServicioEmergencia servicio = new ServicioEmergencia();
        servicio.enviarId();
        servicio.generarInforme();
    }
}
