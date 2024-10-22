package com.cibertec.controller;

import java.util.List;

import com.cibertec.models.entity.Producto;
import com.cibertec.models.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.models.entity.Videojuego;
import com.cibertec.models.service.IVideojuegoService;


@Controller
@RequestMapping("/views/videojuegos")
public class VideojuegoController {
	@Autowired
	private IVideojuegoService videojuegoService;
	@Autowired
	private ProductoService productoService;

	@GetMapping("/")
	public String listarVideojuegos(Model model) {
		List<Videojuego> listadoVideojuegos = videojuegoService.listarTodos();
		model.addAttribute("titulo", "Lista de Historial");
		model.addAttribute("videojuegos", listadoVideojuegos);

		return "/views/videojuegos/listar";
	}

	@GetMapping("/create")
	public String crear(Model model) {
		Videojuego videojuego = new Videojuego();

		model.addAttribute("titulo", "Formulario: Nuevo videojuego");
		model.addAttribute("videojuego", videojuego);
		List<Producto> listadoProductos = productoService.listarTodos();
		model.addAttribute("productos", listadoProductos);
		return "/views/videojuegos/frmCrear";
	}
	@GetMapping("/find")
	public String buscar(Model model) {
		model.addAttribute("titulo", "Buscar producto para actualizar stock");
		List<Producto> listadoProductos = productoService.listarTodos();
		model.addAttribute("productos", listadoProductos);
		return "/views/videojuegos/frmBuscar";
	}

	@PostMapping("/save")
	public String guardar(@ModelAttribute Videojuego videojuego, RedirectAttributes attribute) {

		videojuegoService.guardar(videojuego);
		System.out.println("Videojuego guardado con éxito...!!!");
		attribute.addFlashAttribute("success","Videojuego guardado con éxito...!!!");

		return "redirect:/views/videojuegos/";
	}

	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") Integer idVideojuego, Model model, RedirectAttributes attribute) {
		Videojuego videojuego = null;

		if (idVideojuego > 0) {
			videojuego = videojuegoService.buscarPorId(idVideojuego);

			if (videojuego == null) {
				System.out.println("Error: El ID del Videojuego a EDITAR no existe...!!!");
				attribute.addFlashAttribute("error", "Atención: El ID del Videojuego a EDITAR no existe...!!!");
				return "redirect:/views/videojuegos/";
			}
		} else {
			System.out.println("Error: Error con el ID del Videojuego a EDITAR...!!!");
			attribute.addFlashAttribute("error", "Atención: Error con el ID del Videojuego a EDITAR...!!!");
			return "redirect:/views/videojuegos/";
		}

		model.addAttribute("titulo", "Formulario: Editar datos");
		model.addAttribute("videojuego", videojuego);

		return "/views/videojuegos/frmCrear";
	}
	@GetMapping("/cargar")
	public String cargar(@RequestParam("productoId") Integer idProducto, Model model, RedirectAttributes attribute) {
		Videojuego videojuego = new Videojuego();
		Producto producto;
		if (idProducto > 0) {
			producto = productoService.buscarPorId(idProducto);
			videojuego.setCodigo(producto.getCodigo());
			videojuego.setNombre(producto.getNombre());
			videojuego.setPrecio(producto.getPrecio());
			//videojuego.setStock();

			if (videojuego == null) {
				System.out.println("Error: El ID del Videojuego a EDITAR no existe...!!!");
				attribute.addFlashAttribute("error", "Atención: El ID del Videojuego a EDITAR no existe...!!!");
				return "redirect:/views/videojuegos/";
			}
		} else {
			System.out.println("Error: Error con el ID del Videojuego a EDITAR...!!!");
			attribute.addFlashAttribute("error", "Atención: Error con el ID del Videojuego a EDITAR...!!!");
			return "redirect:/views/videojuegos/";
		}

		model.addAttribute("titulo", "Formulario: Agregar nuevo");
		model.addAttribute("videojuego", videojuego);

		return "/views/videojuegos/frmCrear";
	}

	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") Integer idVideojuego, RedirectAttributes attribute) {
		Videojuego videojuego = null;

		if (idVideojuego > 0) {
			videojuego = videojuegoService.buscarPorId(idVideojuego);

			if (videojuego == null) {
				System.out.println("Error: El ID del Videojuego a ELIMINAR no existe...!!!");
				attribute.addFlashAttribute("error", "Atención: El ID del Videojuego a ELIMINAR no existe...!!!");
				return "redirect:/views/videojuegos/";
			}
		} else {
			System.out.println("Error: Error con el ID del Videojuego a ELIMINAR...!!!");
			attribute.addFlashAttribute("error", "Atención: Error con el ID del Videojuego a ELIMINAR...!!!");
			return "redirect:/views/videojuegos/";
		}

		videojuegoService.eliminar(idVideojuego);
		System.out.println("Registro eliminado con éxito...!!!");
		attribute.addFlashAttribute("warning", "Registro eliminado con éxito...!!!");

		return "redirect:/views/videojuegos/";
	}

}
