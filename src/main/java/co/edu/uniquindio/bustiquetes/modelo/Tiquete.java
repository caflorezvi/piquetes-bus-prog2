package co.edu.uniquindio.bustiquetes.modelo;

import lombok.*;

@AllArgsConstructor
@Builder
@Setter
@Getter
public class Tiquete {
    private String id;
    private Cliente cliente;
    private int numeroAsiento;
    private float precio;
    private RutaBus rutaBus;

    @Override
    public String toString() {
        return  "ID: " + id + '\n' +
                "Nombre ciente: " + cliente.getNombreCompleto() + '\n' +
                "Numero del asiento: " + numeroAsiento + '\n' +
                "Precio: $" + precio + '\n' +
                "Ruta Bus: " + rutaBus.getRuta().getOrigen() + " a " + rutaBus.getRuta().getDestino() + '\n' +
                "Fecha salida: " + rutaBus.getFechaSalida() + '\n';
    }
}
