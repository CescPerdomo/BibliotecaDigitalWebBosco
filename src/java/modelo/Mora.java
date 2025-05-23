package modelo;

import java.sql.Timestamp;

public class Mora {
    private int id;
    private int prestamoId;
    private String usuario;
    private int idMaterial;
    private int diasMora;
    private double montoMora;
    private Timestamp fechaCalculo;
    private int ejemplaresPrestados;

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getPrestamoId() { return prestamoId; }
    public void setPrestamoId(int prestamoId) { this.prestamoId = prestamoId; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public int getIdMaterial() { return idMaterial; }
    public void setIdMaterial(int idMaterial) { this.idMaterial = idMaterial; }

    public int getDiasMora() { return diasMora; }
    public void setDiasMora(int diasMora) { this.diasMora = diasMora; }

    public double getMontoMora() { return montoMora; }
    public void setMontoMora(double montoMora) { this.montoMora = montoMora; }

    public Timestamp getFechaCalculo() { return fechaCalculo; }
    public void setFechaCalculo(Timestamp fechaCalculo) { this.fechaCalculo = fechaCalculo; }

    public int getEjemplaresPrestados() { return ejemplaresPrestados; }
    public void setEjemplaresPrestados(int ejemplaresPrestados) { this.ejemplaresPrestados = ejemplaresPrestados; }
}

