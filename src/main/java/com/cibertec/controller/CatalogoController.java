package com.cibertec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.cibertec.models.entity.Historial;
import com.cibertec.models.service.HistorialService;

import java.util.List;

@Controller
public class CatalogoController {
	
	 	@Autowired
	    private HistorialService videojuegoService;

	    @GetMapping("/catalogo")
	    public String mostrarCatalogo(Model model) {
	        List<Historial> historials = videojuegoService.listarTodos();
	        
	        model.addAttribute("titulo", "Catálogo de Videojuegos");
	        model.addAttribute("videojuegos", historials);
	        
	        return "views/videojuegos/catalogo";
	    }
}
