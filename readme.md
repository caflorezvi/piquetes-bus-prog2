## 🚌 Empresa de transporte terrestre

Una empresa de transporte terrestre maneja una flota de buses que cubre diferentes rutas a nivel nacional. Cada bus tiene características específicas como el tipo de bus (económico, ejecutivo, VIP), servicios ofrecidos (wifi, baño, pantallas, etc), la capacidad de asientos, y el precio base por tiquete. El objetivo de la empresa es implementar un sistema de venta de tiquetes. Cada tiquetes debe tener un id, el cliente, número de asiento, precio total, fecha de compra y un objeto de tipo RutaBus. El objeto RutaBus tiene un id, el bus, la ruta, fecha de salida y los asientos ocupados. El Cliente debe proporcionar su cédula, nombre completo, teléfono y edad. Finalmente, cada ruta tiene un id, origen, destino y número de kilómetros.

El precio total se calcula de la siguiente manera:

- Bus Económico: Precio base: \$15000 + Tarifa por kilómetro: \$150 por kilómetro.
- Bus Ejecutivo: Precio: \$25000 + Tarifa por kilómetro: \$200 por kilómetro.
- Bus VIP: Precio base: \$40000 + Tarifa por kilómetro: \$300 por kilómetro.

Para organizar y estructurar el sistema de venta de tiquetes, la empresa ha decidido utilizar el patrón de diseño Factory (o Abstract Factory) para construir los objetos de tipo bus, y el patrón Builder para gestionar la creación de tiquetes y clientes de manera dinámica.
