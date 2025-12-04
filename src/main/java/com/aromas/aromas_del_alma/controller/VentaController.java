package com.aromas.aromas_del_alma.controller;

import com.aromas.aromas_del_alma.dto.VentaTemporal;
import com.aromas.aromas_del_alma.model.Producto;
import com.aromas.aromas_del_alma.service.ProductoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class VentaController {

    @Autowired
    private ProductoService productoService;

    // Página principal (carrito de venta)
    @GetMapping("/")
    public String mostrarCarrito(HttpSession session, Model model) {
        @SuppressWarnings("unchecked")
        List<VentaTemporal> productosEnCarrito = (List<VentaTemporal>) session.getAttribute("carrito");
        if (productosEnCarrito == null) {
            productosEnCarrito = new ArrayList<>();
            session.setAttribute("carrito", productosEnCarrito);
        }

        double total = productosEnCarrito.stream().mapToDouble(VentaTemporal::getSubtotal).sum();
        model.addAttribute("productosEnCarrito", productosEnCarrito);
        model.addAttribute("totalGeneral", total);
        return "venta";
    }

    // Agregar producto al carrito
    @PostMapping("/agregar-al-carrito")
    public String agregarAlCarrito(
            @RequestParam String codigoBarras,
            @RequestParam(defaultValue = "1") int cantidad,
            HttpSession session,
            Model model) {

        Optional<Producto> productoOpt = productoService.buscarPorCodigoBarras(codigoBarras);
        if (productoOpt.isPresent()) {
            Producto producto = productoOpt.get();
            if (producto.getStock() >= cantidad) {
                VentaTemporal item = new VentaTemporal(producto, cantidad);

                @SuppressWarnings("unchecked")
                List<VentaTemporal> carrito = (List<VentaTemporal>) session.getAttribute("carrito");
                if (carrito == null) {
                    carrito = new ArrayList<>();
                    session.setAttribute("carrito", carrito);
                }
                carrito.add(item);
            } else {
                model.addAttribute("error", "Stock insuficiente para " + producto.getNombre());
            }
        } else {
            model.addAttribute("error", "Producto no encontrado con código: " + codigoBarras);
        }
        return "redirect:/";
    }

    // Eliminar producto del carrito
    @GetMapping("/eliminar-del-carrito")
    public String eliminarDelCarrito(@RequestParam int indice, HttpSession session) {
        @SuppressWarnings("unchecked")
        List<VentaTemporal> carrito = (List<VentaTemporal>) session.getAttribute("carrito");
        if (carrito != null && indice >= 0 && indice < carrito.size()) {
            carrito.remove(indice);
        }
        return "redirect:/";
    }

    // Confirmar venta completa
    @PostMapping("/confirmar-venta")
    public String confirmarVenta(HttpSession session, Model model) {
        @SuppressWarnings("unchecked")
        List<VentaTemporal> carrito = (List<VentaTemporal>) session.getAttribute("carrito");
        if (carrito == null || carrito.isEmpty()) {
            model.addAttribute("error", "No hay productos para vender.");
            return "redirect:/";
        }

        // Verificar stock de todos los productos
        for (VentaTemporal item : carrito) {
            Optional<Producto> productoOpt = productoService.buscarPorCodigoBarras(item.getProducto().getCodigoBarras());
            if (productoOpt.isEmpty() || productoOpt.get().getStock() < item.getCantidad()) {
                model.addAttribute("error", "Stock insuficiente o producto eliminado.");
                return "redirect:/";
            }
        }

        // Realizar las ventas
        for (VentaTemporal item : carrito) {
            productoService.realizarVenta(item.getProducto().getCodigoBarras(), item.getCantidad());
        }

        // Limpiar carrito
        session.removeAttribute("carrito");
        model.addAttribute("mensaje", "¡Venta completada con éxito!");
        return "redirect:/";
    }

    // Formulario para agregar nuevo producto
    @GetMapping("/agregar-producto")
    public String mostrarFormularioAgregar(Model model) {
        model.addAttribute("producto", new Producto());
        return "agregar-producto";
    }

    // Guardar nuevo producto
    @PostMapping("/guardar-producto")
    public String guardarProducto(@ModelAttribute Producto producto, Model model) {
        if (producto.getStock() == null) producto.setStock(0);
        if (producto.getPrecio() == null) producto.setPrecio(0.0);
        productoService.guardarProducto(producto);
        model.addAttribute("mensaje", "¡Producto agregado con éxito!");
        return "agregar-producto";
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