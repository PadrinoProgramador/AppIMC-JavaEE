package com.tecmilenio.imc.model;

public enum CategoriaIMC {
    BAJO_PESO("Bajo peso"),
    PESO_NORMAL("Peso normal"),
    SOBREPESO("Sobrepeso"),
    OBESIDAD("Obesidad");

    private final String descripcion;

    CategoriaIMC(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static CategoriaIMC obtenerCategoria(double imc) {
        if (imc < 18.5) {
            return BAJO_PESO;
        } else if (imc >= 18.5 && imc <= 24.9) {
            return PESO_NORMAL;
        } else if (imc >= 25.0 && imc <= 29.9) {
            return SOBREPESO;
        } else {
            return OBESIDAD;
        }
    }
}
