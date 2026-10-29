package pe.edu.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.entity.Huesped;
import pe.edu.repository.HuespedRepository;
import pe.edu.service.HuespedService;

import java.util.List;
import java.util.Optional;

@Service
public class HuespedServiceImpl implements HuespedService {

    @Autowired
    private HuespedRepository huespedRepository;

    @Override
    public List<Huesped> listarTodos() { return huespedRepository.findAll(); }

    @Override
    public Optional<Huesped> buscarPorId(Long id) { return huespedRepository.findById(id); }

    @Override
    public Huesped guardar(Huesped huesped) { return huespedRepository.save(huesped); }

    @Override
    public Huesped actualizar(Long id, Huesped huesped) {
        Huesped existente = huespedRepository.findById(id)
            .orElseThrow(() ->
                new IllegalArgumentException("No existe el huésped con ID " + id)
            );

        existente.setNombreCompleto(huesped.getNombreCompleto());
        existente.setDocumento(huesped.getDocumento());
        existente.setTelefono(huesped.getTelefono());
        existente.setEmail(huesped.getEmail());
        existente.setEstado(huesped.getEstado());

        return huespedRepository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        if (!huespedRepository.existsById(id)) {
            throw new IllegalArgumentException("No existe el huésped con ID " + id);
        }
        huespedRepository.deleteById(id);
    }
}