package com.example.recetacocina;

import com.example.recetacocina.models.Receta;
import lombok.Data;


public class Session {
    private static Receta recetaActual = null;

    public static Receta getRecetaActual() {
        return recetaActual;
    }

    public static void setRecetaActual(Receta recetaActual) {
        Session.recetaActual = recetaActual;
    }
}
