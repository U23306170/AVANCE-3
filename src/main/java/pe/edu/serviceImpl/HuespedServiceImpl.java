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
}