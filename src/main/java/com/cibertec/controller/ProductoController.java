package com.cibertec.controller;

import com.cibertec.models.entity.Producto;
import com.cibertec.models.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/views/productos")
public class ProductoController {
    @Autowired
    private ProductoService service;
    @GetMapping("/")
    public String listarProductos(Model model) {
        List<Producto> listadoProductos = service.listarTodos();

        model.addAttribute("titulo", "Lista de Productos");
        model.addAttribute("productos", listadoProductos);

        return "/views/productos/listarProductos";
    }
    @GetMapping("/create")
    public String crear(Model model) {
        Producto producto = new Producto();

        model.addAttribute("titulo", "Formulario: Nuevo Producto");
        model.addAttribute("producto", producto);

        return "/views/productos/frmCrearProducto";
    }
    @PostMapping("/save")
    public String guardar(@ModelAttribute Producto producto, RedirectAttributes attribute) {

        service.guardar(producto);
        System.out.println("Productos guardado con éxito...!!!");
        attribute.addFlashAttribute("success","Producto guardado con éxito...!!!");

        return "redirect:/views/productos/";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") Integer productoId, RedirectAttributes attribute) {
        Producto producto = null;

        if (productoId > 0) {
            producto = service.buscarPorId(productoId);

            if (producto == null) {
                System.out.println("Error: El ID del Producto a ELIMINAR no existe...!!!");
                attribute.addFlashAttribute("error", "Atención: El ID del Producto a ELIMINAR no existe...!!!");
                return "redirect:/views/productos/";
            }
        } else {
            System.out.println("Error: Error con el ID del Videojuego a ELIMINAR...!!!");
            attribute.addFlashAttribute("error", "Atención: Error con el ID del Producto a ELIMINAR...!!!");
            return "redirect:/views/productos/";
        }

        service.eliminar(productoId);
        System.out.println("Registro eliminado con éxito...!!!");
        attribute.addFlashAttribute("warning", "Registro eliminado con éxito...!!!");

        return "redirect:/views/productos/";
    }
    @GetMapping("/find/{id}")
    public String find(@PathVariable("id") Integer idProducto, Model model, RedirectAttributes attribute) {
        Producto producto = null;

        if (idProducto > 0) {
            producto = service.buscarPorId(idProducto);

            if (producto == null) {
                System.out.println("Error: El ID del Videojuego a EDITAR no existe...!!!");
                attribute.addFlashAttribute("error", "Atención: El ID del Videojuego a EDITAR no existe...!!!");
                return "redirect:/views/videojuegos/";
            }
        } else {
            System.out.println("Error: Error con el ID del Videojuego a EDITAR...!!!");
            attribute.addFlashAttribute("error", "Atención: Error con el ID del Videojuego a EDITAR...!!!");
            return "redirect:/views/videojuegos/";
        }

        model.addAttribute("titulo", "Formulario: Editar videojuego");
        model.addAttribute("producto", producto);

        return "/views/productos/frmCrearProducto";
    }
}
