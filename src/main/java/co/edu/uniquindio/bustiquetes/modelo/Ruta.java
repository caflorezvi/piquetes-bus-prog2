package co.edu.uniquindio.bustiquetes.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class Ruta {

    private String id;
    private String origen;
    private String destino;
    private int numKilometros;
}
