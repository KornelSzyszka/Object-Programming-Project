package com.simulation.projekt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("menu.fxml"));
        //FXMLLoader loader = new FXMLLoader(Main.class.getResource("sim.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        //loader.getController();

        stage.setTitle("Symulacja rozwoju komórek");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}