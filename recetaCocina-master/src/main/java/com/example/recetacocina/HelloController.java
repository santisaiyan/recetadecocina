package com.example.recetacocina;

import com.example.recetacocina.models.Receta;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Slider sliderDuracion;
    @FXML
    private ComboBox<String> comboDificultad;
    @FXML
    private Button btnAniadir;
    @FXML
    private TableColumn<Receta,String> cReceta;
    @FXML
    private TableColumn<Receta,String> cDuracion;
    @FXML
    private TableColumn<Receta,String> cDificultad;
    @FXML
    private TableColumn<Receta,String> cTipo;
    @FXML
    private Label info;
    @FXML
    private Label lblDuracion;
    @FXML
    private ListView<String> listTipo;
    @FXML
    private TableView<Receta> listRecetas;
    @FXML
    private TextField txtNombre;
    @FXML
    private MenuItem menuSalir;
    @FXML
    private MenuItem menuAcercaDe;
    @FXML
    private ComboBox comboRecetas;
    @FXML
    private ToggleGroup dificultad;
    @FXML
    private ToggleButton toguelDificultad;
    @FXML
    private ImageView carita;

    private MediaPlayer mediaPlayer;

    @FXML
    protected void onHelloButtonClick() {


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Media sonido = new Media(HelloApplication.class.getClassLoader().getResource("com/example/recetacocina/audio/audio.wav").toExternalForm());
        MediaPlayer mediaplayer = new MediaPlayer(sonido);
//        comboDificultad.getItems().add("Fácil");
//        comboDificultad.getItems().add("Medio");
//        comboDificultad.getItems().add("Dificil");
//        comboDificultad.getItems().addAll("básica","hard","pro");

        //nunca se instancia el observable list
        ObservableList<String> datos = FXCollections.observableArrayList();

        datos.addAll("Fácil","Dificil","Moderada");
        comboDificultad.setItems(datos);
        comboDificultad.getSelectionModel().selectFirst();
        comboDificultad.getSelectionModel().selectFirst();

        sliderDuracion.setValue(60);

        lblDuracion.setText(Math.round(sliderDuracion.getValue())+" min");

        listTipo.getItems().addAll("Desayuno","Segundo desayuno","Almuerzo","SobreAlmuerzo","Merienda","Cena","Recena","PostCena");
        listTipo.getSelectionModel().select(0);

        //listener al evento del slider duracion, (cuando cambia el slider se ejecuta este listener)
        sliderDuracion.valueProperty().addListener((observableValue, number, t1) -> {
            lblDuracion.setText(t1.intValue()+" min");
        });

//        txtNombre.textProperty().addListener((observable,vold,vnew) -> {
//            info.setText("antiguo: "+vold+" nuevo: "+vnew);
//        });

        listRecetas.getSelectionModel().selectedItemProperty().addListener((observableValue, vOld, vNew) -> {
            info.setText(vNew.toString());
            txtNombre.setText(vNew.getNombre());
            sliderDuracion.setValue(vNew.getDuracion());
            listTipo.getSelectionModel().select( vNew.getTipo());
            comboDificultad.getSelectionModel().select(vNew.getDificultad());
        });

        //tabla recetas
        cReceta.setCellValueFactory( (fila) -> {
            String nombre = fila.getValue().getNombre().toLowerCase();
            return new SimpleStringProperty(nombre);
        });

        cDuracion.setCellValueFactory( (fila) -> {
            Integer duracion = fila.getValue().getDuracion();
            return new SimpleStringProperty(duracion.toString());
        });

        cDificultad.setCellValueFactory((fila) -> {
            String dificultad = fila.getValue().getDificultad();
            return new SimpleStringProperty(dificultad);
        });

        cTipo.setCellValueFactory((fila) -> {
            String tipo = fila.getValue().getTipo();
            return new SimpleStringProperty(tipo);
        });

        listRecetas.getItems().add(new Receta("Tacos de carne asada", "Almuerzo", 45, "Fácil"));
        listRecetas.getItems().add(new Receta("Huevos revueltos con tocino", "Desayuno", 15, "Moderada"));
        listRecetas.getItems().add(new Receta("Sándwich de jamón y queso", "Merienda", 10, "Fácil"));
        listRecetas.getItems().add(new Receta("Pollo a la parrilla con verduras", "Almuerzo", 60, "Moderada"));
        listRecetas.getItems().add(new Receta("Avena con frutas", "Desayuno", 20, "Fácil"));
        listRecetas.getItems().add(new Receta("Ensalada de atún", "Almuerzo", 30, "Moderada"));
        listRecetas.getItems().add(new Receta("Pizza casera", "Cena", 35, "Moderada"));
        listRecetas.getItems().add(new Receta("Batido de frutas", "Merienda", 5, "Fácil"));
        listRecetas.getItems().add(new Receta("Sopa de pollo casera", "Cena", 40, "Difícil"));
        listRecetas.getItems().add(new Receta("Pancakes con sirope de arce", "Desayuno", 25, "Moderada"));

        comboDificultad.valueProperty().addListener(
                (observableValue, s, t1) -> {
                    String imagen = "neutral.png";
                    //t1 es el valor actual   s valor antiguo
                    if(t1 == "Fácil") imagen = "feliz.png";
                    else if (t1 == "Dificil") imagen = "muerto.png";

                    carita.setImage(new Image("com/example/recetacocina/img/"+imagen));
                    mediaplayer.seek(new Duration(0));
                    mediaplayer.play();

        });
        comboRecetas.setConverter(new StringConverter<Receta>() {
            @Override
            public String toString(Receta receta) {
                if(receta!=null) {
                    return receta.getNombre();
                }
                else{
                    return null;
                }
            }

            @Override
            public Receta fromString(String s) {
                return null;
            }
        });

        comboRecetas.getItems().addAll( listRecetas.getItems());
//        System.out.println(toguelDificultad.getUserData());
    }

//    @FXML
//    public void actualizarDuracion(Event event) {
//        lblDuracion.setText(Math.round(sliderDuracion.getValue())+" min");
//    }

    public void insertarReceta(ActionEvent actionEvent){
        if(!txtNombre.getText().isEmpty()){
            Receta receta = new Receta();
            receta.setNombre(txtNombre.getText());
            receta.setTipo(listTipo.getSelectionModel().getSelectedItem());
            receta.setDuracion((int) sliderDuracion.getValue());
            receta.setDificultad(comboDificultad.getSelectionModel().getSelectedItem());
            listRecetas.getItems().add(receta);
            info.setText("Insertada receta");
        }
    }




    @Deprecated
    public void salir(ActionEvent actionEvent) {
        System.exit(0);
    }

    @FXML
    public void acercaDe(ActionEvent actionEvent) {
        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("El creador");
        alert.setContentText("Creado por Joaquin Romero");
        alert.showAndWait();
    }

    @FXML
    public void mostrarRecetas(ActionEvent actionEvent) {
        System.out.println( comboRecetas.getSelectionModel().getSelectedItem() );
//        listRecetas.getSelectionModel().select(comboRecetas.getSelectionModel().getSelectedItem());

        Session.setRecetaActual((Receta) comboRecetas.getSelectionModel().getSelectedItem());
        HelloApplication.loadFXML("VentanaSecundaria.fxml");


    }
}