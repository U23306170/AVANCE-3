package pe.edu.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.entity.Reserva;
import pe.edu.repository.ReservaRepository;
import pe.edu.service.ReservaService;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public List<Reserva> listarTodas() { return reservaRepository.findAll(); }

    @Override
    public Optional<Reserva> buscarPorId(Long id) { return reservaRepository.findById(id); }

    @Override
    public Reserva guardar(Reserva reserva) { return reservaRepository.save(reserva); }

    @Override
    public Reserva actualizar(Long id, Reserva reserva) {
        Reserva existente = reservaRepository.findById(id)
            .orElseThrow(() ->
                new IllegalArgumentException("No existe la reserva con ID " + id)
            );

        existente.setHuesped(reserva.getHuesped());
        existente.setHabitacion(reserva.getHabitacion());
        existente.setFechaEntrada(reserva.getFechaEntrada());
        existente.setFechaSalida(reserva.getFechaSalida());
        existente.setNoches(reserva.getNoches());
        existente.setTotal(reserva.getTotal());
        existente.setEstado(reserva.getEstado());

        return reservaRepository.save(existente);
    }

    @Override
    public void anular(Long id) {
        reservaRepository.findById(id).ifPresent(r -> {
            r.setEstado("Anulada");
            reservaRepository.save(r);
        });
    }

    @Override
    public void eliminar(Long id) {
        if (!reservaRepository.existsById(id)) {
            throw new IllegalArgumentException("No existe la reserva con ID " + id);
        }
        reservaRepository.deleteById(id);
    }
}