package ies.tierno.Titanic.ServicioEmergencia;

import java.util.List;

public interface IServicioEmergencia {
    List<String> generarIds();
    void enviarId();
    void recibirBotesAsignados(String id, int mujeres, int varones, int niños);
}
