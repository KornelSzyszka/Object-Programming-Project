package com.simulation.projekt;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ResourceBundle;

public class OptionsController implements Initializable {

    @FXML private Slider temperatureSlider;
    @FXML private Slider pollutionSlider;
    @FXML private Slider dayTimeLenghtSlider;

    @FXML private Label temperatureListener;
    @FXML private Label pollutionListener;
    @FXML private Label dayTimeLengthListener;

    @FXML private Button saveOptions;

    private static int temperature;
    private static double pollution;
    private static int dayTimeLength;

    public int getTemperature(){
        return this.temperature;
    }
    public double getPollution(){
        return this.pollution;
    }
    public int getDayTimeLength(){
        return this.dayTimeLength;
    }

    @FXML
    public void onActionSaveEnvironment(){
        temperature = (int) temperatureSlider.getValue();
        pollution = pollutionSlider.getValue();
        dayTimeLength = (int) dayTimeLenghtSlider.getValue();
        System.out.println("t: " + temperature);
        System.out.println("p: " + pollution);
        System.out.println("time: " + dayTimeLength);
        Stage stage = (Stage)saveOptions.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        temperatureListener.setText(temperatureListener.getText() + "℃");
        temperatureSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                temperature = (int) temperatureSlider.getValue();
                temperatureListener.setText(Integer.toString(temperature) + "℃");
            }
        });

        pollutionSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                pollution = (double) pollutionSlider.getValue();
                pollutionListener.setText(BigDecimal.valueOf(pollution).setScale(2, RoundingMode.HALF_UP).toString());
            }
        });

        dayTimeLenghtSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                dayTimeLength = (int) dayTimeLenghtSlider.getValue();
                dayTimeLengthListener.setText(Integer.toString(dayTimeLength));
            }
        });
    }
}
