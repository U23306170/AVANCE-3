package pe.edu.utp.hoteldescansoeterno_v2.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import pe.edu.dto.HabitacionDTO;
import pe.edu.entity.Habitacion;
import pe.edu.service.HabitacionService;
import pe.edu.dto.HabitacionDTO;
import pe.edu.entity.Habitacion;
import pe.edu.service.HabitacionService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/habitaciones")
@Validated
public class HabitacionRestController {

    private final HabitacionService service;

    public HabitacionRestController(HabitacionService service) {
        this.service = service;
    }

    // GET: listar habitaciones
    @GetMapping
    public List<Habitacion> listar() {
        return service.listar();
    }

    // GET: buscar por ID, con validación
    @GetMapping("/{id}")
    public Habitacion buscarPorId(
            @PathVariable @Min(value = 1,
                message = "El ID debe ser mayor que cero") Long id) {

        return service.buscarPorId(id)
            .orElseThrow(() ->
                new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Habitación no encontrada"
                )
            );
    }

    // POST: registrar una habitación con DTO y validaciones
    @PostMapping
    public ResponseEntity<Habitacion> crear(
            @Valid @RequestBody HabitacionDTO dto) {

        Habitacion habitacion = new Habitacion();

        habitacion.setNumero(dto.getNumero());
        habitacion.setTipo(dto.getTipo());
        habitacion.setPrecio(dto.getPrecio());
        habitacion.setCapacidad(dto.getCapacidad());

        Habitacion guardada = service.guardar(habitacion);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(guardada);
    }

    // PUT: actualizar una habitación
    @PutMapping("/{id}")
    public Habitacion actualizar(
            @PathVariable @Min(1) Long id,
            @Valid @RequestBody HabitacionDTO dto) {

        Habitacion habitacion = new Habitacion();

        habitacion.setNumero(dto.getNumero());
        habitacion.setTipo(dto.getTipo());
        habitacion.setPrecio(dto.getPrecio());
        habitacion.setCapacidad(dto.getCapacidad());

        return service.actualizar(id, habitacion);
    }

    // DELETE: eliminar una habitación
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable @Min(1) Long id) {

        service.eliminar(id);

        return ResponseEntity.noContent().build();
    }

    // GET: búsqueda por tipo con validaciones
    @GetMapping("/buscar")
    public List<Habitacion> buscarPorTipo(
            @RequestParam
            @NotBlank(message = "El tipo es obligatorio")
            @Size(max = 50,
                message = "El tipo no debe superar 50 caracteres")
            String tipo) {

        return service.buscarPorTipo(tipo);
    }

    // GET: consulta SQL nativa
    @GetMapping("/precio-mayor")
    public List<Habitacion> buscarPorPrecio(
            @RequestParam @Min(0) int precio) {

        return service.buscarPorPrecioMayor(precio);
    }
}