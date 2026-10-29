package pe.edu.service;

import pe.edu.entity.Huesped;
import java.util.List;
import java.util.Optional;

public interface HuespedService {
    List<Huesped> listarTodos();
    Optional<Huesped> buscarPorId(Long id);
    Huesped guardar(Huesped huesped);
    Huesped actualizar(Long id, Huesped huesped);
    void eliminar(Long id);
}