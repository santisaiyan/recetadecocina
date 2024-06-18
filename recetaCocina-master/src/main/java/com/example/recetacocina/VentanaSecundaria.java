package com.example.recetacocina;

import com.example.recetacocina.models.Receta;
import javafx.event.*;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class VentanaSecundaria implements Initializable
{
    @javafx.fxml.FXML
    private Label labelInfo;
    @javafx.fxml.FXML
    private Button boonVolver;
    @javafx.fxml.FXML
    private Spinner<Double> spinner;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void volver(ActionEvent actionEvent) {
        HelloApplication.loadFXML("hello-view.fxml");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Receta r = Session.getRecetaActual();
        labelInfo.setText(r.toString());

        spinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0,10,5,0.25));
    }
}