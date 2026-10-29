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
    public void anular(Long id) {
        reservaRepository.findById(id).ifPresent(r -> {
            r.setEstado("Anulada");
            reservaRepository.save(r);
        });
    }
}