package jugadores.proyecto.services;

import jugadores.proyecto.models.Equipo;
import jugadores.proyecto.models.Jugador;
import jugadores.proyecto.repositories.EquipoRepository;
import jugadores.proyecto.repositories.JugadorRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class EquipoService {


    private EquipoRepository equipoRepository;
    private JugadorRepository jugadorRepository;

    public EquipoService(EquipoRepository equipoRepository, JugadorRepository jugadorRepository) {
        this.equipoRepository = equipoRepository;
        this.jugadorRepository = jugadorRepository;
    }

    public List<Equipo> getAllEquipos() {
        return equipoRepository.findAll();
    }

    public Equipo getEquipoById(int id) {
        return equipoRepository.findById(id).orElse(null);
    }

    public Equipo saveEquipo(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    public void deleteEquipo(int id) {
        equipoRepository.deleteById(id);
    }

    public Equipo updateEquipo(int id, Equipo equipoDetails) {
        Equipo equipo = getEquipoById(id);
        if (equipo != null) {
            equipo.setNombre(equipoDetails.getNombre());
            return equipoRepository.save(equipo);
        }
        return null;
    }

    public List<Jugador> jugadoresEquipo(int id) {
        return jugadorRepository.jugadores(id);
    }
}
