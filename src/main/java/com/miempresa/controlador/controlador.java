package com.miempresa.controlador;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.miempresa.interfaceServicio.IJuegoServicio;
import com.miempresa.modelo.Juego;

import jakarta.validation.Valid;

@Controller
public class controlador {
	
	@Autowired
    private IJuegoServicio servicio;
	
	@GetMapping("/login")
    public String showLogin() {
        return "login";
    }
	
	@GetMapping("/")
    public String mostrarPagina(Model model) {
		model.addAttribute("juegos", servicio.listar());
        return "index";
    }
	
	@GetMapping("/listarJuegos")
    public String listarJuegos(Model model) {
        model.addAttribute("juegos", servicio.listar());
        return "listajuegos";
    }

    @GetMapping("/verJuego/{id}")
    public String verJuego(@PathVariable("id") int id, RedirectAttributes atributos) {
    	Optional<Juego> juego = servicio.listarId(id);
        atributos.addFlashAttribute("juego", juego.orElse(null));
        return "redirect:/verJuego";
    }
    
    @GetMapping("/verJuego")
    public String verJuego(@ModelAttribute("juego") Juego juego, Model model) {
        model.addAttribute("juego", juego);
        return "detalleJuego";
    }

    @GetMapping("/formJuego")
    public String formJuego(Model model) {
        model.addAttribute("juego", new Juego());
        return "formularioJuego";
    }

    @PostMapping("/guardarJuego")
    public String guardarJuego(@ModelAttribute("juego") @Valid Juego juego, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	model.addAttribute("juego", juego);
            return "formularioJuego";
        }
        servicio.guardar(juego);
        return "redirect:/listarJuegos";
    }

    @GetMapping("/editarJuego/{id}")
    public String editarJuego(@PathVariable("id") int id, RedirectAttributes atributos) {
        Optional<Juego> juego = servicio.listarId(id);
        atributos.addFlashAttribute("juego", juego.orElse(null));
        return "redirect:/mostrarJuego";
    }
    
    @GetMapping("/mostrarJuego")
    public String mostrarJuego(@ModelAttribute("juego") Juego juego, Model model) {
        model.addAttribute("juego", juego);
        return "formularioJuego";
    }

    @GetMapping("/eliminarJuego/{id}")
    public String eliminarJuego(@PathVariable("id") int id) {
    	servicio.borrar(id);
        return "redirect:/listarJuegos";
    }
    
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "error";
    }
    
    @GetMapping("/iniciarsesion")
    public String customLogin() {
        return "login"; 
    }
}
