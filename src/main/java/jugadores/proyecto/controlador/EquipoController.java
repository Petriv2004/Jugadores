package jugadores.proyecto.controlador;

import jugadores.proyecto.models.Equipo;
import jugadores.proyecto.models.Jugador;
import jugadores.proyecto.services.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipos")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    @GetMapping("/all")
    public List<Equipo> getAllEquipos() {
        return equipoService.getAllEquipos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipo> getEquipoById(@PathVariable int id) {
        Equipo equipo = equipoService.getEquipoById(id);
        if (equipo != null) {
            return ResponseEntity.ok(equipo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Equipo> createEquipo(@RequestBody Equipo equipo) {
        Equipo newEquipo = equipoService.saveEquipo(equipo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEquipo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipo> updateEquipo(@PathVariable int id, @RequestBody Equipo equipoDetails) {
        Equipo updatedEquipo = equipoService.updateEquipo(id, equipoDetails);
        if (updatedEquipo != null) {
            return ResponseEntity.ok(updatedEquipo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipo(@PathVariable int id) {
        equipoService.deleteEquipo(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/jugadoresT/{id}")
    public List<Jugador> jugadoresEquipo(@PathVariable int id) {
        return equipoService.jugadoresEquipo(id);
    }
}
