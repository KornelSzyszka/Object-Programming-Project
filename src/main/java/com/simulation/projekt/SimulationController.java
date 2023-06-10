package com.simulation.projekt;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SimulationController implements Initializable{
    @FXML
    private TableView<Cell> cellTableView;
    @FXML
    public TableColumn<Cell, Integer> columnFertility;
    @FXML
    public TableColumn<Cell, Integer> columnLifespan;
    @FXML
    public TableColumn<Cell, Double> columnPreferredTemperature;
    @FXML
    public TableColumn<Cell, Double> columnPollutionResistance;
    @FXML
    public TableColumn<Cell, Double> columnMetabolism;
    @FXML
    public TableColumn<Cell, Boolean> columnIsSaprobiont;

    public ObservableList<Cell> list = FXCollections.observableArrayList();

    public void setSpeciesList(ArrayList<Cell> speciesList) {
        System.out.println("the species list: " + speciesList);
        list.addAll(speciesList);
        System.out.println(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*
        columnFertility.setCellValueFactory(new PropertyValueFactory<>("fertility"));
        columnLifespan.setCellValueFactory(new PropertyValueFactory<>("lifeExpectancy"));
        columnPreferredTemperature.setCellValueFactory(new PropertyValueFactory<>("temperatureResistance"));
        columnPollutionResistance.setCellValueFactory(new PropertyValueFactory<>("pollutionResistance"));
        columnMetabolism.setCellValueFactory(new PropertyValueFactory<>("metabolism"));
        columnIsSaprobiont.setCellValueFactory(new PropertyValueFactory<>("isSaprobiont"));
        */
        RunSimulation firstSimulation = new RunSimulation();
        firstSimulation.setParameters();
        firstSimulation.firstPhase();
        firstSimulation.secondPhase();
        firstSimulation.startSimulation();

        // Set cell value factories for IntegerProperty and DoubleProperty columns
        columnFertility.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getFertility()).asObject());
        columnLifespan.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getLifeExpectancy()).asObject());
        columnPreferredTemperature.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getTemperatureResistance()).asObject());
        columnPollutionResistance.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getPollutionResistance()).asObject());
        columnMetabolism.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getMetabolism()).asObject());

        // Set cell value factory for BooleanProperty column
        columnIsSaprobiont.setCellValueFactory(data -> new SimpleBooleanProperty(data.getValue().isSaprobiont()));

        System.out.println(firstSimulation.getList());
        list.addAll(firstSimulation.getList());
        System.out.println(list);
        cellTableView.setItems(list);
        //list.add(newCell);

    }
}
