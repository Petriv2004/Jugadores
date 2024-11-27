package jugadores.proyecto.repositories;

import jugadores.proyecto.models.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipoRepository extends JpaRepository<Equipo,Integer> {
}
