package pe.edu.repository;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.entity.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface HabitacionRepository
        extends JpaRepository<Habitacion, Long> {

    @Query("SELECT h FROM Habitacion h WHERE h.tipo = :tipo")
    List<Habitacion> buscarPorTipo(@Param("tipo") String tipo);

    @Query(
        value = "SELECT * FROM habitaciones WHERE precio > :precio",
        nativeQuery = true
    )
    List<Habitacion> buscarPorPrecioMayor(double precio);
}