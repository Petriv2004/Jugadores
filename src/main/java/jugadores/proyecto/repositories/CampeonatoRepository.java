package jugadores.proyecto.repositories;

import jugadores.proyecto.models.Campeonato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CampeonatoRepository extends JpaRepository<Campeonato,Integer> {
    @Query(value = "SELECT jugador_id FROM campeonato_jugador WHERE campeonato_id=:id", nativeQuery = true)
    List<Integer> getIdesP(@Param("id") int id);
}
