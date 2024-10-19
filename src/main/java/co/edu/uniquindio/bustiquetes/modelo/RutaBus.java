package co.edu.uniquindio.bustiquetes.modelo;

import co.edu.uniquindio.bustiquetes.modelo.factory.Bus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class RutaBus {
    private String id;
    private Bus bus;
    private Ruta ruta;
    private LocalDate fechaSalida;
    private List<Integer> asientosOcupados;

    /**
     * Metodo que permite reservar un asiento
     * @param numeroAsiento numero del asiento a reservar
     */
    public void reservarAsiento(int numeroAsiento){
        if(asientosOcupados == null){
            asientosOcupados = new ArrayList<>();
        }
        asientosOcupados.add(numeroAsiento);
    }

    /**
     * Metodo que permite verificar si un asiento esta ocupado
     * @param numeroAsiento numero del asiento a verificar
     * @return true si esta ocupado, false si no lo esta
     */
    public boolean estaOcupado(int numeroAsiento){
        if(asientosOcupados == null){
            return false;
        }
        return asientosOcupados.contains(numeroAsiento);
    }

    /**
     * Metodo que permite calcular el valor del tiquete segun la ruta y el bus
     * @return valor del tiquete
     */
    public float calcularValorTiquete(){
        return bus.calcularPrecio(ruta.getNumKilometros());
    }

    /**
     * Metodo que permite obtener la cantidad de asientos disponibles en el bus
     * @return cantidad de asientos disponibles
     */
    public int obtenerAsientosDisponibles(){
        if(asientosOcupados == null){
            return bus.getCapacidad();
        }
        return bus.getCapacidad() - asientosOcupados.size();
    }

}
