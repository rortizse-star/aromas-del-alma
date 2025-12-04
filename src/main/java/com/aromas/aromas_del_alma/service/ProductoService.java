package com.aromas.aromas_del_alma.service;

import com.aromas.aromas_del_alma.model.Producto;
import com.aromas.aromas_del_alma.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // Busca un producto por su código de barras
    public Optional<Producto> buscarPorCodigoBarras(String codigoBarras) {
        return productoRepository.findByCodigoBarras(codigoBarras);
    }

    // Realiza una venta: reduce el stock
    @Transactional
    public boolean realizarVenta(String codigoBarras, int cantidad) {
        Optional<Producto> productoOpt = productoRepository.findByCodigoBarras(codigoBarras);
        if (productoOpt.isPresent()) {
            Producto producto = productoOpt.get();
            if (producto.getStock() >= cantidad) {
                producto.setStock(producto.getStock() - cantidad);
                productoRepository.save(producto);
                return true;
            }
        }
        return false;
    }

    // Guarda o actualiza un producto
    public void guardarProducto(Producto producto) {
        productoRepository.save(producto);
    }
}