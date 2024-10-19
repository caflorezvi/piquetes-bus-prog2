package co.edu.uniquindio.bustiquetes.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class Cliente {
    private String cedula;
    private String nombreCompleto;
    private String telefono;
    private int edad;
}
