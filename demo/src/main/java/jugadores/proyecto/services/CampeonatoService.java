package jugadores.proyecto.services;

import jugadores.proyecto.models.Campeonato;
import jugadores.proyecto.models.Jugador;
import jugadores.proyecto.repositories.CampeonatoRepository;
import jugadores.proyecto.repositories.JugadorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CampeonatoService {


    private CampeonatoRepository campeonatoRepository;
    private JugadorRepository jugadorRepository;

    public CampeonatoService(CampeonatoRepository campeonatoRepository, JugadorRepository jugadorRepository) {
        this.campeonatoRepository = campeonatoRepository;
        this.jugadorRepository = jugadorRepository;
    }

    public List<Campeonato> getAllCampeonatos() {
        return campeonatoRepository.findAll();
    }

    public Campeonato getCampeonatoById(int id) {
        return campeonatoRepository.findById(id).orElse(null);
    }

    public Campeonato saveCampeonato(Campeonato campeonato) {
        return campeonatoRepository.save(campeonato);
    }

    public void deleteCampeonato(int id) {
        campeonatoRepository.deleteById(id);
    }

    public Campeonato updateCampeonato(int id, Campeonato campeonatoDetails) {
        Campeonato campeonato = getCampeonatoById(id);
        if (campeonato != null) {
            campeonato.setNombre(campeonatoDetails.getNombre());
            return campeonatoRepository.save(campeonato);
        }
        return null;
    }

    public List<Jugador> getJugadoresCamp(int id){
        List<Integer> arr = campeonatoRepository.getIdesP(id);
        List<Jugador> jug = new ArrayList<>();
        for (int i=0;i < arr.size();i++){
            jug.add(jugadorRepository.findById(arr.get(i)).orElse(null));
        }
        return jug;
    }
}
