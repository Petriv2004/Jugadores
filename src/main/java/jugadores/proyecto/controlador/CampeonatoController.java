package jugadores.proyecto.controlador;

import jugadores.proyecto.JugadoresApplication;
import jugadores.proyecto.models.Campeonato;
import jugadores.proyecto.models.Jugador;
import jugadores.proyecto.services.CampeonatoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campeonatos")
public class CampeonatoController {
    private CampeonatoService campeonatoService;

    public CampeonatoController(CampeonatoService campeonatoService) {
        this.campeonatoService = campeonatoService;
    }

    @GetMapping("/all")
    public List<Campeonato> getAllCampeonatos() {
        return campeonatoService.getAllCampeonatos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Campeonato> getCampeonatoById(@PathVariable int id) {
        Campeonato campeonato = campeonatoService.getCampeonatoById(id);
        if (campeonato != null) {
            return ResponseEntity.ok(campeonato);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Campeonato> createCampeonato(@RequestBody Campeonato campeonato) {
        Campeonato newCampeonato = campeonatoService.saveCampeonato(campeonato);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCampeonato);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Campeonato> updateCampeonato(@PathVariable int id, @RequestBody Campeonato campeonatoDetails) {
        Campeonato updatedCampeonato = campeonatoService.updateCampeonato(id, campeonatoDetails);
        if (updatedCampeonato != null) {
            return ResponseEntity.ok(updatedCampeonato);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCampeonato(@PathVariable int id) {
        campeonatoService.deleteCampeonato(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/campeonatoJ/{id}")
    public List<Jugador> getjugadoresCamp(@PathVariable int id){
        return campeonatoService.getJugadoresCamp(id);
    }
}
