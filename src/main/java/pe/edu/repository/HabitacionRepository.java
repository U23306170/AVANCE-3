package pe.edu.utp.hoteldescansoeterno_v2.repository;

import pe.edu.utp.hoteldescansoeterno_v2.entity.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HabitacionRepository
        extends JpaRepository<Habitacion, Long> {

    // SELECT utilizando JPA mediante JPQL
    @Query("SELECT * FROM Habitacion h WHERE h.tipo = :tipo")
    List<Habitacion> buscarPorTipo(String tipo);

    // SELECT utilizando SQL nativo
    @Query(
        value = "SELECT * FROM habitaciones WHERE precio > :precio",
        nativeQuery = true
    )
    List<Habitacion> buscarPorPrecioMayor(double precio);
}