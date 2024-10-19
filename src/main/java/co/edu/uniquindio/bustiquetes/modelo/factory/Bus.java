package co.edu.uniquindio.bustiquetes.modelo.factory;

import java.util.List;

/**
 * Interfaz que define el comportamiento de un bus
 */
public interface Bus {

    List<String> getServicios();
    int getCapacidad();
    String getTipo();
    float calcularPrecio(int numKilometros);

}
