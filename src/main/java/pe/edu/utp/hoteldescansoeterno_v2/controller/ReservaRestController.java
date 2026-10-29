package pe.edu.utp.hoteldescansoeterno_v2.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import pe.edu.dto.ReservaDTO;
import pe.edu.entity.Habitacion;
import pe.edu.entity.Huesped;
import pe.edu.entity.Reserva;
import pe.edu.service.HabitacionService;
import pe.edu.service.HuespedService;
import pe.edu.service.ReservaService;

import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@Validated
public class ReservaRestController {

    private final ReservaService reservaService;
    private final HuespedService huespedService;
    private final HabitacionService habitacionService;

    public ReservaRestController(ReservaService reservaService,
                                  HuespedService huespedService,
                                  HabitacionService habitacionService) {
        this.reservaService = reservaService;
        this.huespedService = huespedService;
        this.habitacionService = habitacionService;
    }

    // GET: listar reservas
    @GetMapping
    public List<Reserva> listar() {
        return reservaService.listarTodas();
    }

    // GET: buscar por ID, con validación
    @GetMapping("/{id}")
    public Reserva buscarPorId(
            @PathVariable @Min(value = 1,
                message = "El ID debe ser mayor que cero") Long id) {

        return reservaService.buscarPorId(id)
            .orElseThrow(() ->
                new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Reserva no encontrada"
                )
            );
    }

    // POST: registrar una reserva con DTO y validaciones
    @PostMapping
    public ResponseEntity<Reserva> crear(
            @Valid @RequestBody ReservaDTO dto) {

        Reserva reserva = construirDesdeDto(dto, new Reserva());
        Reserva guardada = reservaService.guardar(reserva);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(guardada);
    }

    // PUT: actualizar una reserva
    @PutMapping("/{id}")
    public Reserva actualizar(
            @PathVariable @Min(1) Long id,
            @Valid @RequestBody ReservaDTO dto) {

        Reserva reserva = construirDesdeDto(dto, new Reserva());
        return reservaService.actualizar(id, reserva);
    }

    // PATCH: anular una reserva (regla de negocio existente, no es un DELETE físico)
    @PatchMapping("/{id}/anular")
    public ResponseEntity<Void> anular(
            @PathVariable @Min(1) Long id) {

        reservaService.anular(id);
        return ResponseEntity.noContent().build();
    }

    // DELETE: eliminar una reserva
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable @Min(1) Long id) {

        reservaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // Mapea el DTO validado a la entidad, resolviendo las FK y calculando noches/total
    private Reserva construirDesdeDto(ReservaDTO dto, Reserva reserva) {
        Huesped huesped = huespedService.buscarPorId(dto.getHuespedId())
            .orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Huésped no encontrado")
            );

        Habitacion habitacion = habitacionService.buscarPorId(dto.getHabitacionId())
            .orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Habitación no encontrada")
            );

        long noches = ChronoUnit.DAYS.between(dto.getFechaEntrada(), dto.getFechaSalida());
        if (noches <= 0) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "La fecha de salida debe ser posterior a la de entrada"
            );
        }

        reserva.setHuesped(huesped);
        reserva.setHabitacion(habitacion);
        reserva.setFechaEntrada(dto.getFechaEntrada());
        reserva.setFechaSalida(dto.getFechaSalida());
        reserva.setNoches((int) noches);
        reserva.setTotal(noches * habitacion.getPrecio().doubleValue());
        reserva.setEstado(dto.getEstado());

        return reserva;
    }
}
