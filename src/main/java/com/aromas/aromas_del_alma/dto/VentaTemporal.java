package com.aromas.aromas_del_alma.dto;

import com.aromas.aromas_del_alma.model.Producto;

public class VentaTemporal {
    private Producto producto;
    private int cantidad;
    private double descuentoPorcentaje; // 0.0 = sin descuento

    public VentaTemporal(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.descuentoPorcentaje = 0.0;
    }

    // Getters y Setters
    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getDescuentoPorcentaje() { return descuentoPorcentaje; }
    public void setDescuentoPorcentaje(double descuentoPorcentaje) { this.descuentoPorcentaje = descuentoPorcentaje; }

    public double getSubtotal() {
        double total = producto.getPrecio() * cantidad;
        if (descuentoPorcentaje > 0) {
            total = total * (1 - descuentoPorcentaje / 100);
        }
        return total;
    }
}