package pe.edu.utp.hoteldescansoeterno_v2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.entity.Habitacion;
import pe.edu.service.HabitacionService;

import java.math.BigDecimal;

/**
 * Página de administración de habitaciones (inventario real del hotel).
 * A diferencia de /habitaciones (catálogo público con datos de ejemplo
 * en DatosDemo.java), esta sección sí lee y escribe directamente en la
 * base de datos MySQL a través de HabitacionService -> HabitacionRepository.
 */
@Controller
@RequestMapping("/admin/habitaciones")
public class HabitacionAdminController {

    @Autowired
    private HabitacionService habitacionService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("activePage", "admin-habitaciones");
        model.addAttribute("habitaciones", habitacionService.listar());
        return "admin-habitaciones";
    }

    @PostMapping("/guardar")
    public String guardar(@RequestParam(required = false) Long id,
                           @RequestParam String numero,
                           @RequestParam String tipo,
                           @RequestParam BigDecimal precio,
                           @RequestParam Integer capacidad,
                           RedirectAttributes ra) {

        Habitacion habitacion = new Habitacion();
        habitacion.setNumero(numero.trim());
        habitacion.setTipo(tipo.trim());
        habitacion.setPrecio(precio);
        habitacion.setCapacidad(capacidad);

        try {
            if (id != null) {
                habitacionService.actualizar(id, habitacion);
                ra.addFlashAttribute("mensajeExito", "Habitación " + numero + " actualizada correctamente.");
            } else {
                habitacionService.guardar(habitacion);
                ra.addFlashAttribute("mensajeExito", "Habitación " + numero + " registrada correctamente.");
            }
        } catch (IllegalArgumentException ex) {
            ra.addFlashAttribute("mensajeError", ex.getMessage());
        }

        return "redirect:/admin/habitaciones";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes ra) {
        try {
            habitacionService.eliminar(id);
            ra.addFlashAttribute("mensajeExito", "Habitación eliminada correctamente.");
        } catch (IllegalArgumentException ex) {
            ra.addFlashAttribute("mensajeError", ex.getMessage());
        }
        return "redirect:/admin/habitaciones";
    }
}
