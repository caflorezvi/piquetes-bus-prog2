package co.edu.uniquindio.bustiquetes.modelo.factory;

import java.util.List;

public class BusEconomico implements Bus{

    private final float precioBase ;

    public BusEconomico(){
        precioBase = 15000;
    }

    @Override
    public List<String> getServicios() {
        return List.of("Wifi");
    }

    @Override
    public int getCapacidad() {
        return 30;
    }

    @Override
    public String getTipo() {
        return "Económico";
    }

    @Override
    public float calcularPrecio(int numKilometros) {
        return precioBase + numKilometros * 150;
    }
}
