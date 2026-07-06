package com.tecmilenio.imc.model;

public class IMCCalculador {
    
    /**
     * Calcula el Índice de Masa Corporal
     * @param peso en kilogramos (debe ser mayor a 0)
     * @param estatura en metros
     * @return el valor del IMC
     */
    public static double calcular(double peso, double estatura) {
        if (peso <= 0) {
            throw new IllegalArgumentException("El peso debe ser mayor a 0");
        }
        if (estatura <= 0) {
            throw new IllegalArgumentException("La estatura debe ser mayor a 0");
        }
        
        // Fórmula: IMC = peso / (estatura * estatura)
        double imc = peso / (estatura * estatura);
        
        // Redondear a 2 decimales
        return Math.round(imc * 100.0) / 100.0;
    }
}
