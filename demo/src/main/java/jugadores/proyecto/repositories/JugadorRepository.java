package jugadores.proyecto.repositories;

import jugadores.proyecto.models.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JugadorRepository extends JpaRepository<Jugador,Integer> {
    @Query(value = "SELECT * FROM jugadores WHERE equipo_id =:id", nativeQuery = true)
    List<Jugador> jugadores(@Param("id") int id);
}
