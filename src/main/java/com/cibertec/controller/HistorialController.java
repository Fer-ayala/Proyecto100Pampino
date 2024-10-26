package com.cibertec.controller;

import java.util.List;

import com.cibertec.models.entity.Historial;
import com.cibertec.models.entity.Producto;
import com.cibertec.models.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.models.service.HistorialService;


@Controller
@RequestMapping("/views/historial")
public class HistorialController {
	@Autowired
	private HistorialService historialervice;
	@Autowired
	private ProductoService productoService;

	@GetMapping("/")
	public String listarhistorial(Model model) {
		List<Historial> listadoHistorials = historialervice.listarTodos();
		model.addAttribute("titulo", "Lista de Historial");
		model.addAttribute("historial", listadoHistorials);

		return "/views/historial/listar";
	}

	@GetMapping("/create")
	public String crear(Model model) {
		Historial historial = new Historial();

		model.addAttribute("titulo", "Formulario: Nuevo Historial");
		model.addAttribute("historial", historial);
		List<Producto> listadoProductos = productoService.listarTodos();
		model.addAttribute("productos", listadoProductos);
		return "/views/historial/frmCrear";
	}
	@GetMapping("/find")
	public String buscar(Model model) {
		model.addAttribute("titulo", "Buscar producto para actualizar stock");
		List<Producto> listadoProductos = productoService.listarTodos();
		model.addAttribute("productos", listadoProductos);
		return "/views/historial/frmBuscar";
	}

	@PostMapping("/save")
	public String guardar(@ModelAttribute Historial historial, RedirectAttributes attribute) {

		historialervice.guardar(historial);
		System.out.println("Historial guardado con éxito...!!!");
		attribute.addFlashAttribute("success","Historial guardado con éxito...!!!");

		return "redirect:/views/historial/";
	}

	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") Integer idHistorial, Model model, RedirectAttributes attribute) {
		Historial historial = null;

		if (idHistorial > 0) {
			historial = historialervice.buscarPorId(idHistorial);

			if (historial == null) {
				System.out.println("Error: El ID del Historial a EDITAR no existe...!!!");
				attribute.addFlashAttribute("error", "Atención: El ID del Historial a EDITAR no existe...!!!");
				return "redirect:/views/historial/";
			}
		} else {
			System.out.println("Error: Error con el ID del Historial a EDITAR...!!!");
			attribute.addFlashAttribute("error", "Atención: Error con el ID del Historial a EDITAR...!!!");
			return "redirect:/views/historial/";
		}

		model.addAttribute("titulo", "Formulario: Editar datos");
		model.addAttribute("historial", historial);

		return "/views/historial/frmCrear";
	}
	@GetMapping("/cargar")
	public String cargar(@RequestParam("productoId") Integer idProducto, Model model, RedirectAttributes attribute) {
		Historial historial = new Historial();
		Producto producto;
		if (idProducto > 0) {
			producto = productoService.buscarPorId(idProducto);
			historial.setCodigo(producto.getCodigo());
			historial.setNombre(producto.getNombre());
			historial.setPrecio(producto.getPrecio());
			//Historial.setStock();

			if (historial == null) {
				System.out.println("Error: El ID del Historial a EDITAR no existe...!!!");
				attribute.addFlashAttribute("error", "Atención: El ID del Historial a EDITAR no existe...!!!");
				return "redirect:/views/historial/";
			}
		} else {
			System.out.println("Error: Error con el ID del Historial a EDITAR...!!!");
			attribute.addFlashAttribute("error", "Atención: Error con el ID del Historial a EDITAR...!!!");
			return "redirect:/views/historial/";
		}

		model.addAttribute("titulo", "Formulario: Agregar nuevo");
		model.addAttribute("historial", historial);

		return "/views/historial/frmCrear";
	}

	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") Integer idHistorial, RedirectAttributes attribute) {
		Historial historial = null;

		if (idHistorial > 0) {
			historial = historialervice.buscarPorId(idHistorial);

			if (historial == null) {
				System.out.println("Error: El ID del Historial a ELIMINAR no existe...!!!");
				attribute.addFlashAttribute("error", "Atención: El ID del Historial a ELIMINAR no existe...!!!");
				return "redirect:/views/historial/";
			}
		} else {
			System.out.println("Error: Error con el ID del Historial a ELIMINAR...!!!");
			attribute.addFlashAttribute("error", "Atención: Error con el ID del Historial a ELIMINAR...!!!");
			return "redirect:/views/historial/";
		}

		historialervice.eliminar(idHistorial);
		System.out.println("Registro eliminado con éxito...!!!");
		attribute.addFlashAttribute("warning", "Registro eliminado con éxito...!!!");

		return "redirect:/views/historial/";
	}

}
