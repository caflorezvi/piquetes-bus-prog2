module co.edu.uniquindio.bustiquetes {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;

    opens co.edu.uniquindio.bustiquetes to javafx.fxml;
    exports co.edu.uniquindio.bustiquetes;
    exports co.edu.uniquindio.bustiquetes.controladores;
    exports co.edu.uniquindio.bustiquetes.modelo;
    exports co.edu.uniquindio.bustiquetes.modelo.factory;
    exports co.edu.uniquindio.bustiquetes.dto;
    opens co.edu.uniquindio.bustiquetes.controladores to javafx.fxml;
}