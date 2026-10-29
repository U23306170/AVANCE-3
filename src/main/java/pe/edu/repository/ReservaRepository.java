package pe.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.entity.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}