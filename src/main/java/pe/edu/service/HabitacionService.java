package pe.edu.service;

import pe.edu.entity.Habitacion;

import java.util.List;
import java.util.Optional;

public interface HabitacionService {

    List<Habitacion> listar();

    Optional<Habitacion> buscarPorId(Long id);

    Habitacion guardar(Habitacion habitacion);

    Habitacion actualizar(Long id, Habitacion habitacion);

    void eliminar(Long id);

    List<Habitacion> buscarPorTipo(String tipo);

    List<Habitacion> buscarPorPrecioMayor(double precio);
}