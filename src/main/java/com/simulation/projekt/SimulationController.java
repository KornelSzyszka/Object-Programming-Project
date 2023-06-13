package com.simulation.projekt;

import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SimulationController implements Initializable{

    //fxml implementation
    @FXML private TableView<Cell> cellTableView;
    @FXML public TableColumn<Cell, Integer> columnFertility;
    @FXML public TableColumn<Cell, Integer> columnLifespan;
    @FXML public TableColumn<Cell, Double> columnPreferredTemperature;
    @FXML public TableColumn<Cell, Double> columnPollutionResistance;
    @FXML public TableColumn<Cell, Double> columnMetabolism;
    @FXML public TableColumn<Cell, Boolean> columnIsSaprobiont;
    @FXML private Button rerun_btn;
    @FXML private Button chart_btn;
    @FXML private Button visualise_btn;
    @FXML private Button legend_btn;
    @FXML private Button menu_btn;

    //action methods
    //.setStyle("-fx-background-color: grey;");
    //.setStyle("-fx-background-color: lightgrey;");
    @FXML public void onHoverHighlightRerun() { rerun_btn.setStyle("-fx-background-color: grey;"); }
    @FXML public void onHoverDefaultRerun() { rerun_btn.setStyle("-fx-background-color: lightgrey;"); }
    @FXML public void onHoverHighlightChart() { chart_btn.setStyle("-fx-background-color: grey;"); }
    @FXML public void onHoverDefaultChart() { chart_btn.setStyle("-fx-background-color: lightgrey;"); }
    @FXML public void onHoverHighlightVisualise() { visualise_btn.setStyle("-fx-background-color: grey;"); }
    @FXML public void onHoverDefaultVisualise() { visualise_btn.setStyle("-fx-background-color: lightgrey;"); }
    @FXML public void onHoverHighlightLegend() { legend_btn.setStyle("-fx-background-color: grey;"); }
    @FXML public void onHoverDefaultLegend() { legend_btn.setStyle("-fx-background-color: lightgrey;"); }
    @FXML public void onHoverHighlightMenu() { menu_btn.setStyle("-fx-background-color: grey;"); }
    @FXML public void onHoverDefaultMenu() { menu_btn.setStyle("-fx-background-color: lightgrey;"); }

    @FXML
    public void onActionRerun(){
        cellTableView.getItems().clear();
        initialize(null,null);
    }

    @FXML
    public void onActionReturn(ActionEvent actionEvent) throws IOException {
            Stage stage = (Stage)menu_btn.getScene().getWindow();
            stage.close();
    }

    public ObservableList<Cell> list = FXCollections.observableArrayList();
    public void setSpeciesList(ArrayList<Cell> speciesList) { list.addAll(speciesList); }


    public static RunSimulation run(){
        RunSimulation firstSimulation = new RunSimulation();
        firstSimulation.setParameters();
        firstSimulation.firstPhase();
        firstSimulation.secondPhase();
        firstSimulation.startSimulation();
        return firstSimulation;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        run();
        columnFertility.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getFertility()).asObject());
        columnLifespan.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getLifeExpectancy()).asObject());
        columnPreferredTemperature.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getTemperatureResistance()).asObject());
        columnPollutionResistance.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getPollutionResistance()).asObject());
        columnMetabolism.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getMetabolism()).asObject());
        columnIsSaprobiont.setCellValueFactory(data -> new SimpleBooleanProperty(data.getValue().isSaprobiont()));
        list.addAll(run().getList());
        cellTableView.setItems(list);

    }
}
