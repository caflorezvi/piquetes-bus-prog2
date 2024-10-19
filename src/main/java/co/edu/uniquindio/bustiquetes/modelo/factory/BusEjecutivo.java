package co.edu.uniquindio.bustiquetes.modelo.factory;

import java.util.List;

public class BusEjecutivo implements Bus {

    private final float precioBase ;

    public BusEjecutivo(){
        precioBase = 25000;
    }

    @Override
    public List<String> getServicios() {
        return List.of("Wifi", "Aire acondicionado", "Baño");
    }

    @Override
    public int getCapacidad() {
        return 40;
    }

    @Override
    public String getTipo() {
        return "Ejecutivo";
    }

    @Override
    public float calcularPrecio(int numKilometros) {
        return precioBase + numKilometros * 200;
    }

}
