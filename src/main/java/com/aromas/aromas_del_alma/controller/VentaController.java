package com.aromas.aromas_del_alma.controller;

import com.aromas.aromas_del_alma.model.Producto;
import com.aromas.aromas_del_alma.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class VentaController {

    @Autowired
    private ProductoService productoService;

    // Página principal (búsqueda de productos)
    @GetMapping("/")
    public String mostrarPaginaPrincipal() {
        return "venta";
    }
    // Aplicar descuento y recalcular totales
    @PostMapping("/aplicar-descuento")
    public String aplicarDescuento(
            @RequestParam String codigoBarras,
            @RequestParam int cantidad,
            @RequestParam double descuentoPorcentaje,
            Model model) {

        var producto = productoService.buscarPorCodigoBarras(codigoBarras);
        if (producto.isPresent()) {
            Producto p = producto.get();
            double totalOriginal = p.getPrecio() * cantidad;
            double totalConDescuento = totalOriginal;

            if (descuentoPorcentaje > 0 && descuentoPorcentaje <= 100) {
                totalConDescuento = totalOriginal * (1 - descuentoPorcentaje / 100);
            }

            model.addAttribute("producto", p);
            model.addAttribute("cantidad", cantidad);
            model.addAttribute("descuentoPorcentaje", descuentoPorcentaje);
            model.addAttribute("totalOriginal", totalOriginal);
            model.addAttribute("totalConDescuento", totalConDescuento);
        } else {
            model.addAttribute("error", "Producto no encontrado.");
        }
        return "venta";
    }

    // Buscar producto por código de barras
    @PostMapping("/buscar")
    public String buscarProducto(
            @RequestParam String codigoBarras,
            @RequestParam(defaultValue = "1") int cantidad,
            @RequestParam(defaultValue = "0.0") double descuentoPorcentaje,
            Model model) {

        var producto = productoService.buscarPorCodigoBarras(codigoBarras);
        if (producto.isPresent()) {
            Producto p = producto.get();
            double totalOriginal = p.getPrecio() * cantidad;
            double totalConDescuento = totalOriginal;

            // Aplica descuento solo si es válido (0-100)
            if (descuentoPorcentaje > 0 && descuentoPorcentaje <= 100) {
                totalConDescuento = totalOriginal * (1 - descuentoPorcentaje / 100);
            }

            model.addAttribute("producto", p);
            model.addAttribute("cantidad", cantidad);
            model.addAttribute("descuentoPorcentaje", descuentoPorcentaje);
            model.addAttribute("totalOriginal", totalOriginal);
            model.addAttribute("totalConDescuento", totalConDescuento);
        } else {
            model.addAttribute("error", "Producto no encontrado. Por favor, verifica el código.");
        }
        return "venta";
    }

    // Formulario para agregar nuevo producto
    @GetMapping("/agregar-producto")
    public String mostrarFormularioAgregar(Model model) {
        model.addAttribute("producto", new Producto());
        return "agregar-producto"; // ← Nombre del archivo HTML SIN extensión
    }
    // Guardar nuevo producto
    @PostMapping("/guardar-producto")
    public String guardarProducto(@ModelAttribute Producto producto, Model model) {
        // Establecer valores predeterminados si son nulos
        if (producto.getStock() == null) producto.setStock(0);
        if (producto.getPrecio() == null) producto.setPrecio(0.0);
        productoService.guardarProducto(producto);
        model.addAttribute("mensaje", "¡Producto agregado con éxito!");
        return "agregar-producto";
    }

    // Realizar venta
    @PostMapping("/vender")
    public String realizarVenta(
            @RequestParam String codigoBarras,
            @RequestParam(defaultValue = "1") int cantidad,
            Model model) {

        boolean exito = productoService.realizarVenta(codigoBarras, cantidad);
        if (exito) {
            model.addAttribute("mensaje", "¡Venta realizada con éxito!");
        } else {
            model.addAttribute("error", "Stock insuficiente o producto no encontrado.");
        }
        return "redirect:/";
    }
    // Mostrar formulario de edición
    @GetMapping("/editar-producto")
    public String mostrarFormularioEdicion(@RequestParam String codigoBarras, Model model) {
        var producto = productoService.buscarPorCodigoBarras(codigoBarras);
        if (producto.isPresent()) {
            model.addAttribute("producto", producto.get());
            return "editar-producto";
        } else {
            model.addAttribute("error", "Producto no encontrado.");
            return "venta";
        }
    }

    // Guardar cambios del producto
    @PostMapping("/actualizar-producto")
    public String actualizarProducto(@ModelAttribute Producto producto, Model model) {
        productoService.guardarProducto(producto);
        model.addAttribute("mensaje", "¡Producto actualizado con éxito!");
        return "editar-producto";
    }
}