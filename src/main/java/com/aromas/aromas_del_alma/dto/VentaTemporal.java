package com.aromas.aromas_del_alma.dto;

import com.aromas.aromas_del_alma.model.Producto;

public class VentaTemporal {
    private Producto producto;
    private int cantidad;

    public VentaTemporal(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // Calcula el subtotal (precio * cantidad)
    public double getSubtotal() {
        return producto.getPrecio() * cantidad;
    }
}