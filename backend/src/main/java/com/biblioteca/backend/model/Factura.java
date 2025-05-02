package com.biblioteca.backend.model;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;


public class Factura {
    private int id;
    @NotBlank(message = "El cliente no puede estar vacío")
    private String cliente;
    private LocalDateTime fecha;
    private int estado;



    // Getters
    public int getId() {
        return id;
    }

    public String getCliente() {
        return cliente;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public int getEstado() {
        return estado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
