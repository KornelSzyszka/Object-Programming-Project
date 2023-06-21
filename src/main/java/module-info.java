module com.simulation.projekt {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.simulation.projekt to javafx.fxml;
    exports com.simulation.projekt;
}