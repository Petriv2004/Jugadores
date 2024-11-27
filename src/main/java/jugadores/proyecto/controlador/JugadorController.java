package jugadores.proyecto.controlador;

import jugadores.proyecto.models.Jugador;
import jugadores.proyecto.services.JugadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jugador")
public class JugadorController {

    private JugadorService jugadorService;

    public JugadorController(JugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }

    @GetMapping("/all")
    public List<Jugador> getAllJugadores() {
        return jugadorService.getAllJugadores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jugador> getJugadorById(@PathVariable int id) {
        Jugador jugador = jugadorService.getJugadorById(id);
        if (jugador != null) {
            return ResponseEntity.ok(jugador);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/orderbyName")
    public List<Jugador> obtenerJugadoresOrdenadosPorNombre(){
        return jugadorService.obtenerJugadoresOrdenadosPorNombre();
    }

    @PostMapping("/add")
    public ResponseEntity<Jugador> createJugador(@RequestBody Jugador jugador) {
        Jugador newJugador = jugadorService.saveJugador(jugador);
        return ResponseEntity.status(HttpStatus.CREATED).body(newJugador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jugador> updateJugador(@PathVariable int id, @RequestBody Jugador jugadorDetails) {
        Jugador updatedJugador = jugadorService.updateJugador(id, jugadorDetails);
        if (updatedJugador != null) {
            return ResponseEntity.ok(updatedJugador);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJugador(@PathVariable int id) {
        jugadorService.deleteJugador(id);
        return ResponseEntity.noContent().build();
    }
}
