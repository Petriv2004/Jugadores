package jugadores.proyecto.services;

import jugadores.proyecto.models.Campeonato;
import jugadores.proyecto.models.Equipo;
import jugadores.proyecto.models.Jugador;
import jugadores.proyecto.repositories.CampeonatoRepository;
import jugadores.proyecto.repositories.EquipoRepository;
import jugadores.proyecto.repositories.JugadorRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JugadorService {
    private JugadorRepository jugadorRepository;
    private EquipoRepository equipoRepository;
    private CampeonatoRepository campeonatoRepository;

    public JugadorService(JugadorRepository jugadorRepository, EquipoRepository equipoRepository, CampeonatoRepository campeonatoRepository) {
        this.jugadorRepository = jugadorRepository;
        this.equipoRepository = equipoRepository;
        this.campeonatoRepository = campeonatoRepository;
    }

    public List<Jugador> getAllJugadores() {
        return jugadorRepository.findAll();
    }

    public Jugador getJugadorById(int id) {
        return jugadorRepository.findById(id).orElse(null);
    }

//    public Jugador saveJugador(Jugador jugador) {
//        Equipo equipo = equipoRepository.findById(jugador.getEquipo().getId()).orElse(null);
//        equipo.getJugadorList().add(jugador);
//        equipoRepository.save(equipo);
//        return jugadorRepository.save(jugador);
//    }

    public Jugador saveJugador(Jugador jugador) {
        Equipo equipo = equipoRepository.findById(jugador.getEquipo().getId()).orElse(null);
        if (equipo != null) {
            jugador.setEquipo(equipo);
        }
        return jugadorRepository.save(jugador);
    }


    public void deleteJugador(int id) {
        jugadorRepository.deleteById(id);
    }

    public Jugador updateJugador(int id, Jugador jugadorDetails) {
        Jugador jugador = getJugadorById(id);
        if (jugador != null) {
            jugador.setNombre(jugadorDetails.getNombre());
            jugador.setEdad(jugadorDetails.getEdad());
            jugador.setPosicion(jugadorDetails.getPosicion());


            if (jugadorDetails.getEquipo() != null) {
                Equipo equipo = equipoRepository.findById(jugadorDetails.getEquipo().getId()).orElse(null);
                if (equipo != null) {
                    jugador.setEquipo(equipo);
                }
            }

            if (jugadorDetails.getCampeonatoList() != null) {
                List<Campeonato> campeonatos = campeonatoRepository.findAllById(
                        jugadorDetails.getCampeonatoList().stream().map(Campeonato::getId).toList());
                jugador.setCampeonatoList(campeonatos);
            }

            return jugadorRepository.save(jugador);
        }
        return null;
    }

    public List<Jugador> obtenerJugadoresOrdenadosPorNombre(){
        return jugadorRepository.findAll(Sort.by(Sort.Direction.DESC,"nombre", "edad"));
    }
}
