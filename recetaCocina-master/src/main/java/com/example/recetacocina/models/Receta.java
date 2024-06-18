package com.example.recetacocina.models;

import javafx.beans.NamedArg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Receta {
    private String nombre;
    private String tipo;
    private Integer duracion;
    private String dificultad;
}
