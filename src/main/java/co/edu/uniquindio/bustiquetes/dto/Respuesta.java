package co.edu.uniquindio.bustiquetes.dto;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class Respuesta {
    private String id;
    private int asientosDisponibles;
    private String tipoBus;
    private List<String> servicios;
    private float precio;
}
