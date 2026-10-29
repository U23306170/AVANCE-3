package pe.edu.utp.hoteldescansoeterno_v2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.entity.Habitacion;
import pe.edu.entity.Huesped;
import pe.edu.entity.Reserva;
import pe.edu.service.HabitacionService;
import pe.edu.service.HuespedService;
import pe.edu.service.ReservaService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired private ReservaService reservaService;
    @Autowired private HuespedService huespedService;
    @Autowired private HabitacionService habitacionService;

    @GetMapping
    public String listarReserva(Model model) {
        List<Habitacion> habitaciones = habitacionService.listar();
        model.addAttribute("activePage", "reservas");
        model.addAttribute("reservas", reservaService.listarTodas());
        model.addAttribute("huespedes", huespedService.listarTodos());
        model.addAttribute("habitaciones", habitaciones);
        model.addAttribute("tipos", habitaciones.stream()
                .map(Habitacion::getTipo).distinct().sorted().toList());
        return "reservas";
    }

    @PostMapping("/guardar")
    public String guardar(@RequestParam(required = false) Long id,
                          @RequestParam Long huespedId,
                          @RequestParam Long habitacionId,
                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaEntrada,
                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaSalida,
                          @RequestParam String estado,
                          RedirectAttributes ra) {

        long noches = ChronoUnit.DAYS.between(fechaEntrada, fechaSalida);
        if (noches <= 0) {
            ra.addFlashAttribute("mensajeError", "La fecha de salida debe ser posterior a la de entrada.");
            return "redirect:/reservas";
        }

        Huesped huesped = huespedService.buscarPorId(huespedId).orElse(null);
        Habitacion habitacion = habitacionService.buscarPorId(habitacionId).orElse(null);
        if (huesped == null || habitacion == null) {
            ra.addFlashAttribute("mensajeError", "Huésped o habitación no válidos.");
            return "redirect:/reservas";
        }

        Reserva r = (id != null) ? reservaService.buscarPorId(id).orElse(new Reserva()) : new Reserva();
        r.setHuesped(huesped);
        r.setHabitacion(habitacion);
        r.setFechaEntrada(fechaEntrada);
        r.setFechaSalida(fechaSalida);
        r.setNoches((int) noches);
        r.setTotal(noches * habitacion.getPrecio().doubleValue());
        r.setEstado(estado);
        reservaService.guardar(r);

        ra.addFlashAttribute("mensajeExito", id == null ? "Reserva creada correctamente." : "Reserva actualizada.");
        return "redirect:/reservas";
    }

    @PostMapping("/anular/{id}")
    public String anular(@PathVariable Long id, RedirectAttributes ra) {
        reservaService.anular(id);
        ra.addFlashAttribute("mensajeExito", "Reserva #" + id + " anulada.");
        return "redirect:/reservas";
    }
}