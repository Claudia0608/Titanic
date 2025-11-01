package ies.tierno.Titanic.Informes;

import java.time.LocalDateTime;
import java.util.Map;

public interface IInformes {
    void generar(Map<String, int[]> datosBotes, LocalDateTime fecha);
}

