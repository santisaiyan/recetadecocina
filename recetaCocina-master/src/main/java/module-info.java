module com.example.recetacocina {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires javafx.media;

    //para que se puedan abrir las imagenes de esta ruta
    opens com.example.recetacocina.img;
    opens com.example.recetacocina.audio;

    opens com.example.recetacocina to javafx.fxml;
    exports com.example.recetacocina;
}