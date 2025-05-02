package com.biblioteca.backend.model;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;


public class DetalleFactura {
    private int id;
    private int idFactura;
    @NotBlank(message = "El producto no puede estar vacío")
    private String producto;
    @Min(value = 1, message = "La cantidad debe ser mayor que cero")
    private int cantidad;
    private BigDecimal precioUnitario;
    private int estado;

    // Getters
    public int getId() {
        return id;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public String getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public int getEstado() {
        return estado;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
