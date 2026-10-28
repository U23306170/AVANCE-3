package pe.edu.utp.hoteldescansoeterno_v2.serviceImpl;

import pe.edu.utp.hoteldescansoeterno_v2.entity.Habitacion;
import pe.edu.utp.hoteldescansoeterno_v2.repository.HabitacionRepository;
import pe.edu.utp.hoteldescansoeterno_v2.service.HabitacionService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HabitacionServiceImpl implements HabitacionService {

    private final HabitacionRepository repository;

    public HabitacionServiceImpl(HabitacionRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Habitacion> listar() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Habitacion> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Habitacion guardar(Habitacion habitacion) {
        return repository.save(habitacion);
    }

    @Override
    public Habitacion actualizar(Long id, Habitacion habitacion) {
        Habitacion existente = repository.findById(id)
            .orElseThrow(() ->
                new IllegalArgumentException(
                    "No existe la habitación con ID " + id
                )
            );

        existente.setNumero(habitacion.getNumero());
        existente.setTipo(habitacion.getTipo());
        existente.setPrecio(habitacion.getPrecio());
        existente.setCapacidad(habitacion.getCapacidad());

        return repository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException(
                "No existe la habitación con ID " + id
            );
        }

        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Habitacion> buscarPorTipo(String tipo) {
        return repository.buscarPorTipo(tipo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Habitacion> buscarPorPrecioMayor(double precio) {
        return repository.buscarPorPrecioMayor(precio);
    }
}