package co.edu.uniquindio.bustiquetes.modelo;

import co.edu.uniquindio.bustiquetes.dto.Respuesta;
import co.edu.uniquindio.bustiquetes.modelo.factory.Bus;
import co.edu.uniquindio.bustiquetes.modelo.factory.BusEconomico;
import co.edu.uniquindio.bustiquetes.modelo.factory.BusEjecutivo;
import co.edu.uniquindio.bustiquetes.modelo.factory.BusVIP;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class EmpresaTransporte {

    private final List<Tiquete> tiquetes;
    private final List<Bus> buses;
    private final List<Ruta> rutas;
    private final List<RutaBus> rutasBuses;

    public EmpresaTransporte() {
        this.tiquetes = new ArrayList<>();
        this.buses = new ArrayList<>();
        this.rutas = new ArrayList<>();
        this.rutasBuses = new ArrayList<>();

        try {
            inicializarDatos();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo que permite crear un bus segun el tipo (Económico, Ejecutivo, VIP). BusFactory
     * @param tipoBus
     * @return
     * @throws Exception
     */
    public Bus crearBus(String tipoBus)throws Exception{

        Bus bus = switch (tipoBus){
            case "Económico" -> new BusEconomico();
            case "Ejecutivo" -> new BusEjecutivo();
            case "VIP" -> new BusVIP();
            default -> throw new Exception("Tipo de bus no valido");
        };

        buses.add(bus);
        return bus;
    }

    /**
     * Metodo que permite inicializar los datos de prueba de la empresa de transporte
     * @throws Exception
     */
    public void inicializarDatos() throws Exception{

        //Se crean las rutas
        Ruta ruta1 = new Ruta(UUID.randomUUID().toString(), "Cali", "Bogota", 500);
        Ruta ruta2 = new Ruta(UUID.randomUUID().toString(),"Armenia", "Medellin", 300);
        Ruta ruta3 = new Ruta(UUID.randomUUID().toString(),"Cali", "Cartagena", 700);

        rutas.add(ruta1);
        rutas.add(ruta2);
        rutas.add(ruta3);

        //Se crean los buses
        Bus bus1 = crearBus("Económico");
        Bus bus2 = crearBus("Ejecutivo");
        Bus bus3 = crearBus("VIP");

        buses.add(bus1);
        buses.add(bus2);
        buses.add(bus3);

        //Se crean las rutas buses con las rutas y buses creados anteriormente
        RutaBus rutaBus1 = RutaBus.builder()
                .id(UUID.randomUUID().toString())
                .ruta(ruta1)
                .bus(bus1)
                .fechaSalida(LocalDate.of(2024, 10, 11))
                .build();

        RutaBus rutaBus2 = RutaBus.builder()
                .id(UUID.randomUUID().toString())
                .ruta(ruta2)
                .bus(bus2)
                .fechaSalida(LocalDate.of(2024, 10, 20))
                .build();

        RutaBus rutaBus3 = RutaBus.builder()
                .id(UUID.randomUUID().toString())
                .ruta(ruta3)
                .bus(bus3)
                .fechaSalida(LocalDate.of(2024, 10, 12))
                .build();

        rutasBuses.add(rutaBus1);
        rutasBuses.add(rutaBus2);
        rutasBuses.add(rutaBus3);

    }

    /**
     * Metodo que permite buscar los buses disponibles segun el origen, destino y fecha de salida
     * @param origen
     * @param destino
     * @param fecha
     * @return
     */
    public List<Respuesta> buscarBusesDisponibles(String origen, String destino, LocalDate fecha){
        List<Respuesta> respuestas = new ArrayList<>();

        //Se recorren todas las rutas buses
        for (RutaBus rutaBus : rutasBuses) {
            //Si la ruta bus tiene el mismo origen, destino y fecha de salida
            if(rutaBus.getRuta().getOrigen().equals(origen) &&
                    rutaBus.getRuta().getDestino().equals(destino) &&
                    rutaBus.getFechaSalida().isEqual(fecha)){

                //Se agrega la respuesta con los datos solicitados a la lista
                respuestas.add(
                        new Respuesta(
                                rutaBus.getId(),
                                rutaBus.obtenerAsientosDisponibles(),
                                rutaBus.getBus().getTipo(),
                                rutaBus.getBus().getServicios(),
                                rutaBus.calcularValorTiquete()
                        )
                );
            }
        }

        return respuestas;

    }

    /**
     * Metodo que permite realizar la compra de un tiquete dados los datos del cliente, la ruta bus y el asiento
     * @param cedula
     * @param nombreCompleto
     * @param telefono
     * @param edad
     * @param idRutaBus
     * @param numeroAsiento
     * @return
     */
    public Tiquete realizarCompra(String cedula, String nombreCompleto, String telefono, int edad, String idRutaBus, int numeroAsiento) throws Exception{

        //Se busca la ruta bus por su id
        RutaBus rutaBus = buscarRutaBusPorId(idRutaBus);

        if(rutaBus == null){
            throw new Exception("No se encontro la ruta bus");
        }

        //Se verifica si el asiento esta ocupado
        boolean estaOcupado = rutaBus.estaOcupado(numeroAsiento);

        if(estaOcupado){
            throw new Exception("El asiento ya esta ocupado");
        }

        //Se crea el cliente
        Cliente cliente = Cliente.builder()
                .cedula(cedula)
                .nombreCompleto(nombreCompleto)
                .telefono(telefono)
                .edad(edad)
                .build();

        //Se crea el tiquete, se le asigna un id, se calcula el precio automaticamente
        Tiquete tiquete = Tiquete.builder()
                .id(UUID.randomUUID().toString())
                .cliente(cliente)
                .numeroAsiento(numeroAsiento)
                .precio( rutaBus.calcularValorTiquete() )
                .rutaBus(rutaBus)
                .build();

        //Se reserva el asiento en la ruta bus
        rutaBus.reservarAsiento(numeroAsiento);
        tiquetes.add(tiquete);

        return tiquete;
    }

    /**
     * Metodo que permite buscar una ruta bus por su id
     * @param idRutaBus id de la ruta bus
     * @return RutaBus
     */
    public RutaBus buscarRutaBusPorId(String idRutaBus){
        for (RutaBus rutaBus : rutasBuses) {
            if(rutaBus.getId().equals(idRutaBus)){
                return rutaBus;
            }
        }
        return null;
    }

    public List<RutaBus> listarRutasBuses(){
        return rutasBuses;
    }

}
