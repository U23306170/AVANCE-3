package pe.edu.service;

import pe.edu.entity.Reserva;
import java.util.List;
import java.util.Optional;

public interface ReservaService {
    List<Reserva> listarTodas();
    Optional<Reserva> buscarPorId(Long id);
    Reserva guardar(Reserva reserva);
    void anular(Long id);
}