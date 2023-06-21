package com.simulation.projekt;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

public class MenuController {
   @FXML
   private Button quit_btn;
    @FXML
    private Button start_btn;
    @FXML
    private Button options_btn;

    @FXML
    public void onActionExit(){
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
    public void onActionOptions() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("options.fxml"));
        Stage options_window = new Stage();
        options_window.setResizable(false);
        options_window.initStyle(StageStyle.UTILITY);
        options_window.setScene(new Scene(fxmlLoader.load(), 600, 400));
        options_window.setTitle("Options");
        options_window.show();
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
