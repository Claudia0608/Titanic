package ies.tierno.Titanic.ServicioEmergencia;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServicioEmergencia implements IServicioEmergencia {

    //Constantes
    private static final int NUMERO_BOTES = 20;
    private static final String FORMATO_ID_BOTE = "B%02d";
    private static final String COMANDO_JAVA = "java";
    private static final String OPCION_CLASSPATH = "-cp";
    private static final String CLASE_BOTE = "com.example.Bote.Bote";
    private static final String COMA = ",";
    private static final String CLASSPATH = "java.class.path";

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
        for (String id : generarIds()) {
            try {
                Process process = Runtime.getRuntime().exec(new String[]{
                    COMANDO_JAVA, OPCION_CLASSPATH, CLASSPATH, CLASE_BOTE, id
                });

                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line = reader.readLine();
                process.waitFor();

                if (line != null) {
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
}
