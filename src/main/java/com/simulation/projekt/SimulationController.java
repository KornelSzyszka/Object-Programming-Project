package com.simulation.projekt;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
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
    @FXML public TableColumn<Cell, Integer> columnQuantity;

    @FXML private Button rerun_btn;
    @FXML private Button menu_btn;

    @FXML public void onHoverHighlightRerun() { rerun_btn.setStyle("-fx-background-color: grey;"); }
    @FXML public void onHoverDefaultRerun() { rerun_btn.setStyle("-fx-background-color: lightgrey;"); }
    @FXML public void onHoverHighlightMenu() { menu_btn.setStyle("-fx-background-color: grey;"); }
    @FXML public void onHoverDefaultMenu() { menu_btn.setStyle("-fx-background-color: lightgrey;"); }
    @FXML
    public void onActionRerun(){
        cellTableView.getItems().clear();
        initialize(null,null);
    }
    @FXML
    public void onActionReturn() throws IOException {
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
        columnQuantity.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Cell, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Cell, Integer> param) {
                if (param.getValue().speciesAmount > 0){
                    columnFertility.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getFertility()).asObject());
                    columnLifespan.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getLifeExpectancy()).asObject());
                    columnPreferredTemperature.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getTemperatureResistance()).asObject());
                    columnPollutionResistance.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getPollutionResistance()).asObject());
                    columnMetabolism.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getMetabolism()).asObject());
                    columnIsSaprobiont.setCellValueFactory(data -> new SimpleBooleanProperty(data.getValue().isSaprobiont()));
                    return new SimpleIntegerProperty(param.getValue().getSpeciesAmount()).asObject();
                }
                return null;
            }
        });
        list.addAll(run().getList().stream().filter(x -> x.getSpeciesAmount() > 0).toList());
        cellTableView.setItems(list);

    }
}
