package pe.edu.utp.hoteldescansoeterno_v2.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import pe.edu.dto.HuespedDTO;
import pe.edu.entity.Huesped;
import pe.edu.service.HuespedService;

import java.util.List;

@RestController
@RequestMapping("/api/huespedes")
@Validated
public class HuespedRestController {

    private final HuespedService service;

    public HuespedRestController(HuespedService service) {
        this.service = service;
    }

    // GET: listar huéspedes
    @GetMapping
    public List<Huesped> listar() {
        return service.listarTodos();
    }

    // GET: buscar por ID, con validación
    @GetMapping("/{id}")
    public Huesped buscarPorId(
            @PathVariable @Min(value = 1,
                message = "El ID debe ser mayor que cero") Long id) {

        return service.buscarPorId(id)
            .orElseThrow(() ->
                new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Huésped no encontrado"
                )
            );
    }

    // POST: registrar un huésped con DTO y validaciones
    @PostMapping
    public ResponseEntity<Huesped> crear(
            @Valid @RequestBody HuespedDTO dto) {

        Huesped huesped = new Huesped();
        huesped.setNombreCompleto(dto.getNombreCompleto());
        huesped.setDocumento(dto.getDocumento());
        huesped.setTelefono(dto.getTelefono());
        huesped.setEmail(dto.getEmail());
        huesped.setEstado(dto.getEstado());

        Huesped guardado = service.guardar(huesped);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(guardado);
    }

    // PUT: actualizar un huésped
    @PutMapping("/{id}")
    public Huesped actualizar(
            @PathVariable @Min(1) Long id,
            @Valid @RequestBody HuespedDTO dto) {

        Huesped huesped = new Huesped();
        huesped.setNombreCompleto(dto.getNombreCompleto());
        huesped.setDocumento(dto.getDocumento());
        huesped.setTelefono(dto.getTelefono());
        huesped.setEmail(dto.getEmail());
        huesped.setEstado(dto.getEstado());

        return service.actualizar(id, huesped);
    }

    // DELETE: eliminar un huésped
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable @Min(1) Long id) {

        service.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}
