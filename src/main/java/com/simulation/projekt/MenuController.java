package com.simulation.projekt;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

   @FXML
   private Button quit_btn;
    @FXML
    private Button start_btn;
    @FXML
    private Button options_btn;


    @FXML
    public void onActionExit(ActionEvent actionEvent){
        Platform.exit();
    }
    @FXML
    public void onMouseEnteredQ(){
        quit_btn.setStyle("-fx-background-color: grey;");
    }
    @FXML
    public void onMouseExitedQ(){
        quit_btn.setStyle("-fx-background-color: lightgrey;");
    }
    @FXML
    public void onMouseEnteredS(){
        start_btn.setStyle("-fx-background-color: grey;");
    }
    @FXML
    public void onMouseExitedS(){
        start_btn.setStyle("-fx-background-color: lightgrey;");
    }
    @FXML
    public void onMouseEnteredO(){
        options_btn.setStyle("-fx-background-color: grey;");
    }
    @FXML
    public void onMouseExitedO(){
        options_btn.setStyle("-fx-background-color: lightgrey;");
    }

    @FXML
    public void onActionStartSimulation() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sim.fxml"));

        Stage simulation_workspace = new Stage();
        simulation_workspace.setScene(new Scene(fxmlLoader.load(), 803, 536));
        simulation_workspace.setResizable(false);
        simulation_workspace.sizeToScene();
        simulation_workspace.setTitle("Simulation");
        simulation_workspace.show();
    }

}
