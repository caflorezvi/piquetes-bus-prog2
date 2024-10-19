package co.edu.uniquindio.bustiquetes.modelo.factory;

import java.util.List;

public class BusVIP implements Bus {

    private final float precioBase ;

    public BusVIP(){
        precioBase = 40000;
    }

    @Override
    public List<String> getServicios() {
        return List.of("Wifi", "Aire acondicionado", "Baño", "Asientos reclinables", "Televisión");
    }

    @Override
    public int getCapacidad() {
        return 20;
    }

    @Override
    public String getTipo() {
        return "VIP";
    }

    @Override
    public float calcularPrecio(int numKilometros) {
        return precioBase + numKilometros * 300;
    }
}
