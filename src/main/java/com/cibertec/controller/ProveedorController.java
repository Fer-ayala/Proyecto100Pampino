package com.cibertec.controller;

import com.cibertec.models.entity.Producto;
import com.cibertec.models.entity.Proveedor;
import com.cibertec.models.service.ProductoService;
import com.cibertec.models.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/views/proveedor")
public class ProveedorController {
    @Autowired
    private ProveedorService service;

    @GetMapping("/")
    public String listarProductos(Model model) {
        List<Proveedor> listadoProductos = service.listarTodos();

        model.addAttribute("titulo", "Lista de Proveedores");
        model.addAttribute("proveedor", listadoProductos);

        return "/views/proveedor/listarProveedores";
    }

    @GetMapping("/create")
    public String crear(Model model) {
        Proveedor producto = new Proveedor();

        model.addAttribute("titulo", "Formulario: Nuevo Proveedor");
        model.addAttribute("proveedor", producto);

        return "/views/proveedor/frmCrearProveedor";
    }

    @PostMapping("/save")
    public String guardar(@ModelAttribute Proveedor producto, RedirectAttributes attribute) {

        service.guardar(producto);
        System.out.println("Proveedor guardado con éxito...!!!");
        attribute.addFlashAttribute("success", "Producto guardado con éxito...!!!");

        return "redirect:/views/proveedor/";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") Integer productoId, RedirectAttributes attribute) {
        Proveedor producto = null;

        if (productoId > 0) {
            producto = service.buscarPorId(productoId);

            if (producto == null) {
                System.out.println("Error: El ID del Producto a ELIMINAR no existe...!!!");
                attribute.addFlashAttribute("error", "Atención: El ID del Proveedor a ELIMINAR no existe...!!!");
                return "redirect:/views/proveedor/";
            }
        } else {
            System.out.println("Error: Error con el ID del Videojuego a ELIMINAR...!!!");
            attribute.addFlashAttribute("error", "Atención: Error con el ID del Producto a ELIMINAR...!!!");
            return "redirect:/views/proveedor/";
        }

        service.eliminar(productoId);
        System.out.println("Registro eliminado con éxito...!!!");
        attribute.addFlashAttribute("warning", "Registro eliminado con éxito...!!!");

        return "redirect:/views/proveedor/";
    }

    @GetMapping("/find/{id}")
    public String find(@PathVariable("id") Integer idProducto, Model model, RedirectAttributes attribute) {
        Proveedor proveedor = null;

        if (idProducto > 0) {
            proveedor = service.buscarPorId(idProducto);

            if (proveedor == null) {
                System.out.println("Error: El ID del Videojuego a EDITAR no existe...!!!");
                attribute.addFlashAttribute("error", "Atención: El ID del Videojuego a EDITAR no existe...!!!");
                return "redirect:/views/proveedor/";
            }
        } else {
            System.out.println("Error: Error con el ID del Videojuego a EDITAR...!!!");
            attribute.addFlashAttribute("error", "Atención: Error con el ID del Videojuego a EDITAR...!!!");
            return "redirect:/views/proveedor/";
        }

        model.addAttribute("titulo", "Formulario: Editar videojuego");
        model.addAttribute("proveedor", proveedor);

        return "/views/proveedor/frmCrearProducto";
    }
}