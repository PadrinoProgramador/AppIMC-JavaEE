package com.tecmilenio.imc.model;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

// Anotación necesaria para que JAX-RS pueda convertir este objeto a XML/JSON
@XmlRootElement
public class RegistroIMC {
    private int id;
    private int usuarioId;
    private double imc;
    private double peso;
    private String categoria;
    private Date fecha;

    public RegistroIMC() {
    }

    public RegistroIMC(int usuarioId, double imc, double peso, String categoria) {
        this.usuarioId = usuarioId;
        this.imc = imc;
        this.peso = peso;
        this.categoria = categoria;
        this.fecha = new Date();
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }

    public double getImc() { return imc; }
    public void setImc(double imc) { this.imc = imc; }

    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
}
