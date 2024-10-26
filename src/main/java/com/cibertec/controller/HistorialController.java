package com.cibertec.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.cibertec.models.entity.Historial;
import com.cibertec.models.entity.Producto;
import com.cibertec.models.service.ProductoService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.models.service.HistorialService;


@Controller
@RequestMapping("/views/historial")
public class HistorialController {
	@Autowired
	private HistorialService historialService;
	@Autowired
	private ProductoService productoService;

	@GetMapping("/")
	public String listarhistorial(Model model) {
		List<Historial> listadoHistorials = historialService.listarTodos();
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

		historialService.guardar(historial);
		System.out.println("Historial guardado con éxito...!!!");
		attribute.addFlashAttribute("success","Historial guardado con éxito...!!!");

		return "redirect:/views/historial/";
	}

	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") Integer idHistorial, Model model, RedirectAttributes attribute) {
		Historial historial = null;

		if (idHistorial > 0) {
			historial = historialService.buscarPorId(idHistorial);

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
			historial = historialService.buscarPorId(idHistorial);

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

		historialService.eliminar(idHistorial);
		System.out.println("Registro eliminado con éxito...!!!");
		attribute.addFlashAttribute("warning", "Registro eliminado con éxito...!!!");

		return "redirect:/views/historial/";
	}
	@GetMapping("/exportExcel")
	public ResponseEntity<byte[]> exportToExcel() throws IOException {
		List<Historial> historialList = historialService.listarTodos();

		// Crear un libro de trabajo y una hoja
		try (Workbook workbook = new XSSFWorkbook()) {
			Sheet sheet = workbook.createSheet("Historial");

			// Crear el encabezado
			Row headerRow = sheet.createRow(0);
			String[] columnHeaders = {"ID", "CODIGO", "NOMBRE", "DESCRIPCION", "STOCK", "PRECIO"};
			for (int i = 0; i < columnHeaders.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(columnHeaders[i]);
			}

			// Llenar los datos
			int rowNum = 1;
			for (Historial ent : historialList) {
				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(ent.getId());
				row.createCell(1).setCellValue(ent.getCodigo());
				row.createCell(2).setCellValue(ent.getNombre());
				row.createCell(3).setCellValue(ent.getDescripcion());
				row.createCell(4).setCellValue(ent.getStock());
				row.createCell(5).setCellValue(String.valueOf(ent.getPrecio()));
			}

			// Convertir el archivo a un byte array para la respuesta
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			workbook.write(outputStream);
			byte[] excelData = outputStream.toByteArray();

			// Configurar los headers para la descarga del archivo
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData("attachment", "historial.xlsx");

			return ResponseEntity.ok()
					.headers(headers)
					.body(excelData);
		}
	}

}
