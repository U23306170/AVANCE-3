package pe.edu.utp.hoteldescansoeterno_v2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.entity.Huesped;
import pe.edu.service.HuespedService;

@Controller
@RequestMapping("/huespedes")
public class HuespedController {

    @Autowired
    private HuespedService huespedService;

    @GetMapping
    public String listarHuespedes(Model model) {
        model.addAttribute("activePage", "huespedes");
        model.addAttribute("huespedes", huespedService.listarTodos());
        return "huespedes";
    }

    @PostMapping("/guardar")
    public String guardar(@RequestParam(required = false) Long id,
                          @RequestParam String nombreCompleto,
                          @RequestParam String documento,
                          @RequestParam String telefono,
                          @RequestParam String email,
                          @RequestParam(defaultValue = "Activo") String estado,
                          RedirectAttributes ra) {

        Huesped h = (id != null) ? huespedService.buscarPorId(id).orElse(new Huesped()) : new Huesped();
        h.setNombreCompleto(nombreCompleto.trim());
        h.setDocumento(documento.trim());
        h.setTelefono(telefono.trim());
        h.setEmail(email.trim());
        h.setEstado(estado);
        huespedService.guardar(h);

        ra.addFlashAttribute("mensajeExito", id == null ? "Huésped registrado." : "Huésped actualizado.");
        return "redirect:/huespedes";
    }
}